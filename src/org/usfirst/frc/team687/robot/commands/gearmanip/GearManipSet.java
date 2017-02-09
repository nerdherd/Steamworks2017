package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;
import org.usfirst.frc.team687.robot.constants.IntakeConstants;

import edu.wpi.first.wpilibj.command.Command;

public class GearManipSet extends Command {
	
	private double m_desired;
	
	public GearManipSet(double desired) {
		requires(Robot.gearManip);
		m_desired = desired;
	}
	
	@Override
	public void execute() {
		Robot.gearManip.setPos(m_desired);
	}
	
	@Override
	protected boolean isFinished() {
		return (Robot.gearManip.getPos()-m_desired < GearManipulationConstants.kGearManipPosTolerance);
	}

}
