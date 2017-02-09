package org.usfirst.frc.team687.robot.commands.intake;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.IntakeConstants;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeWheelsClosedLoop extends Command {
	
	private double m_desired;
	
	public IntakeWheelsClosedLoop(double speed) {
		requires(Robot.intake);
		m_desired = speed;
	}
	
	@Override
	public void execute() {
		Robot.intake.setWheelsSpeed(m_desired);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
