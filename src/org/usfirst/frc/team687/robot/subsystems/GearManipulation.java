package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearManipulation extends Subsystem {
	private CANTalon m_gearManip;
	
	public GearManipulation() {
		m_gearManip = new CANTalon(RobotMap.gearManipPort);
		m_gearManip.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		m_gearManip.setP(GearManipulationConstants.kGearManipP);
		m_gearManip.setI(GearManipulationConstants.kGearManipI);
		m_gearManip.setD(GearManipulationConstants.kGearManipD);
		m_gearManip.setF(GearManipulationConstants.kGearManipF);
	}
	
	public void setPercentVoltage(double pow) {
		m_gearManip.changeControlMode(TalonControlMode.PercentVbus);
		m_gearManip.set(pow);
	}
	
	public void setPos(double pos) {
		m_gearManip.changeControlMode(TalonControlMode.Position);
		m_gearManip.set(pos);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
}
