package org.usfirst.frc.team687.robot.constants;

public class DriveConstants {
	
	public final static double kDistF = 0.1; // You probably want to put value calcs here rather than the number
	public final static double kDistP = 0.6;
	public final static double kDistI = 0;
	public final static double kDistD = 0;
	
	public final static double kRotP = 0.0075;
	public final static double kRotI = 0;
	public final static double kRotD = 3;
	// Tolerance for closed loop DriveTurnToAngle command in degrees
	public final static double kDriveRotationTolerance = 1;
	public final static double kDriveRotationOscillationCount = 5;
	
	public final static double kMaxVelocity = 0;
	public final static double kMaxAcceleration = 0;
	
	public final static double kShiftCurrent = 20; // Amps Per Talon
	public final static double kShiftAcceleration = 2; // Gotta make sure this is right
	
	public enum DriveMode {
		TANK, ARCADE, FIELD_CENTRIC
	}
	
	public final static double kDriveDistanceTolerance = 0.3; //Magic units
	public final static double kDriveFeetToEncoderUnitsR = 4.388*3/(Math.PI);
	public final static double kDriveFeetToEncoderUnitsL = 4.487*3/(Math.PI);
	public final static double kDriveStraightP = 0.018;
	
	public static double kDistanceMidAuto = -6.6; //was 6.6	// new 9
	
	public static double kDistanceFeederAuto1 = -7.5; //was 7.25	// new 7.5
	public static double kDistanceFeederAuto2 = -2.25; //was 2.25	// new 8
	public static double kAngleFeederAutoBlue = -62.5;
	public static double kAngleFeederAutoRed = 62.5;
	public static double kAngleBoilerAutoBlue = -62.5;
	public static double kAngleBoilerAutoRed = 62.5;
	
	public final static int kDriveCurrentLimit = 20;
	public final static double DriveAlpha = 0.125; 
}
