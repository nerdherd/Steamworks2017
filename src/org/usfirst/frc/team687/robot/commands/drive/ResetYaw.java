package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetYaw extends Command {
	
	public ResetYaw() {
		requires(Robot.drive);
	}
	
	@Override
	public void initialize() {
		Robot.drive.resetGyro();
	}
	
	@Override
	public void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
}
