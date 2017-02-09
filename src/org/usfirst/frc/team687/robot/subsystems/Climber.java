package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;

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
	}
	
	public void setPercentVoltage(double pow) {
		m_climber.set(pow);
	}
	
	public double getCurrent() {
		return m_climber.getOutputCurrent();
	}
	
}
