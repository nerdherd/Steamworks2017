package org.usfirst.frc.team687.robot.commands.gearintake;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeManualControl extends Command {
	
	public IntakeManualControl() {
		requires(Robot.gearIntake);
	}
	
	@Override
	public void execute() {
		Robot.gearIntake.manualControl();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}