package org.usfirst.frc.team687.robot.commands.gearintake;

import org.usfirst.frc.team687.robot.constants.GearIntakeConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeTuckRetain extends CommandGroup {
	public IntakeTuckRetain()	{
		addSequential(new IntakePosition(GearIntakeConstants.kGearIntakeUpPos));
		addParallel(new IntakePositionHold(GearIntakeConstants.kGearIntakeUpPos));
		addSequential(new SpinSpeed(GearIntakeConstants.kGearIntakeSpinHoldVoltage));
	}
}
