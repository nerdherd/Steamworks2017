package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.commands.climber.ClimberSet;
import org.usfirst.frc.team687.robot.constants.ClimberConstants;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {
	
	private final CANTalon m_climber;
	
	public Climber() {
		super();
		m_climber = new CANTalon(RobotMap.climberPort);
		m_climber.changeControlMode(TalonControlMode.Voltage);
		m_climber.setCurrentLimit(ClimberConstants.kCurrentLimit);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ClimberSet(0));
	}
	
	public void setPercentVoltage(double pow) {
		//m_climber.changeControlMode(TalonControlMode.Voltage);
		//m_climber.set(pow);
		m_climber.changeControlMode(TalonControlMode.PercentVbus);
		m_climber.set(1);
	}
	
	public void setCurrent(double current)	{
		m_climber.changeControlMode(TalonControlMode.Current); // do we need to set pid?
		m_climber.set(current);
	}
	
	public double getCurrent() {
		return m_climber.getOutputCurrent();
	}
	
	public void reportState()	{
		SmartDashboard.putNumber("Climber Current", getCurrent());
		SmartDashboard.putNumber("Climber Encoder", m_climber.getPosition());
	}
	
}
