package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	private SpeedController motorLeft;
	private SpeedController motorRight;
	private DoubleSolenoid solenoid;

	public Shooter() {
		motorLeft = new Spark(RobotMap.ShooterMap.PWM_MOTOR_LEFT);
		motorLeft.setInverted(RobotMap.ShooterMap.INV_MOTOR_LEFT);

		motorRight = new Spark(RobotMap.ShooterMap.PWM_MOTOR_RIGHT);
		motorRight.setInverted(RobotMap.ShooterMap.INV_MOTOR_RIGHT);

		solenoid = new DoubleSolenoid(RobotMap.ShooterMap.SOL_FORWARD, RobotMap.ShooterMap.SOL_REVERSE);
	}

	public void setSpeed(double speed) {
		motorLeft.set(speed);
		motorRight.set(speed);
	}

	public void setSol(int direction) {
		if (direction == 1) {
			solenoid.set(DoubleSolenoid.Value.kForward);
		} else if (direction == 0) {
			solenoid.set(DoubleSolenoid.Value.kOff);
		} else if (direction == -1) {
			solenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public void retractPin() {
		setSol(-1);
	}

	public void insertPin() {
		setSol(1);
	}

	public void retract() {
		setSpeed(-1);
	}

	public void stop() {
		setSpeed(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
