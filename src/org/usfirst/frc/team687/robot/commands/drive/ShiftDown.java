package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftDown extends Command {
	
	public ShiftDown() {
		requires(Robot.drive);
	}
	
	@Override
	public void execute() {
		Robot.drive.shiftDown();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
