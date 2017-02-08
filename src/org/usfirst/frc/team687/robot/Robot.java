package org.usfirst.frc.team687.robot;

import org.usfirst.frc.team687.robot.subsystems.*;
import org.usfirst.frc.team687.robot.commands.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *                          
 * _._ _..._ .-',     _.._(`)
 *'-. `     '  /-._.-'    ',/
 *   )         \            '.
 *  / _    _    |             \
 * |  a    a    /              |
 * \   .-.                     ;  
 *  '-('' ).-'       ,'       ;
 *     '-;           |      .'
 *        \           \    /
 *        | 7  .__  _.-\   \
 *        | |  |  ``/  /`  /
 *       /,_|  |   /,_/   /
 *          /,_/      '`-'
 * 
 * @author Ted
 *
 */

public class Robot extends IterativeRobot {

	public static Drive drive;
	public static GearManipulator gearManipulator;
	
	public static PowerDistributionPanel pdp;
	public static Compressor compressor;
	
	public static OI oi;
    public static SendableChooser<Command> autoProgram;

    
    public void robotInit() {
		oi = new OI();
		
		drive = new Drive();
		gearManipulator = new GearManipulator();
		
		pdp = new PowerDistributionPanel();
		compressor = new Compressor();
		compressor.start();
        
        SmartDashboard.putData(drive);
        SmartDashboard.putData(gearManipulator);
    }
	
    public void disabledInit() {

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        autoProgram = new SendableChooser<Command>();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        drive.reportToSmartDashboard();
        gearManipulator.reportToSmartDashboard();
    }

    public void teleopInit() {
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        drive.reportToSmartDashboard();
        gearManipulator.reportToSmartDashboard();

    }
    
    public void testPeriodic() {
        LiveWindow.run();
        
        drive.reportToSmartDashboard();
        gearManipulator.reportToSmartDashboard();
    }
}
