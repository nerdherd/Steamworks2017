package org.usfirst.frc.team687.robot.commands.drive;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.GeneratedTalonMP;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {
	
//	private double m_distance;
//	private double m_maxVelocity;
//	private double m_maxAcceleration;
//	private double m_timeNoLimit;
//	private double m_maxVelocityNoLimit;
//	private double m_cruiseVelocity;
//	private double m_timeAccelerationPeriod;
//	private double m_distanceAccelerationPeriod;
//	private double m_cruiseDistance;
//	private double m_timeEndCruise;
//	private double m_timeEnd;
//	private double m_clockSpeed;
//	private double m_clkInMinutes;
//	private double m_time;
//	private double[][] m_points;
//	private double m_totalPoints;
	
	/**
	 * @param distance (rotations)
	 * @param maxVelocity (RPM)
	 * @param maxAcceleration (rotations/minutes^2)
	 * @param clockSpeed (ms)
	 */
	public DriveDistance(double distance, double maxVelocity, double maxAcceleration, double clockSpeed) {
//		m_distance = distance;
//		m_maxVelocity = maxVelocity;
//		m_maxAcceleration = maxAcceleration;
//		m_distance = distance;
//		m_clockSpeed = clockSpeed;
//		m_clkInMinutes = m_clockSpeed/60000;
//		
//		m_timeNoLimit = Math.sqrt(m_distance/m_maxAcceleration);
//		m_maxVelocityNoLimit = m_maxAcceleration*m_timeNoLimit;
//		m_cruiseVelocity = Math.min(m_maxVelocity, m_maxVelocityNoLimit);
//		m_timeAccelerationPeriod = m_cruiseVelocity/m_maxAcceleration;
//		m_distanceAccelerationPeriod = m_maxAcceleration*m_timeAccelerationPeriod*m_timeAccelerationPeriod;
//		m_cruiseDistance = m_distance - m_distanceAccelerationPeriod;
//		m_timeEndCruise = (m_cruiseDistance/m_cruiseVelocity)+m_timeAccelerationPeriod;
//		m_timeEnd = m_timeEndCruise+m_timeAccelerationPeriod;
//		
//		m_totalPoints = m_timeEnd/m_clockSpeed;
//		m_points = new double[(int) m_totalPoints+1][2];
//		for (int i=0; i<m_totalPoints; i ++) {
//			if (i < m_timeAccelerationPeriod) {
//				m_time = i*m_clkInMinutes;
//				m_points[i][0] = m_clockSpeed;
//				m_points[i][1] = m_maxAcceleration*m_time;
//			} else if (i > m_timeAccelerationPeriod && i < m_timeEndCruise) {
//				m_time = i*m_clkInMinutes;
//				m_points[i][0] = m_clockSpeed;
//				m_points[i][1] = m_cruiseVelocity;
//			} else if (i > m_timeEndCruise && i < m_timeEnd) {
//				m_time = i*m_clkInMinutes;
//				m_points[i][0] = m_clockSpeed;
//				m_points[i][1] = -m_maxAcceleration*m_time+(m_cruiseVelocity+m_maxAcceleration*m_timeEndCruise);
//			}
//		}
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {		
		Robot.drive.resetEncoders();
		Robot.drive.initializeMotionProfile(GeneratedTalonMP.Points);
	}
	
	@Override 
	protected void execute() {
		System.out.println("254");
		Robot.drive.executeMotionProfile();
	}

	@Override
	protected boolean isFinished() {
		return Robot.drive.profileIsFinished();
	}
	
	@Override
	protected void end() {
		Robot.drive.motionProfileEnd();
	}
	
}
