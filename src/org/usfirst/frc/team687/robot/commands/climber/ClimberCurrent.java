package org.usfirst.frc.team687.robot.commands.climber;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberCurrent extends Command {
	private double m_current;
	
    public ClimberCurrent(double current) {
    	m_current = current;
    	requires(Robot.climber);
    }

    protected void execute() {
    	Robot.climber.setCurrent(m_current);
    }

    protected boolean isFinished() {
        return false;
    }

}
