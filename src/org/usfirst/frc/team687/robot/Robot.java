package org.usfirst.frc.team687.robot;

import org.usfirst.frc.team687.robot.commands.drive.DriveOpenLoop;
import org.usfirst.frc.team687.robot.constants.DriveConstants.DriveMode;
import org.usfirst.frc.team687.robot.subsystems.Climber;
import org.usfirst.frc.team687.robot.subsystems.Conveyor;
import org.usfirst.frc.team687.robot.subsystems.Drive;
import org.usfirst.frc.team687.robot.subsystems.GearManipulation;
import org.usfirst.frc.team687.robot.subsystems.Intake;
import org.usfirst.frc.team687.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	public static OI oi;
	public static Compressor compressor;
	public static PowerDistributionPanel pdp;
	
	public static Climber climber;
	public static Conveyor conveyor;
	public static Drive drive;
	public static Intake intake;
	public static GearManipulation gearManip;
	public static Shooter shooter;
	
	public double mode = 0;
	
	@Override
	public void robotInit() {
		climber = new Climber();
		conveyor = new Conveyor();
		drive = new Drive();
		intake = new Intake();
		shooter = new Shooter();
		gearManip = new GearManipulation();
		
		oi = new OI();
	
		compressor = new Compressor();
		compressor.start();
		
		pdp = new PowerDistributionPanel();
		
		SmartDashboard.putNumber("Drive Mode", mode);
	}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	
    }
    
    public void autonomousPeriodic() {
    	
    }
    
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData("pdp", pdp);
		drive.reportState();
		gearManip.reportState();
    }
}
