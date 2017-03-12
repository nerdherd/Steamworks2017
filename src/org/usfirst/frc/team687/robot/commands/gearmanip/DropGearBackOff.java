package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.commands.TimeoutCommand;
import org.usfirst.frc.team687.robot.commands.drive.DriveTime;
import org.usfirst.frc.team687.robot.commands.drive.ShiftDown;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DropGearBackOff extends CommandGroup{
	
	public DropGearBackOff() {
		addSequential(new ShiftDown());
		addParallel(new SetGearManipTimer(GearManipulationConstants.kGearManipDownPos, 0.5));
		addParallel(new DriveTime(-0.5, 0.5, 1.5));
		addSequential(new TimeoutCommand(0.75));
		addParallel(new SetGearManipTimer(GearManipulationConstants.kGearManipUpPos, 1.25));
	}

}
