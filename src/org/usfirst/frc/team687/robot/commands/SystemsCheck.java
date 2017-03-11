package org.usfirst.frc.team687.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team687.robot.commands.drive.*;
import org.usfirst.frc.team687.robot.commands.gearmanip.*;
import org.usfirst.frc.team687.robot.constants.GearManipulationConstants;

/**
 *
 */
public class SystemsCheck extends CommandGroup {

    public SystemsCheck() {
    	addParallel(new SetGearManipNoTimer(GearManipulationConstants.kGearManipUpPos));
    	
    	addSequential(new DriveTime(1, 1, 2));
    	addSequential(new TimeoutCommand(0.5));
    	addSequential(new DriveTime(-1, -1, 2));
    	addSequential(new TimeoutCommand(1));
    	addSequential(new ShiftUp());
    	addSequential(new TimeoutCommand(0.5));
    	addSequential(new ShiftDown());
    	addSequential(new TimeoutCommand(1));
    	
    	addSequential(new SetGearManipTimer(GearManipulationConstants.kGearManipDownPos, 1));
    	addSequential(new SetGearManipTimer(GearManipulationConstants.kGearManipUpPos, 2));
    }
}
