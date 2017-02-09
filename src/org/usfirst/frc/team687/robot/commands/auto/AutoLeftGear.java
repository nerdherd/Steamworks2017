package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.DriveDistance;
import org.usfirst.frc.team687.robot.commands.DriveTurnToAngle;
import org.usfirst.frc.team687.robot.constants.DriveConstants;
import org.usfirst.frc.team687.robot.constants.DriveConstants.Path;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Left gear autonomous
 *
 * @author Ted
 *
 */

public class AutoLeftGear extends CommandGroup{

	public AutoLeftGear() {
		Path path = DriveConstants.Path.LeftGear;
		addSequential(new DriveDistance(DriveConstants.getFirstSegment(path)));
		addSequential(new DriveTurnToAngle(DriveConstants.getFirstAngle(path)));
		addSequential(new DriveDistance(DriveConstants.getSecondSegment(path)));
	}
}
