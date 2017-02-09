package org.usfirst.frc.team687.robot.commands.conveyor;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ConveyorOut extends Command {
	
	public ConveyorOut() {
		requires(Robot.conveyor);
	}
	
	@Override
	public void execute() {
		Robot.conveyor.setPercentVoltage(-1);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
