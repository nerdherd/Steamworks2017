package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.constants.IntakeConstants;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	private final CANTalon m_artic;
	private final CANTalon m_wheels;
	
	public Intake() {
		m_artic = new CANTalon(RobotMap.intakeArticPort);
		m_wheels = new CANTalon(RobotMap.intakeWheelPort);
		
		m_artic.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		m_wheels.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		m_artic.setP(IntakeConstants.kIntakeArticP);
		m_artic.setI(IntakeConstants.kIntakeArticI);
		m_artic.setD(IntakeConstants.kIntakeArticD);
		m_artic.setF(IntakeConstants.kIntakeArticF);
		
		m_wheels.setP(IntakeConstants.kIntakeWheelP);
		m_wheels.setI(IntakeConstants.kIntakeWheelI);
		m_wheels.setD(IntakeConstants.kIntakeWheelD);
		m_wheels.setF(IntakeConstants.kIntakeWheelF);
	}
	
	public void setArticPercentVoltage(double pow) {
		m_artic.changeControlMode(TalonControlMode.PercentVbus);
		m_artic.set(pow);
	}
	
	public void setArticPos(double position) {
		m_artic.changeControlMode(TalonControlMode.Position);
		m_artic.set(position);
	}
	
	public void setWheelsPercentVoltage(double pow) {
		m_wheels.changeControlMode(TalonControlMode.PercentVbus);
		m_wheels.set(pow);
	}
	
	public void setWheelsSpeed(double speed) {
		m_wheels.changeControlMode(TalonControlMode.Speed);
		m_wheels.set(speed);
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}
