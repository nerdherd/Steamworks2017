package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author Ted
 * 
 */

public class RobotMap {
	
	public final static int rDriveJoy = 1;
	public final static int lDriveJoy = 2;
	public final static int articJoy = 3;
	
	public final static int lDrive1 = 0;
	public final static int lDrive2 = 1;
	public final static int lDrive3 = 2;
	public final static int rDrive1 = 3;
	public final static int rDrive2 = 4;
	public final static int rDrive3 = 5;
	
	public final static int shifter1 = 8;
	public final static int shifter2 = 9;
	
	public final static int gearManip = 6;
	
	public final static Port AHRSPort = SerialPort.Port.kMXP;
	
}
