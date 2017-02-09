package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Open loop tank drive
 * 
 * @author Ted
 *
 */

public class TankDrive extends Command{

    public TankDrive() {
    	// subsystem dependencies
        requires(Robot.drive);
    }

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.drive.setPower(Robot.oi.getDriveJoyL(), Robot.oi.getDriveJoyR());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}