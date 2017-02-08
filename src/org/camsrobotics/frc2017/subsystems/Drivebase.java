package org.camsrobotics.frc2017.subsystems;

import org.camsrobotics.lib.Gearbox;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;

public class Drivebase {
	private Gearbox m_left;
	private Gearbox m_right;
	private AHRS m_nav;
	
	public Drivebase(Gearbox left, Gearbox right) {
		m_left = left;
		m_right = right;
		m_nav = new AHRS(SerialPort.Port.kMXP);
	}
	
	public void driveOpenLoop(double leftPow, double rightPow) {
		m_right.setPercentVBus(rightPow);
		m_left.setPercentVBus(leftPow);
	}
	
	public void driveClosedLoop(double leftPow, double rightPow) {
		m_right.setSpeedFromJoystick(rightPow);
		m_left.setSpeedFromJoystick(leftPow);
	}
}
