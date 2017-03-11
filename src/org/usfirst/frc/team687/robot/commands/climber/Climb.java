package org.usfirst.frc.team687.robot.commands.climber;

import org.usfirst.frc.team687.robot.constants.ClimberConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Climb extends CommandGroup {
	public Climb() {
		addSequential(new ClimberSet(ClimberConstants.kClimberVoltage));
		addSequential(new ClimberCurrent(ClimberConstants.kHoldCurrent));
	}
}
