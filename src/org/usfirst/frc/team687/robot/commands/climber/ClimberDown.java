package org.usfirst.frc.team687.robot.commands.climber;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberDown extends Command{
		
	public ClimberDown() {
		requires(Robot.climber);
	}
	
	@Override
	public void execute() {
		Robot.climber.setPercentVoltage(-1);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
		
}
