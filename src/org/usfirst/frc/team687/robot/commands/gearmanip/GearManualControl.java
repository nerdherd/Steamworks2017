package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import edu.wpi.first.wpilibj.command.Command;

public class GearManualControl extends Command {
	
	public GearManualControl() {
		requires(Robot.gearManip);
	}
	
	@Override
	public void execute() {
		Robot.gearManip.setPos(GearManipulationConstants.kGearManipMidPos);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}