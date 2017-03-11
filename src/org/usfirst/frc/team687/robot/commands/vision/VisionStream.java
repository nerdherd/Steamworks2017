package org.usfirst.frc.team687.robot.commands.vision;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionStream extends Command {

    public VisionStream() {
    	requires(Robot.vision);
    }

    protected void execute() {
    	Robot.vision.display();
    }

    protected boolean isFinished() {
        return false;
    }
}
