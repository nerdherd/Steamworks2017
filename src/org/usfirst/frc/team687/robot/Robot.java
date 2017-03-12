package org.usfirst.frc.team687.robot;

import org.usfirst.frc.team687.robot.commands.SystemsCheck;
import org.usfirst.frc.team687.robot.commands.auto.*;
import org.usfirst.frc.team687.robot.commands.drive.DriveTurnToAngle;
import org.usfirst.frc.team687.robot.constants.DriveConstants;
import org.usfirst.frc.team687.robot.subsystems.Climber;
import org.usfirst.frc.team687.robot.subsystems.Drive;
import org.usfirst.frc.team687.robot.subsystems.GearManipulation;
import org.usfirst.frc.team687.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	public static OI oi;
	public static Compressor compressor;
	public static PowerDistributionPanel pdp;
	public static Vision vision;
	
	public static Climber climber;
	public static Drive drive;
	public static GearManipulation gearManip;
	
	final String autoRedMiddle = "RedMiddle";
	final String autoRedBoiler = "RedBoiler";
	final String autoRedFeeder = "RedFeeder";
	final String autoBlueMiddle = "BlueMiddle";
	final String autoBlueBoiler = "BlueBoiler";
	final String autoBlueFeeder = "BlueFeeder";
	final String autoDoNothing = "DoNothing";

	SendableChooser<String> autoChooser = new SendableChooser<>();
	String autoChosen = "";
	
	Command autocmd;
	
	@Override
	public void robotInit() {
		climber = new Climber();
		drive = new Drive();
		drive.resetEncoders();
		drive.resetGyro();
		gearManip = new GearManipulation();
		vision = new Vision();		

		oi = new OI();
	
		compressor = new Compressor();
		compressor.start();
		
		pdp = new PowerDistributionPanel();
		
		autoChooser.addDefault("Do Nothing", autoDoNothing);
		autoChooser.addObject("Red Middle", autoRedMiddle);	
		autoChooser.addObject("Red Boiler", autoRedBoiler);
		autoChooser.addObject("Red Feeder", autoRedFeeder);
		autoChooser.addObject("Blue Middle", autoBlueMiddle);
		autoChooser.addObject("Blue Boiler", autoBlueBoiler);
		autoChooser.addObject("Blue Feeder", autoBlueFeeder);
		
		SmartDashboard.putData("Autonomous Selection", autoChooser);
		SmartDashboard.putNumber("Distance Feeder Auto 1", DriveConstants.kDistanceFeederAuto1);
		SmartDashboard.putNumber("Distance Feeder Auto 2", DriveConstants.kDistanceFeederAuto2);
		SmartDashboard.putNumber("Distance Mid Auto", DriveConstants.kDistanceMidAuto);
	}
	
	@Override
    public void autonomousInit() {
		Scheduler.getInstance().removeAll();
		autoChosen = autoChooser.getSelected();
		System.out.println(autoChosen);
		DriveConstants.kDistanceMidAuto = SmartDashboard.getNumber("Distance Mid Auto", DriveConstants.kDistanceMidAuto);
		DriveConstants.kDistanceFeederAuto1 = SmartDashboard.getNumber("Distance Feeder Auto 1", DriveConstants.kDistanceFeederAuto1);
		DriveConstants.kDistanceFeederAuto2 = SmartDashboard.getNumber("Distance Feeder Auto 2", DriveConstants.kDistanceFeederAuto2);
		drive.resetGyro();
		drive.resetEncoders();
		switch(autoChosen)	{
		case autoRedMiddle:
			autocmd = new AutoRoutineStartMidRed();
			break;
		case autoRedBoiler:
			autocmd = new AutoRoutineStartBoilerRed();
			break;
		case autoRedFeeder:
			autocmd = new AutoRoutineStartLoadingRed();
			break;
		case autoBlueMiddle:
			autocmd = new AutoRoutineStartMidBlue();
			break;
		case autoBlueBoiler:
			autocmd = new AutoRoutineStartBoilerBlue();
			break;
		case autoBlueFeeder:
			autocmd = new AutoRoutineStartLoadingBlue();
			break;
		case autoDoNothing:
		default:
			autocmd = new DoNothingAuto();
		}
		autocmd.start();
    }
    
	@Override
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    	drive.reportState();
    }
    
	@Override
	public void teleopInit()	{
		Scheduler.getInstance().removeAll();
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData("pdp", pdp);
		drive.reportState();
		gearManip.reportState();
		climber.reportState();
    }
	
	Command testCommand;
	@Override
	public void testInit()	{
		Scheduler.getInstance().removeAll();
		testCommand = new SystemsCheck();
		testCommand.start();
	}
	
	@Override
	public void testPeriodic()	{
		LiveWindow.setEnabled(false);
		Scheduler.getInstance().run();
	}
}
