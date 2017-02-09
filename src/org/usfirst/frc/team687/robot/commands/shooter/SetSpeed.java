package org.usfirst.frc.team687.robot.commands.shooter;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetSpeed extends Command {
	
	private double m_desired;
	
	public SetSpeed(double desired) {
		requires(Robot.shooter);
		m_desired = desired;
	}
	
	@Override
	public void execute() {
		Robot.shooter.setSpeed(m_desired);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
