package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.commands.drive.DriveOpenLoop;
import org.usfirst.frc.team687.robot.constants.DriveConstants;
import org.usfirst.frc.team687.robot.constants.DriveConstants.DriveMode;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	
	private final CANTalon m_encoderTalonR;
	private final CANTalon m_followerTalonR1;
	private final CANTalon m_followerTalonR2;
	private final CANTalon m_encoderTalonL;
	private final CANTalon m_followerTalonL1;
	private final CANTalon m_followerTalonL2;
	
	private final DoubleSolenoid m_shifter;
	
	private final BuiltInAccelerometer m_roborioAcc;
	
	private final AHRS m_nav;
	
	private double leftPow, rightPow, xPow, yPow;
	private double hyp, angle, robotAngle;
	private double m_err = 0; 
	
	private double m_targetR = 0;
	private double m_targetL = 0;
	private double m_lastR = 0;
	private double m_lastL = 0;
	
	private double m_distanceR, m_distanceL = 0;
	
	public Drive() {
		super();
		m_encoderTalonR = new CANTalon(RobotMap.encoderTalonRPort);
		m_followerTalonR1 = new CANTalon(RobotMap.followerTalonR1Port);
		m_followerTalonR2 = new CANTalon(RobotMap.followerTalonR2Port);
		m_encoderTalonL = new CANTalon(RobotMap.encoderTalonLPort);
		m_followerTalonL1 = new CANTalon(RobotMap.followerTalonL1Port);
		m_followerTalonL2 = new CANTalon(RobotMap.followerTalonL2Port);
		
		m_encoderTalonR.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		m_encoderTalonL.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		m_encoderTalonR.reverseSensor(true);
		m_encoderTalonL.reverseSensor(false);
		
		m_encoderTalonR.reverseOutput(true);
		m_encoderTalonL.reverseOutput(false);
		
		m_encoderTalonR.setP(DriveConstants.kDistP);
		m_encoderTalonR.setI(DriveConstants.kDistI);
		m_encoderTalonR.setD(DriveConstants.kDistD);
		m_encoderTalonR.setF(DriveConstants.kDistF);
		
		m_encoderTalonL.setP(DriveConstants.kDistP);
		m_encoderTalonL.setI(DriveConstants.kDistI);
		m_encoderTalonL.setD(DriveConstants.kDistD);
		m_encoderTalonL.setF(DriveConstants.kDistF);
		m_followerTalonR1.changeControlMode(TalonControlMode.Follower);
		m_followerTalonR2.changeControlMode(TalonControlMode.Follower);
		m_followerTalonL1.changeControlMode(TalonControlMode.Follower);
		m_followerTalonL2.changeControlMode(TalonControlMode.Follower);
		
		m_shifter = new DoubleSolenoid(RobotMap.shifterPort1, RobotMap.shifterPort2);
		
		m_nav = new AHRS(SerialPort.Port.kMXP);
		m_roborioAcc = new BuiltInAccelerometer();
		
		m_encoderTalonL.setCurrentLimit(DriveConstants.kDriveCurrentLimit);
		m_encoderTalonR.setCurrentLimit(DriveConstants.kDriveCurrentLimit);
		m_followerTalonL1.setCurrentLimit(DriveConstants.kDriveCurrentLimit);
		m_followerTalonL2.setCurrentLimit(DriveConstants.kDriveCurrentLimit);
		m_followerTalonR1.setCurrentLimit(DriveConstants.kDriveCurrentLimit);
		m_followerTalonR2.setCurrentLimit(DriveConstants.kDriveCurrentLimit);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveOpenLoop(DriveMode.ARCADE));
	}
	
	public void setOpenLoop(double leftPow, double rightPow) {
		m_encoderTalonR.changeControlMode(TalonControlMode.Voltage);
		m_encoderTalonL.changeControlMode(TalonControlMode.Voltage);
		
		m_encoderTalonR.set(rightPow * 12.0);
		m_followerTalonR1.set(m_encoderTalonR.getDeviceID());
		m_followerTalonR2.set(m_encoderTalonR.getDeviceID());
		
		m_encoderTalonL.set(leftPow * 12.0);
		m_followerTalonL1.set(m_encoderTalonL.getDeviceID());
		m_followerTalonL2.set(m_encoderTalonL.getDeviceID());
	}
	
	private double squareInputs(double input)	{
		return Math.pow(input, 2) * (input / Math.abs(input));
	}
	
	public void driveTankOpenLoop() {
		rightPow = squareInputs(Robot.oi.getLeftY());
		leftPow = -squareInputs(Robot.oi.getRightY());
		setOpenLoop(leftPow, rightPow);
	}
	
	public void driveArcadeOpenLoop() {
		yPow = -(Robot.oi.getLeftY());
		xPow = -(Robot.oi.getRightX());
		
		leftPow = xPow+yPow;
		
//		if (leftPow > 1) {
//			leftPow = 1;
//		} else if (leftPow < -1) {
//			leftPow = -1;
//		}	
		
		rightPow = xPow-yPow;
		
//		if (rightPow > 1) {
//			rightPow = 1;
//		} else if (rightPow < -1) {
//			rightPow = -1;
//		}
		
		if(Math.abs(leftPow) > 1 || Math.abs(rightPow) > 1)	{
			double max = Math.max(leftPow, rightPow);
			leftPow /= max;
			rightPow /= max;
		}
		
		m_targetL = DriveConstants.DriveAlpha*leftPow+m_lastL*(1-DriveConstants.DriveAlpha);
		m_targetR = DriveConstants.DriveAlpha*rightPow+m_lastR*(1-DriveConstants.DriveAlpha);
		
		if (m_targetL > 1) {
			m_targetL = 1;
		} else if (m_targetL < -1) {
			m_targetL = -1;
		}
		
		if (m_targetR > 1) {
			m_targetR = 1;
		} else if (m_targetR < -1) {
			m_targetR = -1;
		}
		
		m_lastL = m_targetL;
		m_lastR = m_targetR;
		
		setOpenLoop(m_targetL, m_targetR);
	}
	
	public void driveFieldCentric() {
		yPow = Robot.oi.getLeftY();
		xPow = Robot.oi.getLeftX();
		hyp =  Math.sqrt(xPow*xPow+yPow*yPow);
		angle = Math.atan2(yPow, xPow);
		robotAngle = ((-getYaw()+360) % 360)*Math.PI/180;
		angle += robotAngle;
		angle = angle % 2*Math.PI;
		xPow = hyp*Math.cos(angle);
		yPow = hyp*Math.sin(angle);
		leftPow = xPow+yPow;
		rightPow = xPow-yPow;
		SmartDashboard.putNumber("angle", angle);
		SmartDashboard.putNumber("leftPow", leftPow);
		SmartDashboard.putNumber("rightPow", rightPow);
		setOpenLoop(leftPow, rightPow);
	}
	
	public void shiftUp() {
		m_shifter.set(Value.kReverse);
	}
	
	public void shiftDown() {
		m_shifter.set(Value.kForward);
	}
	
	public double getYaw() {
		return m_nav.getYaw();
	}
	
	public void resetGyro() {
		m_nav.zeroYaw();
	}
	
	public void resetEncoders() {
		m_encoderTalonR.setPosition(0);
		m_encoderTalonL.setPosition(0);
	}
	
	public void initMotionProfile(double distanceR, double distanceL, double velocity, double acceleration) {
		m_encoderTalonR.setMotionMagicAcceleration(acceleration);
		m_encoderTalonL.setMotionMagicAcceleration(acceleration);
		m_encoderTalonR.setMotionMagicCruiseVelocity(velocity);
		m_encoderTalonL.setMotionMagicCruiseVelocity(velocity);
		m_distanceR = distanceR+m_encoderTalonR.getPosition();
		m_distanceL = distanceL+m_encoderTalonL.getPosition();
	}
	
	public void executeMotionProfile(double kP) {
		m_encoderTalonR.changeControlMode(TalonControlMode.MotionMagic);
		m_encoderTalonL.changeControlMode(TalonControlMode.MotionMagic);
		
		m_err = kP*getYaw();
		m_encoderTalonR.set(m_distanceR+m_err);
		m_encoderTalonL.set(m_distanceL-m_err);
		
		m_followerTalonR1.set(m_encoderTalonR.getDeviceID());
		m_followerTalonR2.set(m_encoderTalonR.getDeviceID());
		
		m_followerTalonL1.set(m_encoderTalonL.getDeviceID());
		m_followerTalonL2.set(m_encoderTalonL.getDeviceID());
	}
	
	public boolean motionProfileIsFinished() {
		return (Math.abs(m_distanceR-m_encoderTalonR.getPosition()) < DriveConstants.kDriveDistanceTolerance) && 
				(Math.abs(m_distanceL-m_encoderTalonL.getPosition()) < DriveConstants.kDriveDistanceTolerance);
	}
	
	public double getTotalCurrentLeft()	{
		return m_encoderTalonL.getOutputCurrent() + m_followerTalonL1.getOutputCurrent() + m_followerTalonL2.getOutputCurrent();
	}
	
	public double getTotalCurrentRight()	{
		return m_encoderTalonR.getOutputCurrent() + m_followerTalonR1.getOutputCurrent() + m_followerTalonR2.getOutputCurrent();
	}
	
	public double getRioFowardAcceleration()	{
		return m_roborioAcc.getY();
	}
	
	// Need to make sure Y is what we're looking for
	public double getNavForwardAcceleration()	{
		return m_nav.getRawAccelY();
	}
	
	public void reportState() {
		SmartDashboard.putNumber("Speed Drive Left", m_encoderTalonL.getSpeed());
		SmartDashboard.putNumber("Speed Drive Right", m_encoderTalonR.getSpeed());
		SmartDashboard.putNumber("Yaw", m_nav.getYaw());
		SmartDashboard.putNumber("Err R", m_encoderTalonR.getError());
		SmartDashboard.putNumber("Err L", m_encoderTalonL.getError());
		SmartDashboard.putNumber("Pos R", m_encoderTalonR.getPosition());
		SmartDashboard.putNumber("Pos L", m_encoderTalonL.getPosition());
		SmartDashboard.putNumber("Desired Enc R", m_distanceR);
		SmartDashboard.putNumber("Desired Enc L", m_distanceL);
		SmartDashboard.putNumber("Drive Straight Err", m_err);
		SmartDashboard.putNumber("Current Enc R", m_encoderTalonR.getOutputCurrent());
		SmartDashboard.putNumber("Current Enc L", m_encoderTalonL.getOutputCurrent());
		SmartDashboard.putNumber("Current Follower R1", m_followerTalonR1.getOutputCurrent());
		SmartDashboard.putNumber("Current Follower R2", m_followerTalonR2.getOutputCurrent());
		SmartDashboard.putNumber("Current Follower L1", m_followerTalonL1.getOutputCurrent());
		SmartDashboard.putNumber("Current Follower L2", m_followerTalonL2.getOutputCurrent());
	}
}