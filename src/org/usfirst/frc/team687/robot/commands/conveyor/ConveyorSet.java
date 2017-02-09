package org.usfirst.frc.team687.robot.commands.conveyor;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ConveyorSet extends Command {
	
	private double m_desired;
	
	public ConveyorSet(double pow) {
		requires(Robot.conveyor);
		m_desired = pow;
	}
	
	@Override
	public void execute() {
		Robot.conveyor.setPercentVoltage(m_desired);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
