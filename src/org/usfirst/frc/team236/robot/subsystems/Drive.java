package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import motionProfile.DriveSide;

/**
 *
 */
public class Drive extends Subsystem {
	// Declare motors
	private VictorSP leftFront;
	private VictorSP leftBack;
	private VictorSP rightFront;
	private VictorSP rightBack;

	// Declare encoder
	private Encoder leftEncoder;
	private Encoder rightEncoder;

	// Declare solenoids
	private DoubleSolenoid solenoid;

	// Declare drivesides
	public DriveSide leftSide;
	public DriveSide rightSide;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithJoysticks());
	}

	public Drive() {
		// Instantiate VictorSPs
		leftFront = new VictorSP(RobotMap.DriveMap.PWM_LEFT_FRONT);
		leftBack = new VictorSP(RobotMap.DriveMap.PWM_LEFT_BACK);
		rightFront = new VictorSP(RobotMap.DriveMap.PWM_RIGHT_FRONT);
		rightBack = new VictorSP(RobotMap.DriveMap.PWM_RIGHT_BACK);

		// Instantiate Encoders
		leftEncoder = new Encoder(RobotMap.DriveMap.DIO_ENCODER_LEFT_A, RobotMap.DriveMap.DIO_ENCODER_LEFT_B);
		rightEncoder = new Encoder(RobotMap.DriveMap.DIO_ENCODER_RIGHT_A, RobotMap.DriveMap.DIO_ENCODER_RIGHT_B);

		// Invert VictorSPs
		leftFront.setInverted(RobotMap.DriveMap.INV_LEFT_FRONT);
		leftBack.setInverted(RobotMap.DriveMap.INV_LEFT_MID);
		rightFront.setInverted(RobotMap.DriveMap.INV_RIGHT_FRONT);
		rightBack.setInverted(RobotMap.DriveMap.INV_RIGHT_MID);

		// Invert Encoders
		leftEncoder.setReverseDirection(RobotMap.DriveMap.INV_ENCODER_LEFT);
		rightEncoder.setReverseDirection(RobotMap.DriveMap.INV_ENCODER_RIGHT);

		// Set distance per pulse
		leftEncoder.setDistancePerPulse(RobotMap.DriveMap.DISTANCE_PER_PULSE);
		rightEncoder.setDistancePerPulse(RobotMap.DriveMap.DISTANCE_PER_PULSE);

		// Instantiate solenoid
		solenoid = new DoubleSolenoid(RobotMap.DriveMap.SOL_FORWARD, RobotMap.DriveMap.SOL_REVERSE);

		// Instantiate drivesides
		leftSide = new DriveSide(leftFront, leftBack, leftEncoder);
		rightSide = new DriveSide(rightFront, rightBack, rightEncoder);
	}

	public void setLeftSpeed(double speed) {
		if (speed > 1) {
			speed = 1;
		} else if (speed < -1) {
			speed = -1;
		}
		leftFront.set(speed);
		leftBack.set(speed);
	}

	public void setRightSpeed(double speed) {
		if (speed > 1) {
			speed = 1;
		} else if (speed < -1) {
			speed = -1;
		}
		rightFront.set(speed);
		rightBack.set(speed);
	}

	public void setSpeeds(double leftSpeed, double rightSpeed) {
		leftFront.set(leftSpeed);
		leftBack.set(leftSpeed);

		rightFront.set(rightSpeed);
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

	public void setSol(int direction) {
		if (direction == -1) {
			solenoid.set(DoubleSolenoid.Value.kReverse);
		} else if (direction == 0) {
			solenoid.set(DoubleSolenoid.Value.kOff);
		} else if (direction == 1) {
			solenoid.set(DoubleSolenoid.Value.kForward);
		}
	}
}
