package org.usfirst.frc.team687.robot;

import org.usfirst.frc.team687.robot.commands.climber.ClimberDown;
import org.usfirst.frc.team687.robot.commands.climber.ClimberUp;
import org.usfirst.frc.team687.robot.commands.conveyor.ConveyorIn;
import org.usfirst.frc.team687.robot.commands.conveyor.ConveyorOut;
import org.usfirst.frc.team687.robot.commands.drive.ShiftDown;
import org.usfirst.frc.team687.robot.commands.drive.ShiftUp;
import org.usfirst.frc.team687.robot.commands.gearmanip.GearManipDown;
import org.usfirst.frc.team687.robot.commands.gearmanip.GearManipMid;
import org.usfirst.frc.team687.robot.commands.gearmanip.GearManipUp;
import org.usfirst.frc.team687.robot.commands.gearmanip.GearManualControl;
import org.usfirst.frc.team687.robot.commands.intake.IntakeArticDown;
import org.usfirst.frc.team687.robot.commands.intake.IntakeArticUp;
import org.usfirst.frc.team687.robot.commands.intake.IntakeInClosedLoop;
import org.usfirst.frc.team687.robot.commands.intake.IntakeOutClosedLoop;
import org.usfirst.frc.team687.robot.commands.intake.IntakeManualControl;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team687.robot.commands.*;
import org.usfirst.frc.team687.robot.constants.*;


public class OI {
	public Joystick driveLeftJoy;
	public Joystick driveRightJoy;
	public Joystick articJoy;
	
	// Left Joystick
	public JoystickButton shiftUp_3;
	public JoystickButton shiftDown_4;
	// Right Joystick
	public JoystickButton gearManipUp_2;
	public JoystickButton gearManipMid_3;
	public JoystickButton gearManipDown_4;
	public JoystickButton gearManipManual_1;
	
	// Artic Joystick
	public JoystickButton climberUp_3;
	public JoystickButton climberDown_4;
	public JoystickButton conveyorIn_5;
	public JoystickButton conveyorOut_5;
	public JoystickButton conveyorOut_6;
	public JoystickButton intakeManual_7;
	public JoystickButton intakeWheelsIn_8;
	public JoystickButton intakeWheelsOut_9;
	public JoystickButton intakeArticUp_1;
	public JoystickButton intakeArticDown_2;
	
	public JoystickButton releaseGears_1;
	public JoystickButton secureGears_2;
	public JoystickButton snapToTarget_3;
	
	public OI() {
		driveLeftJoy = new Joystick(0);
		driveRightJoy = new Joystick(1);
		articJoy = new Joystick(2);
		
		// Left Joystick
		shiftUp_3 = new JoystickButton(driveLeftJoy, 3);
		shiftUp_3.whenPressed(new ShiftUp());
		shiftDown_4 = new JoystickButton(driveRightJoy, 4);
		shiftDown_4.whenPressed(new ShiftDown());
		
		//Right Joystick
		gearManipUp_2 = new JoystickButton(driveRightJoy, 2);
		gearManipUp_2.whenPressed(new GearManipUp());
		gearManipMid_3 = new JoystickButton(driveRightJoy, 3);
		gearManipMid_3.whenPressed(new GearManipMid());
		gearManipDown_4 = new JoystickButton(driveRightJoy, 4);
		gearManipDown_4.whenPressed(new GearManipDown());
		gearManipManual_1 = new JoystickButton(driveRightJoy, 1);
		gearManipManual_1.whileHeld(new GearManualControl());
		
		// Artic Joystick
		climberUp_3 = new JoystickButton(articJoy, 3);
		climberUp_3.whenPressed(new ClimberUp());
		climberDown_4 = new JoystickButton(articJoy, 4);
		climberDown_4.whenPressed(new ClimberDown());
		
		conveyorIn_5 = new JoystickButton(articJoy, 5);
		conveyorIn_5.whenPressed(new ConveyorIn());
		conveyorOut_6 = new JoystickButton(articJoy, 6);
		conveyorOut_6.whenPressed(new ConveyorOut());
		
		intakeManual_7 = new JoystickButton(articJoy, 7);
		intakeManual_7.whileHeld(new IntakeManualControl());
		intakeWheelsIn_8 = new JoystickButton(articJoy, 8);
		intakeWheelsIn_8.whenPressed(new IntakeInClosedLoop());
		intakeWheelsOut_9 = new JoystickButton(articJoy, 9);
		intakeWheelsOut_9.whenPressed(new IntakeOutClosedLoop());
		intakeArticUp_1 = new JoystickButton(articJoy, 1);
		intakeArticUp_1.whenPressed(new IntakeArticUp());
		intakeArticDown_2 = new JoystickButton(articJoy, 2);
		intakeArticDown_2.whenPressed(new IntakeArticDown());
		
	}
	
	public double getLeftY() {
		return driveLeftJoy.getY();
	}
	
	public double getRightY() {
		return driveRightJoy.getY();
	}
	
	public double getArticY() {
		return articJoy.getY();
	}
		
}