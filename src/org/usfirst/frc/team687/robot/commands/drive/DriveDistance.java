package org.usfirst.frc.team687.robot.commands.drive;
import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DriveConstants;

import edu.wpi.first.wpilibj.command.Command;


public class DriveDistance extends Command {
	
	private double m_distanceR, m_distanceL;
	private double m_maxVelocity;
	private double m_maxAcceleration;
	private double m_kP;

	public DriveDistance(double distance, double maxVelocity, double maxAcceleration, double kP) {
		m_distanceR = distance*DriveConstants.kDriveFeetToEncoderUnitsR;
		m_distanceL = distance*DriveConstants.kDriveFeetToEncoderUnitsL;
		m_maxVelocity = maxVelocity;
		m_maxAcceleration = maxAcceleration;
		m_kP = kP;
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		Robot.drive.initMotionProfile(m_distanceR, m_distanceL, m_maxVelocity, m_maxAcceleration);
	}
	
	@Override 
	protected void execute() {
		Robot.drive.executeMotionProfile(m_kP);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.drive.motionProfileIsFinished();
	}
	
}