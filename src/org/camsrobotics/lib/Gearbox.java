package org.camsrobotics.lib;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Notifier;

/**
 * Gearbox class for use with 3x CANTalons
 * @author mike
 *
 */
public class Gearbox {
	
	private CANTalon m_encoderTalon;
	private CANTalon m_talon1;
	private CANTalon m_talon2;
	private DoubleSolenoid m_shifter;
	
	private double speed;
	private double m_maxVelocity;
	private double m_maxAcceleration;
	
	private MotionProfile m_profile;
	
	private double[][] m_points;
	private double m_duration;
	
	private int kIntervalSize = 50;
	
	class PeriodicRunnable implements java.lang.Runnable {
	    public void run() { 
	    	m_encoderTalon.processMotionProfileBuffer();    
	    }
	}
	private Notifier m_notifier = new Notifier(new PeriodicRunnable());
	
	public Gearbox(CANTalon encoderTalon, CANTalon talon1, CANTalon talon2, double P, double I, double D, double F, double maxVelocity, double maxAcceleration, DoubleSolenoid shifter) {
		m_encoderTalon = encoderTalon;
		m_talon1 = talon1;
		m_talon2 = talon2;
		m_shifter = shifter;
		
		m_encoderTalon.setP(P);
		m_encoderTalon.setI(I);
		m_encoderTalon.setD(D);
		m_encoderTalon.setF(F);
		m_encoderTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		m_maxVelocity = maxVelocity;
		m_maxAcceleration = maxAcceleration;
	}
	
	public void setPercentVBus(double pow) {
		m_encoderTalon.changeControlMode(TalonControlMode.PercentVbus);
		m_talon1.changeControlMode(TalonControlMode.PercentVbus);
		m_talon2.changeControlMode(TalonControlMode.PercentVbus);
		
		m_encoderTalon.set(pow);
		m_talon1.set(pow);
		m_talon2.set(pow);
	}
	
	public void setSpeed(double speed) {
		m_encoderTalon.changeControlMode(TalonControlMode.Speed);
		m_talon1.changeControlMode(TalonControlMode.Follower);
		m_talon2.changeControlMode(TalonControlMode.Follower);
		
		m_encoderTalon.set(speed);
		m_talon1.set(m_encoderTalon.getDeviceID());
		m_talon2.set(m_encoderTalon.getDeviceID());
	}
	
	public void setSpeedFromJoystick(double pow) {
		speed = m_maxVelocity*pow;
		setSpeed(speed);
	}
	
	public double getSpeed() {
		return m_encoderTalon.getSpeed();
	}
	
	public double getPosition() {
		return m_encoderTalon.getPosition();
	}
	
	public void exectuteProfile(double maxVelocity, double maxAcceleration, double revolutions) {
		m_encoderTalon.changeControlMode(TalonControlMode.MotionProfile);
		m_talon1.changeControlMode(TalonControlMode.Follower);
		m_talon2.changeControlMode(TalonControlMode.Follower);
		
		resetProfile();
		
		m_profile = new MotionProfile(maxVelocity, maxAcceleration, revolutions);
		m_points = m_profile.getPoints(kIntervalSize);
		m_duration = m_points[0][0];
		
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();
		for (int i=0; i < kIntervalSize; i++) {
			point.velocity = m_points[0][i];
			point.timeDurMs = (int) m_points[i][0];
			point.velocityOnly = true;
			
			m_encoderTalon.pushMotionProfileTrajectory(point);
		}
		m_encoderTalon.set(CANTalon.SetValueMotionProfile.Enable.value);
		m_talon1.set(m_encoderTalon.getDeviceID());
		m_talon2.set(m_encoderTalon.getDeviceID());
		
		m_encoderTalon.changeMotionControlFramePeriod((int) m_duration);
		m_notifier.startPeriodic((int) m_duration/1000);
		
	}
	
	public void exectuteProfile(double revolutions) {
		m_encoderTalon.changeControlMode(TalonControlMode.MotionProfile);
		m_talon1.changeControlMode(TalonControlMode.Follower);
		m_talon2.changeControlMode(TalonControlMode.Follower);
		
		resetProfile();
		
		m_profile = new MotionProfile(m_maxVelocity, m_maxAcceleration, revolutions);
		m_points = m_profile.getPoints(kIntervalSize);
		m_duration = m_points[0][0];
		
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();
		for (int i=0; i < kIntervalSize; i++) {
			point.velocity = m_points[0][i];
			point.timeDurMs = (int) m_points[i][0];
			point.velocityOnly = true;
			
			m_encoderTalon.pushMotionProfileTrajectory(point);
		}
		m_encoderTalon.set(CANTalon.SetValueMotionProfile.Enable.value);
		m_talon1.set(m_encoderTalon.getDeviceID());
		m_talon2.set(m_encoderTalon.getDeviceID());
		
		m_encoderTalon.changeMotionControlFramePeriod((int) m_duration);
		m_notifier.startPeriodic((int) m_duration/1000);
		
	}
	
	private void resetProfile() {
		m_encoderTalon.clearMotionProfileTrajectories();
	}
	
	public void shiftUp() {
		if (m_shifter != null) {
			m_shifter.set(DoubleSolenoid.Value.kForward);
		}
	}
	
	public void shiftDown() {
		if (m_shifter != null) {
			m_shifter.set(DoubleSolenoid.Value.kReverse);
		}
	}
}