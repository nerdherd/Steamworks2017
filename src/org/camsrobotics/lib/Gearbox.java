package org.camsrobotics.lib;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

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
	
	public Gearbox(CANTalon encoderTalon, CANTalon talon1, CANTalon talon2) {
		m_encoderTalon = encoderTalon;
		m_talon1 = talon1;
		m_talon2 = talon2;
		
		m_talon1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		m_talon2.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		m_encoderTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
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
	
	public double getSpeed() {
		return m_encoderTalon.getSpeed();
	}
	
	public double getPosition() {
		return m_encoderTalon.getPosition();
	}
	
	public void exectuteProfile(double maxVelocity, double maxAcceleration, double revolutions) {
		//TODO
	}
	
}