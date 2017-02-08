package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DriveConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turn drivetrain to specified angle
 * 
 * @author Ted
 *
 */

public class DriveTurnToAngle extends Command {
	
	private double m_angle;
	private double power;
	private double m_error;
	
	public DriveTurnToAngle(double angle) {
		m_angle = angle;
		
		// subsystem dependencies
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		m_error = m_angle - Robot.drive.getYaw();
		power = DriveConstants.kRotP * m_error;
		Robot.drive.setPower(power, -power);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(m_error) <= 2;
	}

	@Override
	protected void end() {
		Robot.drive.stopDrive();
	}

	@Override
	protected void interrupted() {
	}

}