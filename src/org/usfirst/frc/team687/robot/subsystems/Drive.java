package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.lib.Gearbox;
import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.commands.TankDrive;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive subsystem
 *
 * @author Ted
 *
 */

public class Drive extends Subsystem {
	
	private Gearbox m_leftGearbox;
	private Gearbox m_rightGearbox;
	
	private DoubleSolenoid m_shifter;
	private CANTalon m_l1, m_l2, m_l3, m_r1, m_r2, m_r3;
	private AHRS m_nav;
	
	public Drive() {
		super();
		
		m_l1 = new CANTalon(RobotMap.lDrive1);
		m_l2 = new CANTalon(RobotMap.lDrive2);
		m_l3 = new CANTalon(RobotMap.lDrive3);
		m_r1 = new CANTalon(RobotMap.lDrive1);
		m_r2 = new CANTalon(RobotMap.lDrive2);
		m_r3 = new CANTalon(RobotMap.lDrive3);
		
		m_shifter = new DoubleSolenoid(RobotMap.shifter1, RobotMap.shifter2);
		
		m_leftGearbox = new Gearbox(m_l1, m_l2, m_l3);
		m_rightGearbox = new Gearbox(m_r1, m_r2, m_r3);
		
		m_nav = new AHRS(RobotMap.AHRSPort);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}
	
	public void setPower(double lpow, double rpow) {
		m_leftGearbox.setPercentVBus(lpow);
		m_rightGearbox.setPercentVBus(rpow);
	}
	
	public void setSpeed(double lspeed, double rspeed) {
		m_leftGearbox.setSpeed(lspeed);
		m_rightGearbox.setSpeed(rspeed);
	}
	
	public void shiftUp() {
		m_shifter.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void shiftDown() {
		m_shifter.set(DoubleSolenoid.Value.kForward);
	}
	
	public double getLeftSpeed() {
		return m_leftGearbox.getSpeed();
	}
	
	public double getRightSpeed() {
		return m_rightGearbox.getSpeed();
	}
	
	public double getLeftPos() {
		return m_leftGearbox.getPosition();
	}
	
	public double getRightPos() {
		return m_rightGearbox.getPosition();
	}
	
	public double getPos() {
		return (getLeftPos() + getRightPos() / 2);
	}
	
	public double getYaw() {
		return m_nav.getYaw();
	}
	
	public void executeProfile(double v_max, double accel_max, double distance) {
		m_leftGearbox.executeProfile(v_max, accel_max, distance);
		m_rightGearbox.executeProfile(v_max, accel_max, distance);
	}
	
	public void resetProfile() {
		m_leftGearbox.resetProfile();
		m_rightGearbox.resetProfile();
	}
	
	public void resetSensors() {
		m_leftGearbox.resetEncoders();
		m_rightGearbox.resetEncoders();
		
		m_nav.reset();
	}
	
    public void stopDrive() {
    	setPower(0, 0);
    	resetSensors();
    }
	
	public void reportToSmartDashboard() {
		SmartDashboard.putNumber("Left Speed", getLeftSpeed());
		SmartDashboard.putNumber("Right Gearbox", getRightSpeed());
		SmartDashboard.putNumber("Left Position", getLeftPos());
		SmartDashboard.putNumber("Right Position", getRightPos());
		
		SmartDashboard.putNumber("Yaw", getYaw());
	}
}

