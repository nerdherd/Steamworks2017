package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team687.robot.commands.climber.*;
import org.usfirst.frc.team687.robot.commands.conveyor.*;
import org.usfirst.frc.team687.robot.commands.drive.*;
import org.usfirst.frc.team687.robot.commands.gearmanip.*;
import org.usfirst.frc.team687.robot.commands.intake.*;
import org.usfirst.frc.team687.robot.commands.shooter.SetSpeed;
import org.usfirst.frc.team687.robot.constants.*;
import org.usfirst.frc.team687.robot.constants.DriveConstants.DriveMode;


public class OI {
	public Joystick driveLeftJoy;
	public Joystick driveRightJoy;
	public Joystick articJoy;
	
	// Left Joystick
	public JoystickButton shiftUp;
	public JoystickButton shiftDown;
	public JoystickButton setModeTank;
	public JoystickButton setModeArcade;
	public JoystickButton setModeFieldCentric;
	
	// Right Joystick
	public JoystickButton gearManipUp;
	public JoystickButton gearManipMid;
	public JoystickButton gearManipDown;
	public JoystickButton gearManipManual;
	public JoystickButton snapToTarget;
	
	// Artic Joystick
	public JoystickButton climberUp;
	public JoystickButton climberDown;
	public JoystickButton conveyorIn;
	public JoystickButton conveyorOut;
	public JoystickButton intakeManual;
	public JoystickButton intakeWheelsIn;
	public JoystickButton intakeWheelsOut;
	public JoystickButton intakeArticUp;
	public JoystickButton intakeArticDown;
	public JoystickButton dropGearBackOff;
	public JoystickButton setShooterMax;
	
	public OI() {
		driveLeftJoy = new Joystick(0);
		driveRightJoy = new Joystick(1);
		//articJoy = new Joystick(2);
		
		// Left Joystick
		shiftUp = new JoystickButton(driveLeftJoy, 3);
		shiftUp.whenPressed(new ShiftUp());
		shiftDown = new JoystickButton(driveLeftJoy, 4);
		shiftDown.whenPressed(new ShiftDown());
		setModeTank = new JoystickButton(driveLeftJoy, 7);
		setModeTank.whenPressed(new SetDriveMode(DriveMode.TANK));
		setModeArcade = new JoystickButton(driveLeftJoy, 9);
		setModeArcade.whenPressed(new SetDriveMode(DriveMode.ARCADE));
		setModeFieldCentric = new JoystickButton(driveLeftJoy, 11);
		setModeFieldCentric.whenPressed(new SetDriveMode(DriveMode.FIELD_CENTRIC));
		
		//Right Joystick
		gearManipUp = new JoystickButton(driveRightJoy, 7);
		gearManipUp.whenPressed(new GearManipSet(GearManipulationConstants.kGearManipUpPos));
		gearManipMid = new JoystickButton(driveRightJoy, 9);
		gearManipMid.whenPressed(new GearManipSet(GearManipulationConstants.kGearManipMidPos));
		gearManipDown = new JoystickButton(driveRightJoy, 11);
		gearManipDown.whenPressed(new GearManipSet(GearManipulationConstants.kGearManipDownPos));
		gearManipManual = new JoystickButton(driveRightJoy, 1);
		gearManipManual.whileHeld(new GearManualControl());
		
		// Artic Joystick
		/**
		climberUp = new JoystickButton(articJoy, 3);
		climberUp.whenPressed(new ClimberSet(1));
		climberDown = new JoystickButton(articJoy, 4);
		climberDown.whenPressed(new ClimberSet(-1));
	
		conveyorIn = new JoystickButton(articJoy, 5);
		conveyorIn.whenPressed(new ConveyorSet(1));
		conveyorOut = new JoystickButton(articJoy, 6);
		conveyorOut.whenPressed(new ConveyorSet(-1));
	
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
	
		setShooterMax = new JoystickButton(articJoy, 11);
		setShooterMax.whileHeld(new SetSpeed(ShooterConstants.kMaxRPM));
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