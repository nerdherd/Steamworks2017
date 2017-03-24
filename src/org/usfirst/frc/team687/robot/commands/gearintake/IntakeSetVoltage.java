package org.usfirst.frc.team687.robot.commands.gearintake;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeSetVoltage extends Command {
	
	private double m_pow;
	
	public IntakeSetVoltage(double pow) {
		requires(Robot.gearIntake);
		m_pow = pow;
	}
	
	@Override
	public void execute() {
		Robot.gearIntake.setArticVoltage(m_pow);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
