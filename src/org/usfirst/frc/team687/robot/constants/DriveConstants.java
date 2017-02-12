package org.usfirst.frc.team687.robot.constants;

public class DriveConstants {
	
	public final static double kDistP = 0;
	public final static double kDistI = 0;
	public final static double kDistD = 0;
	
	public final static double kRotP = 0.01;
	public final static double kRotI = 0;
	public final static double kRotD = 0;
	// Tolerance for closed loop DriveTurnToAngle command in degrees
	public final static double kDriveRotationTolerance = 1;
	public final static double kDriveRotationOscillationCount = 20;
	
	public final static double kMaxVelocity = 0;
	public final static double kMaxAcceleration = 0;
	
	public enum DriveMode {
		TANK, ARCADE, FIELD_CENTRIC
	}
}
