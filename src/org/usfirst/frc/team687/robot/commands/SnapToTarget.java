package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DriveConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Target high goal with vision info from NerdyVision (pynetworktables + OpenCV Python)
 * 
 * @author Ted
 *
 */

public class SnapToTarget extends Command {
	
	private NetworkTable m_table;
	private double m_error;

	public SnapToTarget() {
		// subsystem dependencies
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		m_table = NetworkTable.getTable("NerdyVision");
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void execute() {
		m_error = m_table.getDouble("ANGLE_TO_TURN") - Robot.drive.getYaw();
		double power = DriveConstants.kRotP * m_error;
		Robot.drive.setSpeed(power, -power);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return m_table.getBoolean("IS_ALIGNED");
	}

	@Override
	protected void end() {
		Robot.drive.stopDrive();
	}

	@Override
	protected void interrupted() {
	}
	
}
