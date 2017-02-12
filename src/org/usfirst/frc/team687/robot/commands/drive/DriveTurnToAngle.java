package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DriveConstants;
import org.usfirst.frc.team687.robot.constants.DriveConstants.DriveMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTurnToAngle extends Command {
	
	private double m_angle;
	private double power;
	private double m_error;
	private double m_robotAngle;
	private double m_counter = 0;
	
	public DriveTurnToAngle(double angle) {
		m_angle = angle;
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		Robot.drive.resetGyro();
		Robot.drive.shiftDown();
	}

	@Override
	protected void execute() {
		m_robotAngle = (360-Robot.drive.getYaw()) % 360;
		m_error = m_angle - m_robotAngle;
		m_error = (m_error > 180) ? m_error-360 : m_error;
		m_error = (m_error < -180) ? m_error+360 : m_error;
		power = DriveConstants.kRotP * m_error;
		if (power > 1) {
			power = 1;
		} else if (power < -1) {
			power = -1;
		}
		if (Math.abs(m_error) <= DriveConstants.kDriveRotationTolerance) {
			m_counter += 1;
		}
		SmartDashboard.putNumber("power", power);
		SmartDashboard.putNumber("Yaw", Robot.drive.getYaw());
		Robot.drive.setOpenLoop(power, power);
	}

	@Override
	protected boolean isFinished() {
		return m_counter > DriveConstants.kDriveRotationOscillationCount;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}