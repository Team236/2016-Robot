package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Encoder;
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

	// Declare encoder
	private Encoder leftEncoder;
	private Encoder rightEncoder;

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

		// Instantiate Encoders
		leftEncoder = new Encoder(RobotMap.DriveMap.DIO_ENCODER_LEFT_A, RobotMap.DriveMap.DIO_ENCODER_LEFT_B);
		rightEncoder = new Encoder(RobotMap.DriveMap.DIO_ENCODER_RIGHT_A, RobotMap.DriveMap.DIO_ENCODER_RIGHT_B);

		// Invert Victors
		leftFront.setInverted(RobotMap.DriveMap.INV_LEFT_FRONT);
		leftMid.setInverted(RobotMap.DriveMap.INV_LEFT_MID);
		leftBack.setInverted(RobotMap.DriveMap.INV_LEFT_BACK);
		rightFront.setInverted(RobotMap.DriveMap.INV_RIGHT_FRONT);
		rightMid.setInverted(RobotMap.DriveMap.INV_RIGHT_MID);
		rightBack.setInverted(RobotMap.DriveMap.INV_RIGHT_BACK);

		// Invert Encoders
		leftEncoder.setReverseDirection(RobotMap.DriveMap.INV_ENCODER_LEFT);
		rightEncoder.setReverseDirection(RobotMap.DriveMap.INV_ENCODER_RIGHT);

		// Set distance per pulse
		leftEncoder.setDistancePerPulse(RobotMap.DriveMap.DISTANCE_PER_PULSE);
		rightEncoder.setDistancePerPulse(RobotMap.DriveMap.DISTANCE_PER_PULSE);
	}

	public void setLeftSpeed(double speed) {
		if (speed > 1) {
			speed = 1;
		} else if (speed < -1) {
			speed = -1;
		}
		leftFront.set(speed);
		leftMid.set(speed);
		leftBack.set(speed);
	}

	public void setRightSpeed(double speed) {
		if (speed > 1) {
			speed = 1;
		} else if (speed < -1) {
			speed = -1;
		}
		rightFront.set(speed);
		rightMid.set(speed);
		rightBack.set(speed);
	}

	public void setSpeeds(double leftSpeed, double rightSpeed) {
		if (leftSpeed > 1) {
			leftSpeed = 1;
		} else if (leftSpeed < -1) {
			leftSpeed = -1;
		}
		if (rightSpeed > 1) {
			rightSpeed = 1;
		} else if (rightSpeed < -1) {
			rightSpeed = -1;
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

	public void zeroEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public double getLeftDistance() {
		return leftEncoder.getDistance();
	}

	public double getRightDistance() {
		return rightEncoder.getDistance();
	}

	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}
}
