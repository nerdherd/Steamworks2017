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
	public JoystickButton snapToTarget_3;
	
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
	public JoystickButton dropGearBackOff_10;
	public JoystickButton setShooterMax_11;
	
	public OI() {
		driveLeftJoy = new Joystick(0);
		driveRightJoy = new Joystick(1);
		articJoy = new Joystick(2);
		
		// Left Joystick
		shiftUp_3 = new JoystickButton(driveLeftJoy, 3);
		shiftUp_3.whenPressed(new ShiftUp());
		shiftDown_4 = new JoystickButton(driveRightJoy, 4);
		shiftDown_4.whenPressed(new ShiftDown());
		snapToTarget_3 = new JoystickButton(driveRightJoy, 3);
		snapToTarget_3.whenPressed(new SnapToTarget());
		
		//Right Joystick
		gearManipUp_2 = new JoystickButton(driveRightJoy, 2);
		gearManipUp_2.whenPressed(new GearManipSet(GearManipulationConstants.kGearManipUpPos));
		gearManipMid_3 = new JoystickButton(driveRightJoy, 3);
		gearManipMid_3.whenPressed(new GearManipSet(GearManipulationConstants.kGearManipMidPos));
		gearManipDown_4 = new JoystickButton(driveRightJoy, 4);
		gearManipDown_4.whenPressed(new GearManipSet(GearManipulationConstants.kGearManipDownPos));
		gearManipManual_1 = new JoystickButton(driveRightJoy, 1);
		gearManipManual_1.whileHeld(new GearManualControl());
		dropGearBackOff_10 = new JoystickButton(driveRightJoy, 10);
		dropGearBackOff_10.whenPressed(new DropGearBackOff());
		
		// Artic Joystick
		climberUp_3 = new JoystickButton(articJoy, 3);
		climberUp_3.whenPressed(new ClimberSet(1));
		climberDown_4 = new JoystickButton(articJoy, 4);
		climberDown_4.whenPressed(new ClimberSet(-1));
		
		conveyorIn_5 = new JoystickButton(articJoy, 5);
		conveyorIn_5.whenPressed(new ConveyorSet(1));
		conveyorOut_6 = new JoystickButton(articJoy, 6);
		conveyorOut_6.whenPressed(new ConveyorSet(-1));
		
		intakeManual_7 = new JoystickButton(articJoy, 7);
		intakeManual_7.whileHeld(new IntakeManualControl());
		intakeWheelsIn_8 = new JoystickButton(articJoy, 8);
		intakeWheelsIn_8.whenPressed(new IntakeWheelsClosedLoop(IntakeConstants.kIntakeWheelRPM));
		intakeWheelsOut_9 = new JoystickButton(articJoy, 9);
		intakeWheelsOut_9.whenPressed(new IntakeWheelsClosedLoop(-IntakeConstants.kIntakeWheelRPM));
		intakeArticUp_1 = new JoystickButton(articJoy, 1);
		intakeArticUp_1.whenPressed(new IntakeSetPos(IntakeConstants.kIntakeUpPos));
		intakeArticDown_2 = new JoystickButton(articJoy, 2);
		intakeArticDown_2.whenPressed(new IntakeSetPos(IntakeConstants.kIntakeDownPos));
		
		setShooterMax_11 = new JoystickButton(articJoy, 11);
		setShooterMax_11.whileHeld(new SetSpeed(ShooterConstants.kMaxRPM));
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