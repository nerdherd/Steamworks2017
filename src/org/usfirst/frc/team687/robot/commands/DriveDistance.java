package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DriveConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Motion-profiled drivetrain control in low gear
 *
 * @author Ted
 *
 */

public class DriveDistance extends Command{
	
	private double m_distance;

    public DriveDistance(double distance) {
    	m_distance = distance;
    	
    	// subsystem dependencies
        requires(Robot.drive);
    }

	@Override
	protected void initialize() {
		Robot.drive.resetSensors();
		Robot.drive.shiftDown();
	}

	@Override
	protected void execute() {
		Robot.drive.executeProfile(DriveConstants.kLowMaxV, DriveConstants.kLowMaxA, m_distance);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.drive.getPos() - m_distance) <= 1;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
