package org.usfirst.frc.team687.robot.commands.intake;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeManualControl extends Command {
	
	public IntakeManualControl() {
		requires(Robot.intake);
	}
	
	@Override
	public void execute() {
		Robot.intake.setArticPercentVoltage(Robot.oi.getArticY());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
