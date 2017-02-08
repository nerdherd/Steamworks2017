package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Really simple substitute command for driving a distance 
 * Will change to more complex closed loop later
 */

public class DriveDistance extends Command {
	
	private double m_distance;
	
	public DriveDistance(double distance) {
		m_distance = distance;
		
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
	}
	
	@Override 
	protected void execute() {
		if (m_distance < 0) {
			Robot.drive.setOpenLoop(-0.5, -0.5);
		} else if (m_distance > 0){
			Robot.drive.setOpenLoop(0.5, 0.5);
		} else {
			Robot.drive.setOpenLoop(0, 0);
		}
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.drive.getPosition() - m_distance) <= 5;
	}
	
}
