package org.usfirst.frc.team687.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int shifterPort1 = 0;
	public static final int shifterPort2 = 1;
	
	//CANTalons
	public static final int encoderTalonRPort = 2;
	public static final int followerTalonR1Port = 3;
	public static final int followerTalonR2Port = 4;
	public static final int encoderTalonLPort = 5;
	public static final int followerTalonL1Port = 6;
	public static final int followerTalonL2Port = 7;
	public static final int climberPort = 0;
	public static final int conveyorPort = 1;
	public static final int intakeWheelPort = 8;
	public static final int intakeArticPort = 9;
	public static final int gearManipPort = 10;
}
