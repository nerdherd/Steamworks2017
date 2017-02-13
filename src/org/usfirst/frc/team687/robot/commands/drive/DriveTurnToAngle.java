package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DriveConstants;
import org.usfirst.frc.team687.robot.constants.DriveConstants.DriveMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTurnToAngle extends Command {
	
	private double m_angle;
	private double power;
	private double m_error;
	private double m_robotAngle;
	private double m_counter = 0;
	private double m_startTime;
	
	public DriveTurnToAngle(double angle) {
		m_angle = angle;
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		m_startTime = Timer.getFPGATimestamp();
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
		}	else	{
			m_counter = 0;
		}
		SmartDashboard.putNumber("power", power);
		SmartDashboard.putNumber("Yaw", Robot.drive.getYaw());
		Robot.drive.setOpenLoop(power, power);
	}

	@Override
	protected boolean isFinished() {
		return m_counter > DriveConstants.kDriveRotationOscillationCount || Timer.getFPGATimestamp() - m_startTime > 1.5;
	}

	@Override
	protected void end() {
		System.out.println("HI");
		Robot.drive.shiftUp();
		Robot.drive.setOpenLoop(0, 0);
	}

	@Override
	protected void interrupted() {
	}

}