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
	
	private boolean m_profileIsFinished;
	
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
		m_profileIsFinished = false;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveOpenLoop(DriveMode.TANK));
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
		leftPow = -Robot.oi.getLeftY();
		rightPow = Robot.oi.getRightY();
		setOpenLoop(leftPow, rightPow);
	}
	
	public void driveArcadeOpenLoop() {
		yPow = -Robot.oi.getLeftY();
		xPow = -Robot.oi.getRightX();
		
		leftPow = xPow+yPow;
		
		if (leftPow > 1) {
			leftPow = 1;
		} else if (leftPow < -1) {
			leftPow = -1;
		}	
		
		rightPow = xPow-yPow;
		
		if (rightPow > 1) {
			rightPow = 1;
		} else if (rightPow < -1) {
			rightPow = -1;
		}
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
		m_shifter.set(Value.kReverse);
	}
	
	public void shiftDown() {
		m_shifter.set(Value.kForward);
	}
	
	public double getYaw() {
		return m_nav.getYaw();
	}
	
	public void resetGyro() {
		m_nav.reset();
	}
	
	public void resetEncoders() {
		m_encoderTalonR.reset();
//		m_encoderTalonL.reset();
	}
	
	private CANTalon.MotionProfileStatus m_status = new CANTalon.MotionProfileStatus();
	
	public void initializeMotionProfile(double[][] points) {
		if (m_status.hasUnderrun) {
			System.out.println("Motion Profile has underrun");
			m_encoderTalonR.clearMotionProfileHasUnderrun();
//			m_encoderTalonL.clearMotionProfileHasUnderrun();
		}
		
		m_encoderTalonR.changeControlMode(TalonControlMode.MotionProfile);
//		m_encoderTalonL.changeControlMode(TalonControlMode.MotionProfile);
		m_encoderTalonR.clearMotionProfileTrajectories();
//		m_encoderTalonL.clearMotionProfileTrajectories();
		
		m_encoderTalonR.setF(0.987);
		m_encoderTalonR.setP(0.971);
		m_encoderTalonR.setI(0);
		m_encoderTalonR.setD(0);
//		m_encoderTalonR.setF(0.987);
//		m_encoderTalonR.setP(0.1);
//		m_encoderTalonR.setI(0);
//		m_encoderTalonR.setD(0);
		
		CANTalon.TrajectoryPoint pointR = new CANTalon.TrajectoryPoint();
		CANTalon.TrajectoryPoint pointL = new CANTalon.TrajectoryPoint();
		
		for (int i = 0; i < points.length; i++) {
			pointR.velocity = -points[i][1];
			pointL.velocity = points[i][1];
			pointR.position = 0;
			pointL.position = 0;
			pointR.timeDurMs = (int) points[i][2];
			pointL.timeDurMs = (int) points[i][2];
			pointR.profileSlotSelect = 0;
			pointL.profileSlotSelect = 0;
			pointL.velocityOnly = true; 
			pointR.velocityOnly = true;
			pointL.zeroPos = i==0;
			pointR.zeroPos = i==0;

			pointR.isLastPoint = (i + 1) == points.length;
			pointL.isLastPoint = (i + 1) == points.length;
			m_encoderTalonR.pushMotionProfileTrajectory(pointR);
//			m_encoderTalonL.pushMotionProfileTrajectory(pointL);
		}
	}
	
	public void executeMotionProfile()	{
		m_encoderTalonR.getMotionProfileStatus(m_status);
//		m_encoderTalonL.getMotionProfileStatus(m_status);
		if (m_status.hasUnderrun) {
			System.out.println("Has Underrun");
			m_encoderTalonR.clearMotionProfileHasUnderrun();
			m_encoderTalonR.processMotionProfileBuffer();
//			m_encoderTalonL.clearMotionProfileHasUnderrun();
//			m_encoderTalonL.processMotionProfileBuffer();
		}
		if (m_status.activePointValid && m_status.activePoint.isLastPoint) {
			System.out.println("m_status.activePoint.isLastPoint");
			m_encoderTalonR.set(CANTalon.SetValueMotionProfile.Hold.value);
//			m_encoderTalonL.set(CANTalon.SetValueMotionProfile.Hold.value);
			m_profileIsFinished = true;
		} else {
			m_encoderTalonR.set(CANTalon.SetValueMotionProfile.Enable.value);
			m_encoderTalonR.processMotionProfileBuffer();
//			m_encoderTalonL.set(CANTalon.SetValueMotionProfile.Enable.value);
//			m_encoderTalonL.processMotionProfileBuffer();
		}
	}
	
	public boolean profileIsFinished() {
		return m_profileIsFinished;
	}
	
	public void motionProfileEnd() {
		m_encoderTalonR.set(CANTalon.SetValueMotionProfile.Hold.value);
		m_encoderTalonR.clearMotionProfileTrajectories();
		m_encoderTalonR.set(CANTalon.SetValueMotionProfile.Disable.value);
		m_encoderTalonR.changeControlMode(TalonControlMode.PercentVbus);
//		m_encoderTalonL.set(CANTalon.SetValueMotionProfile.Hold.value);
//		m_encoderTalonL.clearMotionProfileTrajectories();
//		m_encoderTalonL.set(CANTalon.SetValueMotionProfile.Disable.value);
//		m_encoderTalonL.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public void reportState() {
		SmartDashboard.putNumber("Speed Drive Left", m_encoderTalonL.getSpeed());
		SmartDashboard.putNumber("Speed Drive Right", m_encoderTalonR.getSpeed());
		SmartDashboard.putNumber("Yaw", m_nav.getYaw());
	}
}