package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.commands.TimeoutCommand;
import org.usfirst.frc.team687.robot.constants.DriveConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTurnToAngle extends TimeoutCommand {
	
	private double m_angle;
	private double power;
	private double m_error;
	private double m_robotAngle;
	private double m_counter = 0;
	private double m_startTime;
	
	public DriveTurnToAngle(double angle, double time) {
		super(time);
		m_angle = angle;
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		init();
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
		SmartDashboard.putNumber("Turn to Angle Power", power);
		Robot.drive.setOpenLoop(power, power);
	}

	@Override
	protected boolean isFinished() {
		return m_counter > DriveConstants.kDriveRotationOscillationCount || timeout();
		//return false;
	}

	@Override
	protected void end() {
		Robot.drive.shiftUp();
		Robot.drive.setOpenLoop(0, 0);
	}

	@Override
	protected void interrupted() {
	}

}