package org.usfirst.frc.team687.robot.commands.intake;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.IntakeConstants;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeSetPos extends Command {
	
	private double m_pos;
	
	public IntakeSetPos(double pos) {
		requires(Robot.intake);
		m_pos = pos;
	}
	
	@Override
	public void execute() {
		Robot.intake.setArticPos(m_pos);
	}
	
	@Override
	protected boolean isFinished() {
		return (Robot.intake.getArticPosition()-IntakeConstants.kIntakeUpPos < IntakeConstants.kIntakePosTolerance);
	}

}
