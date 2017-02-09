package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.commands.drive.DriveOpenLoop;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	private final CANTalon m_encoderTalonR;
	private final CANTalon m_followerTalonR1;
	private final CANTalon m_followerTalonR2;
	private final CANTalon m_encoderTalonL;
	private final CANTalon m_followerTalonL1;
	private final CANTalon m_followerTalonL2;
	
	private final DoubleSolenoid m_shifter;
	
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
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveOpenLoop());
	}
	
	public void setOpenLoop(double leftPow, double rightPow) {
		m_encoderTalonR.changeControlMode(TalonControlMode.PercentVbus);
		m_encoderTalonL.changeControlMode(TalonControlMode.PercentVbus);
		
		m_encoderTalonR.set(rightPow);
		m_followerTalonR1.set(m_encoderTalonR.getDeviceID());
		m_followerTalonR2.set(m_encoderTalonR.getDeviceID());
		
		m_encoderTalonL.set(-leftPow);
		m_followerTalonL1.set(m_encoderTalonL.getDeviceID());
		m_followerTalonL2.set(m_encoderTalonL.getDeviceID());
	}
	
	public void shiftUp() {
		m_shifter.set(Value.kForward);
	}
	
	public void shiftDown() {
		m_shifter.set(Value.kReverse);
	}
}