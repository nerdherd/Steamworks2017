package org.usfirst.frc.team687.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Extend to run a command that expires based on time
 * 
 * @author wsh32
 *
 */
public class TimeoutCommand extends Command {
	
	protected double startTime;
	protected double endTime;
	private double m_time;
	
    public TimeoutCommand(double time) {
    	m_time = time;
    }
    
    @Override
    protected void initialize() {
    	init();
    }
    
    protected void init()	{
    	startTime = Timer.getFPGATimestamp();
    	endTime = startTime + m_time;
    }

    /**
     * In regular timeout tasks they will expire as soon as the time is out
     */
    @Override
    protected boolean isFinished() {
    	return timeout();
    }
    
    /**
     * Call within an extended class to see 
     * 
     * @return true when the time has expired.
     */
    protected boolean timeout()	{
    	return Timer.getFPGATimestamp() > endTime;
    }

}
