package org.usfirst.frc.team687.robot.commands.gearintake;

import org.usfirst.frc.team687.robot.constants.GearIntakeConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeDownSpin extends CommandGroup {
	public IntakeDownSpin()	{
		addParallel(new IntakeSetPosition(GearIntakeConstants.kGearIntakeDownPos));
		addSequential(new SpinSpeed(GearIntakeConstants.kGearIntakeSpinVoltage));
	}
}
