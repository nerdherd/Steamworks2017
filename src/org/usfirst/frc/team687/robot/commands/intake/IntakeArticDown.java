package org.usfirst.frc.team687.robot.commands.intake;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.IntakeConstants;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeArticDown extends Command {

	public IntakeArticDown() {
		requires(Robot.intake);
	}
	
	@Override
	public void execute() {
		Robot.intake.setArticPos(IntakeConstants.kIntakeDownPos);
	}
	
	@Override
	protected boolean isFinished() {
		return (Robot.intake.getArticPosition()-IntakeConstants.kIntakeDownPos < IntakeConstants.kIntakePosTolerance);
	}

}
