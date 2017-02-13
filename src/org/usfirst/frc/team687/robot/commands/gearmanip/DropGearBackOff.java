package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.commands.drive.DriveTime;
import org.usfirst.frc.team687.robot.commands.drive.ShiftDown;
import org.usfirst.frc.team687.robot.commands.drive.ShiftUp;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DropGearBackOff extends CommandGroup{
	
	public DropGearBackOff() {
		addSequential(new ShiftDown());
		addParallel(new GearManipSetTimer(GearManipulationConstants.kGearManipDownPos, 2));
		addSequential(new DriveTime(-0.4, -0.4, 2));
		addParallel(new GearManipSetNoTimer(GearManipulationConstants.kGearManipUpPos));
		addParallel(new ShiftUp());
	}

}
