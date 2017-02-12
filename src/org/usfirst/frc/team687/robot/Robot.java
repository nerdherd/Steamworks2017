package org.usfirst.frc.team687.robot;

import org.usfirst.frc.team687.robot.commands.drive.DriveOpenLoop;
import org.usfirst.frc.team687.robot.commands.drive.DriveTurnToAngle;
import org.usfirst.frc.team687.robot.constants.DriveConstants.DriveMode;
import org.usfirst.frc.team687.robot.subsystems.Climber;
import org.usfirst.frc.team687.robot.subsystems.Drive;
import org.usfirst.frc.team687.robot.subsystems.GearManipulation;
import org.usfirst.frc.team687.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	public static OI oi;
	public static Compressor compressor;
	public static PowerDistributionPanel pdp;
	
	public static Climber climber;
	public static Drive drive;
	public static Intake intake;
	public static GearManipulation gearManip;
	
	@Override
	public void robotInit() {
		climber = new Climber();
		drive = new Drive();
		intake = new Intake();
		gearManip = new GearManipulation();
		
		oi = new OI();
	
		compressor = new Compressor();
		compressor.start();
		
		pdp = new PowerDistributionPanel();
	}

    public void autonomousInit() {
    	Command cmd = new DriveTurnToAngle(90);
    	cmd.start();
    }
    
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }
    
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData("pdp", pdp);
		drive.reportState();
		gearManip.reportState();
    }
}
