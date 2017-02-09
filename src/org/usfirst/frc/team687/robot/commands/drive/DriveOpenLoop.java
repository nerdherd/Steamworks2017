package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveOpenLoop extends Command {
	
	public DriveOpenLoop() {
		requires(Robot.drive);
	}
	
	@Override
	public void execute() {
		Robot.drive.setOpenLoop(Robot.oi.getLeftY(), Robot.oi.getRightY());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
