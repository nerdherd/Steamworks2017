package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetGearManipNoTimer extends Command {
	
	private double m_desired;
	
	public SetGearManipNoTimer(double desired) {
		requires(Robot.gearManip);
		m_desired = desired;
	}
	
	@Override
	public void execute() {
		Robot.gearManip.setPos(m_desired);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
