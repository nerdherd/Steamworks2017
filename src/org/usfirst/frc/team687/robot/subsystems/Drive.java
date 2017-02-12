package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.commands.drive.DriveOpenLoop;
import org.usfirst.frc.team687.robot.constants.DriveConstants.DriveMode;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Scheduler;
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
	
	private final AHRS m_nav;
	
	private double leftPow, rightPow, xPow, yPow;
	private double hyp, angle, robotAngle;
	double mode = 0;
	
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
		
		m_followerTalonR1.changeControlMode(TalonControlMode.Follower);
		m_followerTalonR2.changeControlMode(TalonControlMode.Follower);
		m_followerTalonL1.changeControlMode(TalonControlMode.Follower);
		m_followerTalonL2.changeControlMode(TalonControlMode.Follower);
		
		m_shifter = new DoubleSolenoid(RobotMap.shifterPort1, RobotMap.shifterPort2);
		
		m_nav = new AHRS(SerialPort.Port.kMXP);
	}

	@Override
	protected void initDefaultCommand() {
	}
	
	public void setOpenLoop(double leftPow, double rightPow) {
		m_encoderTalonR.changeControlMode(TalonControlMode.PercentVbus);
		m_encoderTalonL.changeControlMode(TalonControlMode.PercentVbus);
		
		m_encoderTalonR.set(rightPow);
		m_followerTalonR1.set(m_encoderTalonR.getDeviceID());
		m_followerTalonR2.set(m_encoderTalonR.getDeviceID());
		
		m_encoderTalonL.set(leftPow);
		m_followerTalonL1.set(m_encoderTalonL.getDeviceID());
		m_followerTalonL2.set(m_encoderTalonL.getDeviceID());
	}
	
	public void driveTankOpenLoop() {
		leftPow = Robot.oi.getLeftY();
		rightPow = -Robot.oi.getRightY();
		setOpenLoop(leftPow, rightPow);
	}
	
	public void driveArcadeOpenLoop() {
		yPow = Robot.oi.getLeftY();
		xPow = Robot.oi.getRightX();
		leftPow = xPow+yPow;
		rightPow = xPow-yPow; 
		setOpenLoop(leftPow, rightPow);
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
		m_shifter.set(Value.kForward);
	}
	
	public void shiftDown() {
		m_shifter.set(Value.kReverse);
	}
	
	public double getYaw() {
		return m_nav.getYaw();
	}
	
	public void resetGyro() {
		m_nav.reset();
	}
	
	public void resetEncoders() {
		m_encoderTalonR.reset();
		m_encoderTalonL.reset();
	}
	
	public void executeMotionProfile(double[][] points, double totalPoints) {
		m_encoderTalonR.changeControlMode(TalonControlMode.MotionProfile);
		m_encoderTalonL.changeControlMode(TalonControlMode.MotionProfile);
		m_encoderTalonR.clearMotionProfileTrajectories();
		m_encoderTalonL.clearMotionProfileTrajectories();
		
		CANTalon.TrajectoryPoint pointR = new CANTalon.TrajectoryPoint();
		CANTalon.TrajectoryPoint pointL = new CANTalon.TrajectoryPoint();
		
		for (int i = 0; i < totalPoints; i++) {
			pointR.velocity = -points[i][1];
			pointL.velocity = points[i][1];
			pointR.timeDurMs = (int) points[i][0];
			pointL.timeDurMs = (int) points[i][0];
			pointR.profileSlotSelect = 0;
			pointL.profileSlotSelect = 0;
			pointL.velocityOnly = true; 
			pointR.velocityOnly = true;
			pointL.zeroPos = false;
			pointR.zeroPos = false;
			if (i == 0)
				pointL.zeroPos = true;
				pointR.zeroPos = true;

			pointR.isLastPoint = false;
			pointL.isLastPoint = false;
			if ((i + 1) == totalPoints)
				pointR.isLastPoint = true;
				pointL.isLastPoint = true;

			m_encoderTalonR.pushMotionProfileTrajectory(pointR);
			m_encoderTalonL.pushMotionProfileTrajectory(pointL);
		}
	}
	
	public void reportState() {
		SmartDashboard.putNumber("Speed Drive Left", m_encoderTalonL.getSpeed());
		SmartDashboard.putNumber("Speed Drive Right", m_encoderTalonR.getSpeed());
		SmartDashboard.putNumber("Yaw", m_nav.getYaw());
	}
}