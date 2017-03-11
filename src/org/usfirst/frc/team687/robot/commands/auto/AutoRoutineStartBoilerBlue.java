package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team687.robot.commands.drive.DriveTime;
import org.usfirst.frc.team687.robot.commands.drive.DriveTurnToAngle;
import org.usfirst.frc.team687.robot.commands.drive.ShiftDown;
import org.usfirst.frc.team687.robot.commands.gearmanip.DropGearBackOff;
import org.usfirst.frc.team687.robot.commands.gearmanip.SetGearManipNoTimer;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRoutineStartBoilerBlue extends CommandGroup {
	public AutoRoutineStartBoilerBlue() {
		addParallel(new SetGearManipNoTimer(GearManipulationConstants.kGearManipUpPos));
		addSequential(new ShiftDown());
		addSequential(new DriveDistance(7, 1000, 750, 0));
		addSequential(new DriveTurnToAngle(-60, 1));
		addSequential(new DriveDistance(4.5, 1000, 750, 0));
		// WIGGLE
		addSequential(new DriveTime(-0.25, -0.25, 0.125));
		addSequential(new DriveTime(0.25, 0.25, 0.125));
		addSequential(new DropGearBackOff());
		addSequential(new DriveTurnToAngle(0, 2));
		addSequential(new DriveTime(0.5, -0.5, 1));
	}
}
