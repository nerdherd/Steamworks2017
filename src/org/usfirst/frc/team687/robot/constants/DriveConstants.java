package org.usfirst.frc.team687.robot.constants;

/**
 * Drivetrain constants
 *
 * @author Ted
 *
 */

public class DriveConstants {
	
	public final static double kDistP = 0;
	public final static double kDistI = 0;
	public final static double kDistD = 0;
	
	public final static double kRotP = 0;
	public final static double kRotI = 0;
	public final static double kRotD = 0;
	
	public final static double kLowMaxV = 0;
	public final static double kHighMaxV = 0;
	public final static double kLowV = 1.0 / kLowMaxV;
	public final static double kHighV = 1.0 / kHighMaxV;
	public final static double kLowMaxA = 0;
	public final static double kHighMaxA = 0;
	
	public enum Path {
		RightGear, CenterGear, LeftGear
	}
	
	public static double getFirstSegment(Path path) {
		if (path == Path.RightGear) {
			return 0;
		} else if (path == Path.CenterGear) {
			return 0;
		} else if (path == Path.LeftGear) {
			return 0;
		} else {
			return 0;
		}
	}
	
	public static double getFirstAngle(Path path) {
		if (path == Path.RightGear) {
			return 0;
		} else if (path == Path.LeftGear) {
			return 0;
		} else {
			return 0;
		}
	}
	
	public static double getSecondSegment(Path path) {
		if (path == Path.RightGear) {
			return 0;
		} else if (path == Path.LeftGear) {
			return 0;
		} else {
			return 0;
		}
	}
}
