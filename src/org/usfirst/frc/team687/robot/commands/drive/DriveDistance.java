package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class DriveDistance extends Command {
	
	private double m_distance;
	private double m_maxVelocity;
	private double m_maxAcceleration;
	private double m_timeNoLimit;
	private double m_maxVelocityNoLimit;
	private double m_cruiseVelocity;
	private double m_timeAccelerationPeriod;
	private double m_distanceAccelerationPeriod;
	private double m_cruiseDistance;
	private double m_timeEndCruise;
	private double m_timeEnd;
	private double m_intervalSize;
	private double[][] m_points;
	private double m_totalPoints;
	
	public DriveDistance(double distance, double maxVelocity, double maxAcceleration, double totalPoints) {
		m_distance = distance;
		m_maxVelocity = maxVelocity;
		m_maxAcceleration = maxAcceleration;
		m_distance = distance;
		
		m_timeNoLimit = Math.sqrt(m_distance/m_maxAcceleration);
		m_maxVelocityNoLimit = m_maxAcceleration*m_timeNoLimit;
		m_cruiseVelocity = Math.min(m_maxVelocity, m_maxVelocityNoLimit);
		m_timeAccelerationPeriod = m_cruiseVelocity/m_maxAcceleration;
		m_distanceAccelerationPeriod = m_maxAcceleration*m_timeAccelerationPeriod*m_timeAccelerationPeriod;
		m_cruiseDistance = m_distance - m_distanceAccelerationPeriod;
		m_timeEndCruise = (m_cruiseDistance/m_cruiseVelocity)+m_timeAccelerationPeriod;
		m_timeEnd = m_timeEndCruise+m_timeAccelerationPeriod;
		
		m_intervalSize = m_timeEnd/totalPoints;
		m_points = new double[(int) totalPoints][2];
		for (int i=0; i<m_timeEnd/m_intervalSize; i ++) {
			if (i < m_timeAccelerationPeriod) {
				m_points[i][0] = i*m_intervalSize;
				m_points[i][1] = m_maxAcceleration*i;
			} else if (i > m_timeAccelerationPeriod && i < m_timeEndCruise) {
				m_points[i][0] = i*m_intervalSize;
				m_points[i][1] = m_cruiseVelocity;
			} else if (i > m_timeEndCruise && i < m_timeEnd) {
				m_points[i][0] = i*m_intervalSize;
				m_points[i][1] = -m_maxAcceleration*i+(m_cruiseVelocity+m_maxAcceleration*m_timeEndCruise);
			}
		}
		m_totalPoints = totalPoints;
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {		
		Robot.drive.resetEncoders();
		Robot.drive.initializeMotionProfile(m_points, m_totalPoints);
	}
	
	@Override 
	protected void execute() {
		Robot.drive.executeMotionProfile();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
