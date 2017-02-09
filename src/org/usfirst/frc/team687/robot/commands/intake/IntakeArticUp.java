package org.usfirst.frc.team687.robot.commands.intake;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.IntakeConstants;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeArticUp extends Command {

	public IntakeArticUp() {
		requires(Robot.intake);
	}
	
	@Override
	public void execute() {
		Robot.intake.setArticPos(IntakeConstants.kIntakeUpPos);
	}
	
	@Override
	protected boolean isFinished() {
		return (Robot.intake.getArticPosition()-IntakeConstants.kIntakeUpPos < IntakeConstants.kIntakePosTolerance);
	}

}
