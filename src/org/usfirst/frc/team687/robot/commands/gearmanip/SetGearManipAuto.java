package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import edu.wpi.first.wpilibj.command.Command;

public class SetGearManipAuto extends Command {
	
	private boolean m_gearPresent = false;
	
	public SetGearManipAuto() {
		requires(Robot.gearManip);
	}
	
	@Override
	public void execute() {
		if (m_gearPresent) {
			Robot.gearManip.setPos(GearManipulationConstants.kGearManipUpPos);
		} else {
			Robot.gearManip.setPos(GearManipulationConstants.kGearManipMidPos);
		}
		m_gearPresent = Robot.gearManip.gearDetector.get();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
