package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DriveConstants.DriveMode;

import edu.wpi.first.wpilibj.command.Command;

public class DriveOpenLoop extends Command {
	
	private DriveMode m_mode; 
	
	public DriveOpenLoop(DriveMode mode) {
		requires(Robot.drive);
		m_mode = mode;
	}
	
	@Override
	public void execute() {
		switch(m_mode) {
		case TANK:
			Robot.drive.driveTankOpenLoop();
			break;
		case ARCADE:
			Robot.drive.driveArcadeOpenLoop();
			break;
		case FIELD_CENTRIC:
			Robot.drive.driveFieldCentric();
			break;
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
