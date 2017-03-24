package org.usfirst.frc.team687.robot.commands.gearintake;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.commands.TimeoutCommand;

public class SpinSpeedTime extends TimeoutCommand {
	
	private double m_desired;
	
	public SpinSpeedTime(double pow, double timeout) {
		super(timeout);
		requires(Robot.gearIntake);
		m_desired = pow;
	}
	
	@Override
	public void execute() {
		Robot.gearIntake.setSpinVoltage(m_desired);
	}
	
	@Override
	protected boolean isFinished() {
		return timeout();
	}

}
