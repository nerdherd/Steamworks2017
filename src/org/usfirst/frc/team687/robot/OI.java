package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick driveLeftJoy = new Joystick(0);
	public Joystick driveRightJoy = new Joystick(1);
	public Joystick articJoy = new Joystick(2);
	
	public JoystickButton shiftUp_3;
	public JoystickButton shiftDown_4;
	
	public OI() {
		shiftUp_3 = new JoystickButton(driveLeftJoy, 3);
		shiftDown_4 = new JoystickButton(driveRightJoy, 4);
	}
	
	
	
}
