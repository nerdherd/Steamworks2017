package org.usfirst.frc.team687.robot.commands.gearintake;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeSetPosition extends Command {
	
	private double m_desired;
	
	public IntakeSetPosition(double desired) {
		requires(Robot.gearIntake);
		m_desired = desired;
	}
	
	@Override
	public void execute() {
		Robot.gearIntake.setPos(m_desired);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
