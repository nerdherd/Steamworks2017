package org.usfirst.frc.team687.robot.commands.gearintake;

import org.usfirst.frc.team687.robot.constants.GearIntakeConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Outtake extends CommandGroup {
	public Outtake()	{
		addParallel(new IntakeSetPosition(GearIntakeConstants.kGearIntakeUpPos));
		addSequential(new SpinSpeed(GearIntakeConstants.kGearOuttakeSpinVoltage));
	}
}
