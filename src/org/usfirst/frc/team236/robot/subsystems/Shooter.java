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

    private boolean isCocked;
    private SpeedController motor;
    private DoubleSolenoid solenoid;

    public Shooter() {
	isCocked = false;

	motor = new Spark(RobotMap.ShooterMap.PWM_MOTOR);
	motor.setInverted(RobotMap.ShooterMap.INV_MOTOR);

	solenoid = new DoubleSolenoid(RobotMap.ShooterMap.SOL_FORWARD, RobotMap.ShooterMap.SOL_REVERSE);
    }

    public void setSpeed(double speed) {
	motor.set(Math.abs(speed));
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

    public void setIsCocked(boolean _isCocked) {
	isCocked = true;
    }

    public boolean isCocked() {
	return isCocked;
    }

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
    }
}
