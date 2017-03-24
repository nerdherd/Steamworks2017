package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team687.robot.commands.auto.*;
import org.usfirst.frc.team687.robot.commands.climber.*;
import org.usfirst.frc.team687.robot.commands.drive.*;
import org.usfirst.frc.team687.robot.commands.gearmanip.*;
import org.usfirst.frc.team687.robot.commands.gearintake.*;
//import org.usfirst.frc.team687.robot.commands.vision.VisionStream;
import org.usfirst.frc.team687.robot.constants.*;


public class OI {
	public Joystick driveLeftJoy;
	public Joystick driveRightJoy;
	public Joystick articJoy;
	
	// Left Joystick
	public JoystickButton shiftUpDriver;
	public JoystickButton shiftDownDriver;
	public JoystickButton resetYaw;
	
	// Right Joystick
	public JoystickButton driveClear;
	
	// Artic Joystick
	public JoystickButton climberUp;
	public JoystickButton climberDown;
	public JoystickButton climberClear;
//	public JoystickButton gearManipUp;
//	public JoystickButton gearManipMid;
//	public JoystickButton gearManipDown;
//	public JoystickButton gearManipManual;
//	public JoystickButton dropGearBackOff;
//	public JoystickButton gearClear;
	
	public JoystickButton lowerIntake;
	public JoystickButton lowerSpinIntake;
	public JoystickButton raiseIntakeHold;
	public JoystickButton outtakeGear;
	public JoystickButton intakeManual;
	public JoystickButton spinIntake;
	
	public JoystickButton shiftUpOperator;
	public JoystickButton shiftDownOperator;
	
	public OI() {
		driveLeftJoy = new Joystick(0);
		driveRightJoy = new Joystick(1);
		articJoy = new Joystick(2);
		
		// Left Joystick
		shiftUpDriver = new JoystickButton(driveLeftJoy, 4);
		shiftUpDriver.whenPressed(new ShiftUp());
		shiftDownDriver = new JoystickButton(driveLeftJoy, 3);
		shiftDownDriver.whenPressed(new ShiftDown());
		resetYaw = new JoystickButton(driveLeftJoy, 2);
		resetYaw.whenPressed(new ResetYaw());
		
		//Right Joystick
//		driveClear = new JoystickButton(driveRightJoy, 2);
//		driveClear.cancelWhenPressed(Robot.drive.getCurrentCommand());

		// Artic Joystick
//		gearManipManual = new JoystickButton(articJoy, 1);
//		gearManipManual.whileHeld(new GearManualControl());
//		gearManipUp = new JoystickButton(articJoy, 7);
//		gearManipUp.whenPressed(new SetGearManipNoTimer(GearManipulationConstants.kGearManipUpPos));
//		gearManipMid = new JoystickButton(articJoy, 9);
//		gearManipMid.whenPressed(new SetGearManipNoTimer(GearManipulationConstants.kGearManipMidPos));
//		gearManipDown = new JoystickButton(articJoy, 11);
//		gearManipDown.whenPressed(new SetGearManipNoTimer(GearManipulationConstants.kGearManipDownPos));
//		gearClear = new JoystickButton(articJoy, 2);
//		gearClear.cancelWhenPressed(Robot.gearManip.getCurrentCommand());
		
		lowerIntake = new JoystickButton(articJoy, 9);
		lowerIntake.whenPressed(new IntakeSetPosition(GearIntakeConstants.kGearIntakeDownPos));
		lowerSpinIntake = new JoystickButton(articJoy, 10);
		lowerSpinIntake.whenPressed(new IntakeDown());
		raiseIntakeHold = new JoystickButton(articJoy, 7);
		raiseIntakeHold.whenPressed(new IntakeTuckRetain());
		outtakeGear = new JoystickButton(articJoy, 8);
		outtakeGear.whenPressed(new Outtake());
		intakeManual = new JoystickButton(articJoy, 1);
		intakeManual.whileHeld(new IntakeManualControl());
		spinIntake = new JoystickButton(articJoy, 2);
		spinIntake.whileHeld(new SpinSpeed(GearIntakeConstants.kGearIntakeSpinVoltage));
		
		shiftUpOperator = new JoystickButton(articJoy, 4);
		shiftUpOperator.whenPressed(new ShiftUp());
		shiftDownOperator = new JoystickButton(articJoy, 3);
		shiftDownOperator.whenPressed(new ShiftDown());
		
		climberUp = new JoystickButton(articJoy, 12);
		climberUp.whileHeld(new Climb());
		
		
		// Dashboard
		SmartDashboard.putData("Test Left Drive", new DriveTime(1, 0, 2));
		SmartDashboard.putData("Test Right Drive", new DriveTime(0, 1, 2));
		SmartDashboard.putData("Shift Up", new ShiftUp());
		SmartDashboard.putData("Shift Down", new ShiftDown());
		
		SmartDashboard.putData("Gear Manip Up", new SetGearManipNoTimer(GearManipulationConstants.kGearManipUpPos));
		SmartDashboard.putData("Gear Manip Down", new SetGearManipNoTimer(GearManipulationConstants.kGearManipDownPos));
	}
	
	public double getLeftY() {
		return driveLeftJoy.getY();
	}
	
	public double getLeftX() {
		return driveLeftJoy.getX();
	}
	
	public double getRightY() {
		return driveRightJoy.getY();
	}
	
	public double getRightX() {
		return driveRightJoy.getX();
	}
	
	public double getArticY() {
		return articJoy.getY();
	}
		
}