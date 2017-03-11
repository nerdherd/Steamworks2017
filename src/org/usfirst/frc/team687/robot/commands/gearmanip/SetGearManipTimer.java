package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.commands.TimeoutCommand;

public class SetGearManipTimer extends TimeoutCommand {
	
	private double m_desired;
	
	public SetGearManipTimer(double desired, double timeout) {
		super(timeout);
		requires(Robot.gearManip);
		m_desired = desired;
	}
	
	@Override
	public void execute() {
		Robot.gearManip.setPos(m_desired);
	}
	
}
