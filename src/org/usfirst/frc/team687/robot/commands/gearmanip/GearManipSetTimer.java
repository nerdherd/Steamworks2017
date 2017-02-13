package org.usfirst.frc.team687.robot.commands.gearmanip;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.commands.TimeoutCommand;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;
import org.usfirst.frc.team687.robot.constants.IntakeConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class GearManipSetTimer extends TimeoutCommand {
	
	private double m_desired;
	private double m_timeout;
	private Timer m_timer;
	
	public GearManipSetTimer(double desired, double timeout) {
		super(timeout);
		requires(Robot.gearManip);
		m_desired = desired;
	}
	
	@Override
	protected void initialize() {
		m_timer.start();
	}
	
	@Override
	public void execute() {
		Robot.gearManip.setPos(m_desired);
	}
	
}
