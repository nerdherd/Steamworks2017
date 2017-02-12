package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team687.robot.commands.climber.*;
import org.usfirst.frc.team687.robot.commands.drive.*;
import org.usfirst.frc.team687.robot.commands.gearmanip.*;
import org.usfirst.frc.team687.robot.commands.intake.*;
import org.usfirst.frc.team687.robot.constants.*;
import org.usfirst.frc.team687.robot.constants.DriveConstants.DriveMode;


public class OI {
	public Joystick driveLeftJoy;
	public Joystick driveRightJoy;
	public Joystick articJoy;
	
	// Left Joystick
	public JoystickButton shiftUp;
	public JoystickButton shiftDown;
	public JoystickButton resetYaw;
	
	// Right Joystick
	public JoystickButton gearManipUp;
	public JoystickButton gearManipMid;
	public JoystickButton gearManipDown;
	public JoystickButton gearManipManual;
	public JoystickButton snapToTarget;
	
	// Artic Joystick
	public JoystickButton climberUp;
	public JoystickButton climberDown;
	public JoystickButton intakeManual;
	public JoystickButton intakeWheelsIn;
	public JoystickButton intakeWheelsOut;
	public JoystickButton intakeArticUp;
	public JoystickButton intakeArticDown;
	public JoystickButton dropGearBackOff;
	
	public OI() {
		driveLeftJoy = new Joystick(0);
		driveRightJoy = new Joystick(1);
		articJoy = new Joystick(2);
		
		// Left Joystick
		shiftUp = new JoystickButton(driveLeftJoy, 3);
		shiftUp.whenPressed(new ShiftUp());
		shiftDown = new JoystickButton(driveLeftJoy, 4);
		shiftDown.whenPressed(new ShiftDown());
		resetYaw = new JoystickButton(driveLeftJoy, 2);
		resetYaw.whenPressed(new ResetYaw());
		
		//Right Joystick
		
		// Artic Joystick
		gearManipManual = new JoystickButton(articJoy, 1);
		gearManipManual.whileHeld(new GearManualControl());
		gearManipUp = new JoystickButton(articJoy, 7);
		gearManipUp.whenPressed(new GearManipSetNoTimer(GearManipulationConstants.kGearManipUpPos));
		gearManipMid = new JoystickButton(articJoy, 9);
		gearManipMid.whenPressed(new GearManipSetNoTimer(GearManipulationConstants.kGearManipMidPos));
		gearManipDown = new JoystickButton(articJoy, 11);
		gearManipDown.whenPressed(new GearManipSetTimer(GearManipulationConstants.kGearManipDownPos, GearManipulationConstants.kGearManipTimeout));
		
		/**
		climberUp = new JoystickButton(articJoy, 3);
		climberUp.whenPressed(new ClimberSet(1));
		climberDown = new JoystickButton(articJoy, 4);
		climberDown.whenPressed(new ClimberSet(-1));
	
		intakeManual = new JoystickButton(articJoy, 7);
		intakeManual.whileHeld(new IntakeManualControl());
		intakeWheelsIn = new JoystickButton(articJoy, 8);
		intakeWheelsIn.whenPressed(new IntakeWheelsClosedLoop(IntakeConstants.kIntakeWheelRPM));
		intakeWheelsOut = new JoystickButton(articJoy, 9);
		intakeWheelsOut.whenPressed(new IntakeWheelsClosedLoop(-IntakeConstants.kIntakeWheelRPM));
		intakeArticUp = new JoystickButton(articJoy, 1);
		intakeArticUp.whenPressed(new IntakeSetPos(IntakeConstants.kIntakeUpPos));
		intakeArticDown = new JoystickButton(articJoy, 2);
		intakeArticDown.whenPressed(new IntakeSetPos(IntakeConstants.kIntakeDownPos));
		**/
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