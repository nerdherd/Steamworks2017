package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Gear manipulator susbsystem
 * 
 * @author Ted
 *
 */

public class GearManipulator extends Subsystem {
	
	private CANTalon m_gears;
	
	protected enum GearState {
		OPEN, SECURE
	}
	protected GearState m_state;
	
	public GearManipulator() {
		m_gears = new CANTalon(RobotMap.gearManip);
		m_gears.changeControlMode(TalonControlMode.PercentVbus);
		m_gears.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void setPower(double power) {
		m_gears.set(power);
	}
	
	public void setStateSecure(boolean ifSecure) {
		if (ifSecure == true) {
			m_state = GearState.SECURE;
		} else {
			m_state = GearState.OPEN;
		}
	}
	
	public boolean isSecure() {
		return m_state == GearState.SECURE;
	}
	
	public double getPosition() {
		return m_gears.getPosition();
	}
	
	public void resetSensors() {
		m_gears.reset();
	}

	public void reportToSmartDashboard() {
		SmartDashboard.putBoolean("Gear Manipulator Secure", isSecure());
		SmartDashboard.putNumber("Gear Encoder Position", getPosition());
	}
	
}
