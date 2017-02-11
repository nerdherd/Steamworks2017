package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.commands.climber.ClimberSet;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	
	private final CANTalon m_climber;
	
	public Climber() {
		super();
		m_climber = new CANTalon(RobotMap.climberPort);
		m_climber.changeControlMode(TalonControlMode.Voltage);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ClimberSet(0));
	}
	
	public void setPercentVoltage(double pow) {
		m_climber.set(pow);
	}
	
	public double getCurrent() {
		return m_climber.getOutputCurrent();
	}
	
}
