package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftDown extends Command {
	
	public ShiftDown() {
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		Robot.drive.shiftDown();
	}
	
	@Override
	public void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
}
