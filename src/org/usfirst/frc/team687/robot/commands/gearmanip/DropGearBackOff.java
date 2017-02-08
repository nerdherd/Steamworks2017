package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.commands.drive.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DropGearBackOff extends CommandGroup{
	
	public DropGearBackOff() {
		addParallel(new GearManipDown());
		addParallel(new DriveDistance(-6.87));
	}

}
