package org.usfirst.frc.team687.robot.commands.intake;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.IntakeConstants;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeInClosedLoop extends Command {

	public IntakeInClosedLoop() {
		requires(Robot.intake);
	}
	
	@Override
	public void execute() {
		Robot.intake.setWheelsSpeed(IntakeConstants.kIntakeWheelRPM);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
