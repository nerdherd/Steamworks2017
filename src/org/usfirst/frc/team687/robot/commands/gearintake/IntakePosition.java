package org.usfirst.frc.team687.robot.commands.gearintake;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.GearIntakeConstants;

import edu.wpi.first.wpilibj.command.Command;

public class IntakePosition extends Command {
	
	private double m_desired;
	
	public IntakePosition(double desired) {
		requires(Robot.gearIntake);
		m_desired = desired;
	}
	
	@Override
	public void execute() {
		Robot.gearIntake.setPos(m_desired);
	}
	
	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.gearIntake.getPos() - m_desired) <= GearIntakeConstants.kGearIntakeTolerance;
	}

}
