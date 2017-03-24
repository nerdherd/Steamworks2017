package org.usfirst.frc.team687.robot.commands.gearintake;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinSpeed extends Command {
	
	private double m_desired;
	
	public SpinSpeed(double pow) {
		requires(Robot.gearIntake);
		m_desired = pow;
	}
	
	@Override
	public void execute() {
		Robot.gearIntake.setSpinVoltage(m_desired);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
