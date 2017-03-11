package org.usfirst.frc.team687.robot.commands.climber;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.ClimberConstants;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberSet extends Command {
	
	private double m_desired;
	
	public ClimberSet(double pow) {
		requires(Robot.climber);
		m_desired = pow;
	}
	
	@Override
	public void execute() {
		Robot.climber.setPercentVoltage(m_desired);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.climber.getCurrent() > ClimberConstants.kMaxCurrent;
	}
	
}
