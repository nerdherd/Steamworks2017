package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DriveConstants;

import edu.wpi.first.wpilibj.command.Command;

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
		Robot.drive.resetGyro();
	}

	@Override
	protected void execute() {
		m_error = m_angle - Robot.drive.getYaw();
		power = DriveConstants.kRotP * m_error;
		Robot.drive.setOpenLoop(power, -power);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(m_error) <= 2;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}