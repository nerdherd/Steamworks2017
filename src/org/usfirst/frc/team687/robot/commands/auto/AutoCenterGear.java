package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.DriveDistance;
import org.usfirst.frc.team687.robot.constants.DriveConstants;
import org.usfirst.frc.team687.robot.constants.DriveConstants.Path;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Center gear autonomous
 *
 * @author Ted
 *
 */

public class AutoCenterGear extends CommandGroup {
	
	public AutoCenterGear() {
		Path path = DriveConstants.Path.CenterGear;
		addSequential(new DriveDistance(DriveConstants.getFirstSegment(path)));
	}

}
