package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {
	// Declare motors
	private Victor leftFront;
	private Victor leftMid;
	private Victor leftBack;
	private Victor rightFront;
	private Victor rightMid;
	private Victor rightBack;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithJoysticks());
	}

	public Drive() {
		// Instantiate Victors
		leftFront = new Victor(RobotMap.DriveMap.PWM_LEFT_FRONT);
		leftMid = new Victor(RobotMap.DriveMap.PWM_LEFT_MID);
		leftBack = new Victor(RobotMap.DriveMap.PWM_LEFT_BACK);
		rightFront = new Victor(RobotMap.DriveMap.PWM_RIGHT_FRONT);
		rightMid = new Victor(RobotMap.DriveMap.PWM_RIGHT_MID);
		rightBack = new Victor(RobotMap.DriveMap.PWM_RIGHT_BACK);
	}

	public void setLeftSpeed(double speed) {
		if (speed > 1 || speed < -1) {
			return;
		}
		leftFront.set(speed);
		leftMid.set(speed);
		leftBack.set(speed);
	}

	public void setRightSpeed(double speed) {
		if (speed > 1 || speed < -1) {
			return;
		}
		rightFront.set(speed);
		rightMid.set(speed);
		rightBack.set(speed);
	}

	public void setSpeeds(double leftSpeed, double rightSpeed) {
		if (leftSpeed > 1 || leftSpeed < -1) {
			return;
		}
		if (rightSpeed > 1 || rightSpeed < -1) {
			return;
		}
		leftFront.set(leftSpeed);
		leftMid.set(leftSpeed);
		leftBack.set(leftSpeed);

		rightFront.set(rightSpeed);
		rightMid.set(rightSpeed);
		rightBack.set(rightSpeed);
	}

	public void stop() {
		this.setSpeeds(0, 0);
	}
}
