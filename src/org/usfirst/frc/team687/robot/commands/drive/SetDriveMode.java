package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DriveConstants.DriveMode;

import edu.wpi.first.wpilibj.command.Command;

public class SetDriveMode extends Command {
	
	private DriveMode m_mode; 
	
	public SetDriveMode(DriveMode mode) {
		requires(Robot.drive);
		m_mode = mode;
	}
	
	@Override
	public void initialize() {
		Robot.drive.setDriveMode(m_mode);
	}
	
	@Override
	public void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
}
