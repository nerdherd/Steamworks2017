package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.lib.NerdyPID;
import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.GearConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Set gear manipulator position
 * 
 * @author Ted
 *
 */

public class SetGearPosition extends Command {
	
	private double m_desired;
	private NerdyPID m_pidController;
	
	public SetGearPosition(double desired) {
		m_desired = desired;
		
		requires(Robot.gearManipulator);
	}
	
	@Override
	protected void initialize() {
		m_pidController = new NerdyPID(GearConstants.kP, GearConstants.kI, GearConstants.kD);
		m_pidController.setDesired(m_desired);
		
		Robot.gearManipulator.resetSensors();
	}

	@Override
	protected void execute() {
		Robot.gearManipulator.setPower(m_pidController.calculate(Robot.gearManipulator.getPosition()));
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(GearConstants.kOpenPos - Robot.gearManipulator.getPosition()) < 5;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
