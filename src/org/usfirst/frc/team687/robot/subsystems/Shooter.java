package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	
	private final CANTalon m_shooter;
	
	public Shooter() {
		m_shooter = new CANTalon(RobotMap.shooterPort);
		m_shooter.setP(ShooterConstants.kShooterP);
		m_shooter.setI(ShooterConstants.kShooterI);
		m_shooter.setD(ShooterConstants.kShooterD);
		m_shooter.setF(ShooterConstants.kShooterF);
		m_shooter.changeControlMode(TalonControlMode.Speed);
	}
	
	public void setSpeed(double speed) {
		m_shooter.set(speed);
	}
	
	public double getSpeed() {
		return m_shooter.getSpeed();
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
