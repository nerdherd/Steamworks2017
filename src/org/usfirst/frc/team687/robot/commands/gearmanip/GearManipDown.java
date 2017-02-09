package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import edu.wpi.first.wpilibj.command.Command;

public class GearManipDown extends Command {
	
	public GearManipDown() {
		requires(Robot.gearManip);
	}
	
	@Override
	public void execute() {
		Robot.gearManip.setPos(GearManipulationConstants.kGearManipDownPos);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
