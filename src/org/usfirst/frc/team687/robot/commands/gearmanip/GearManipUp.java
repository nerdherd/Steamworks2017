package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import edu.wpi.first.wpilibj.command.Command;

public class GearManipUp extends Command {
	
	public GearManipUp() {
		requires(Robot.gearManip);
	}
	
	@Override
	public void execute() {
		Robot.gearManip.setPos(GearManipulationConstants.kGearManipUpPos);
	}
	
	@Override
	protected boolean isFinished() {
		return (Robot.gearManip.getPos()-GearManipulationConstants.kGearManipUpPos < GearManipulationConstants.kGearManipPosTolerance);
	}

}
