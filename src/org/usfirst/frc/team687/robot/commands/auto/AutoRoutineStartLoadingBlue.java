package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team687.robot.commands.drive.DriveDistanceTimeout;
import org.usfirst.frc.team687.robot.commands.drive.DriveTime;
import org.usfirst.frc.team687.robot.commands.drive.DriveTurnToAngle;
import org.usfirst.frc.team687.robot.commands.drive.ShiftDown;
import org.usfirst.frc.team687.robot.commands.gearintake.IntakeSetPosition;
import org.usfirst.frc.team687.robot.commands.gearintake.Outtake;
import org.usfirst.frc.team687.robot.commands.gearmanip.DropGearBackOff;
import org.usfirst.frc.team687.robot.commands.gearmanip.SetGearManipNoTimer;
import org.usfirst.frc.team687.robot.constants.DriveConstants;
import org.usfirst.frc.team687.robot.constants.GearIntakeConstants;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRoutineStartLoadingBlue extends CommandGroup {
	
	public AutoRoutineStartLoadingBlue() {
		addParallel(new SetGearManipNoTimer(GearManipulationConstants.kGearManipUpPos));
		addSequential(new ShiftDown());
		//8 ft 0.5 in
		addSequential(new DriveDistance(DriveConstants.kDistanceFeederAuto1, 1000, 750, 0));
		addSequential(new DriveTurnToAngle(DriveConstants.kAngleFeederAutoRed, 1));
		addSequential(new DriveDistanceTimeout(DriveConstants.kDistanceFeederAuto2, 1000, 750, 0, 2));
		// WIGGLE
		addSequential(new DriveTime(0.25, 0.25, 0.125));
		addSequential(new DriveTime(-0.25, -0.25, 0.125));
		addSequential(new IntakeSetPosition(GearIntakeConstants.kGearIntakeUpPos));
		addSequential(new Outtake());
	
	}
}
