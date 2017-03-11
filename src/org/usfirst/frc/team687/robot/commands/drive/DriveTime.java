package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.commands.TimeoutCommand;

public class DriveTime extends TimeoutCommand {
	
	private double m_leftPow;
	private double m_rightPow;
	
	public DriveTime(double leftPow, double rightPow, double time) {
		super(time);
		
		m_leftPow = leftPow;
		m_rightPow = rightPow;
		
		requires(Robot.drive);
	}
	
	@Override
	protected void end() {
		Robot.drive.setOpenLoop(0, 0);
	}
	
	@Override
	public void execute() {
		Robot.drive.setOpenLoop(m_leftPow, m_rightPow);
	}
	
}
