package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.drive.*;
import org.usfirst.frc.team687.robot.commands.gearmanip.*;
import org.usfirst.frc.team687.robot.constants.DriveConstants;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRoutineStartMidRed extends CommandGroup {
	public AutoRoutineStartMidRed() {
		addParallel(new SetGearManipNoTimer(GearManipulationConstants.kGearManipUpPos));
		addSequential(new ShiftDown());
		addSequential(new DriveDistanceTimeout(DriveConstants.kDistanceMidAuto, 1000, 650, DriveConstants.kDriveStraightP, 5));
		// WIGGLE
		addSequential(new DriveTime(0.25, 0.25, 0.125));
		addSequential(new DriveTime(-0.25, -0.25, 0.125));
		addSequential(new DropGearBackOff());
		addSequential(new DriveTime(0.5, 0.5, .75));
	}
}
