package org.camsrobotics.lib;

/**
 * Generate a motion profile for use with Talon SRX
 * @author Mike
 *
 */
public class MotionProfile {
	
	private double m_timeNoLimit;
	private double m_maxVelocityNoLimit;
	private double m_cruiseVelocity;
	private double m_timeAccelerationPeriod;
	private double m_distanceAccelerationPeriod;
	private double m_cruiseDistance;
	private double m_timeEndCruise;
	private double m_timeEnd;
	
	private double m_maxVelocity;
	private double m_maxAcceleration;
	private double m_distance;
	
	public MotionProfile(double maxVelocity, double maxAcceleration, double distance) {
		/**
		 * @param maxVelocity: Maximum allowed velocity in rev/ms
		 * @param maxAcceleration: Maximum allowed acceleration in rev/ms/ms
		 * @param distance: Total number of revolutions 
		 * TODO may want to make this in ft and add a seperate parameter for wheel diameter 
		 */
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
	}
	//TODO get points
	//meeting ended lol
}
