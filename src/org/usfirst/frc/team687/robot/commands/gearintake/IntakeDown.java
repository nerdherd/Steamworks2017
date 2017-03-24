package org.usfirst.frc.team687.robot.commands.gearintake;

import org.usfirst.frc.team687.robot.constants.GearIntakeConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeDown extends CommandGroup {
	public IntakeDown()	{
		addSequential(new IntakePosition(GearIntakeConstants.kGearIntakeDownPos));
		addParallel(new IntakePositionHold(GearIntakeConstants.kGearIntakeDownPos));
	}
}
