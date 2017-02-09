package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DropGearBackOff extends CommandGroup{
	
	public DropGearBackOff() {
		addParallel(new GearManipSet(GearManipulationConstants.kGearManipDownPos));
		addParallel(new DriveDistance(-6.87));
	}

}
