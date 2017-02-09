package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Conveyor extends Subsystem {
	
	private final CANTalon m_conveyor; 
	
	public Conveyor() {
		m_conveyor = new CANTalon(RobotMap.conveyorPort);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void setPercentVoltage(double pow) {
		m_conveyor.set(pow);
	}
}
