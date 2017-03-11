package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.commands.gearmanip.SetGearManipAuto;
import org.usfirst.frc.team687.robot.commands.gearmanip.SetGearManipNoTimer;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearManipulation extends Subsystem {
	private CANTalon m_gearManip;
	public DigitalInput gearDetector;
	
	public GearManipulation() {
		m_gearManip = new CANTalon(RobotMap.gearManipPort);
		m_gearManip.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		m_gearManip.setP(GearManipulationConstants.kGearManipP);
		m_gearManip.setI(GearManipulationConstants.kGearManipI);
		m_gearManip.setD(GearManipulationConstants.kGearManipD);
		m_gearManip.setF(GearManipulationConstants.kGearManipF);
		gearDetector = new DigitalInput(RobotMap.gearDetectorPort);
	}
	
	public void setPercentVoltage(double pow) {
		m_gearManip.changeControlMode(TalonControlMode.PercentVbus);
		m_gearManip.set(pow);
	}
	
	public void setPos(double pos) {
		m_gearManip.changeControlMode(TalonControlMode.Position);
		m_gearManip.set(pos);
	}
	
	public double getPos() {
		return m_gearManip.getPosition();
	}
	
	public void manualControl() {
		setPercentVoltage(Robot.oi.getArticY());
	}
	
	public void reportState() {
		SmartDashboard.putNumber("Gear Manip Pos", m_gearManip.getPosition());
		SmartDashboard.putBoolean("Gear Present", gearDetector.get());
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SetGearManipNoTimer(GearManipulationConstants.kGearManipUpPos));
	}
}
