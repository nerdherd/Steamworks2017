package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.commands.gearintake.IntakeSetPosition;
import org.usfirst.frc.team687.robot.constants.GearIntakeConstants;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearIntake extends Subsystem {
	private CANTalon m_gearArtic;
	private CANTalon m_gearSpin;
	
	private double m_target;
	private double m_lastPos;
	
	public GearIntake() {
		m_gearArtic = new CANTalon(RobotMap.gearArticPort);
		m_gearSpin = new CANTalon(RobotMap.gearSpinPort);
		
		m_gearArtic.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		
		m_gearArtic.setP(GearIntakeConstants.kGearIntakeP);
		m_gearArtic.setI(GearIntakeConstants.kGearIntakeI);
		m_gearArtic.setD(GearIntakeConstants.kGearIntakeD);
		m_gearArtic.setF(GearIntakeConstants.kGearIntakeF);
		m_gearArtic.setCurrentLimit(GearIntakeConstants.kIntakeCurrentLimit);
		m_gearSpin.changeControlMode(TalonControlMode.Voltage);
		
		m_lastPos = m_gearArtic.getPosition();
	}
	
	public void setSpinVoltage(double pow) {
		m_gearSpin.set(pow);
	}
	
	public void setArticVoltage(double pow) {
		m_gearArtic.changeControlMode(TalonControlMode.Voltage);
		m_gearArtic.set(pow);
	}
	
	public void setPos(double pos) {
		m_gearArtic.changeControlMode(TalonControlMode.Position);
		m_target = pos*GearIntakeConstants.kIntakeAlpha+m_lastPos*(1-GearIntakeConstants.kIntakeAlpha);
		m_lastPos = m_target;
		m_gearArtic.set(m_target);
	}
	
	public void setPercentVoltage(double pow) {
		m_gearArtic.changeControlMode(TalonControlMode.PercentVbus);
		m_gearArtic.set(pow);
	}
	
	public void manualControl() {
		setPercentVoltage(Robot.oi.getArticY());
	}
	
	public double getPos() {
		return m_gearArtic.getPosition();
	}
	
	public void reportState() {
		SmartDashboard.putNumber("Gear Artic Pos", m_gearArtic.getPosition());
		SmartDashboard.putNumber("Gear Spin Speed", m_gearSpin.getSpeed());
	}
	
	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new IntakeSetPosition(GearIntakeConstants.kGearIntakeUpPos));
	}
}
