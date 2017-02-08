package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team687.robot.commands.*;
import org.usfirst.frc.team687.robot.constants.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author Ted
 * 
 */

public class OI {
	
	public Joystick driveJoyLeft = new Joystick(0);
	public Joystick driveJoyRight = new Joystick(1);
	public Joystick articJoy = new Joystick(2);
	
	public JoystickButton shiftUp_3;
	public JoystickButton shiftDown_4;
	
	public JoystickButton releaseGears_1;
	public JoystickButton secureGears_2;
	public JoystickButton snapToTarget_3;
	
	public OI() {
		shiftUp_3 = new JoystickButton(driveJoyLeft, 3);
		shiftUp_3.whenPressed(new ShiftUp());
		shiftDown_4 = new JoystickButton(driveJoyLeft, 4);
		shiftDown_4.whenPressed(new ShiftDown());
		
		releaseGears_1 = new JoystickButton(articJoy, 1);
		releaseGears_1.whenPressed(new SetGearPosition(GearConstants.kOpenPos));
		secureGears_2 = new JoystickButton(articJoy, 2);
		secureGears_2.whenPressed(new SetGearPosition(GearConstants.kSecurePos));
		snapToTarget_3 = new JoystickButton(articJoy, 3);
		snapToTarget_3.whenPressed(new SnapToTarget());
		
		SmartDashboard.putData("Tank Drive", new TankDrive());
		SmartDashboard.putData("Shift Up", new ShiftUp());
		SmartDashboard.putData("Shift Down", new ShiftDown());
		SmartDashboard.putData("Release Gears", new SetGearPosition(GearConstants.kOpenPos));
		SmartDashboard.putData("Secure Gears", new SetGearPosition(GearConstants.kSecurePos));
		SmartDashboard.putData("Snap to Target", new SnapToTarget());
	}
	
	/**
	 * @return input power from left drive joystick (-1.0 to +1.0)
	 */
	public double getDriveJoyL() {
		return driveJoyLeft.getY();
	}
	
	/**
	 * @return input power from right drive joystick (-1.0 to +1.0)
	 */
	public double getDriveJoyR() {
		return driveJoyRight.getY();
	}
	
}