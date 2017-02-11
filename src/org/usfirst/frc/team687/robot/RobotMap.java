package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.I2C.Port;

public class RobotMap {
	
	public static final int shifterPort1 = 0;
	public static final int shifterPort2 = 1;
	
	//CANTalons
	public static final int encoderTalonRPort = 4;
	public static final int followerTalonR1Port = 5;
	public static final int followerTalonR2Port = 6;
	public static final int encoderTalonLPort = 1;
	public static final int followerTalonL1Port = 2;
	public static final int followerTalonL2Port = 3;
	public static final int climberPort = 6;
	public static final int conveyorPort = 7;
	public static final int intakeWheelPort = 8;
	public static final int intakeArticPort = 9;
	public static final int gearManipPort = 10;
	public static final int shooterPort = 11;
	
	public static final Port navPort = Port.kMXP;
}
