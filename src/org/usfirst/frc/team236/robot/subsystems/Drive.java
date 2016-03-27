package org.usfirst.frc.team236.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.DriveWithJoysticks;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import motionProfile.DriveSide;

/**
 *
 */
public class Drive extends Subsystem {
    // Keep track of what gear we're in. 0 = low, 1 = high
    private int gear;

    // Declare motors
    private VictorSP leftFront;
    private VictorSP leftBack;
    private VictorSP rightFront;
    private VictorSP rightBack;

    private ArrayList<SpeedController> leftMotors;
    private ArrayList<SpeedController> rightMotors;

    // Declare encoder
    private Encoder leftEncoder;
    private Encoder rightEncoder;

    // Declare solenoids
    private DoubleSolenoid solenoid;

    // Declare drivesides
    public DriveSide leftSide;
    public DriveSide rightSide;

    // Declare NavX
    public AHRS navx;

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

	// Create list of motors
	leftMotors = new ArrayList<SpeedController>();
	leftMotors.add(leftFront);
	leftMotors.add(leftBack);
	
	rightMotors = new ArrayList<SpeedController>();
	rightMotors.add(rightFront);
	rightMotors.add(rightBack);

	// Instantiate Encoders
	leftEncoder = new Encoder(RobotMap.DriveMap.DIO_ENCODER_LEFT_A, RobotMap.DriveMap.DIO_ENCODER_LEFT_B);
	rightEncoder = new Encoder(RobotMap.DriveMap.DIO_ENCODER_RIGHT_A, RobotMap.DriveMap.DIO_ENCODER_RIGHT_B);

	// Invert VictorSPs
	leftFront.setInverted(RobotMap.DriveMap.INV_LEFT_FRONT);
	leftBack.setInverted(RobotMap.DriveMap.INV_LEFT_BACK);
	rightFront.setInverted(RobotMap.DriveMap.INV_RIGHT_FRONT);
	rightBack.setInverted(RobotMap.DriveMap.INV_RIGHT_BACK);

	// Invert Encoders
	// leftEncoder.setReverseDirection(RobotMap.DriveMap.INV_ENCODER_LEFT);
	// rightEncoder.setReverseDirection(RobotMap.DriveMap.INV_ENCODER_RIGHT);

	// Set distance per pulse
	leftEncoder.setDistancePerPulse(RobotMap.DriveMap.DISTANCE_PER_PULSE);
	rightEncoder.setDistancePerPulse(RobotMap.DriveMap.DISTANCE_PER_PULSE);

	// Instantiate solenoid
	solenoid = new DoubleSolenoid(RobotMap.DriveMap.SOL_FORWARD, RobotMap.DriveMap.SOL_REVERSE);

	// Instantiate drivesides

	leftSide = new DriveSide(leftMotors, leftEncoder);
	rightSide = new DriveSide(rightMotors, rightEncoder);

	// Start NavX
	navx = new AHRS(SPI.Port.kMXP);

    }

    public void setLeftSpeed(double speed) {
	leftFront.set(speed);
	leftBack.set(speed);
    }

    public void setRightSpeed(double speed) {
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
	return -rightEncoder.getDistance();
    }

    public Encoder getLeftEncoder() {
	return leftEncoder;
    }

    public Encoder getRightEncoder() {
	return rightEncoder;
    }

    /**
     * Set the solenoid forward or reverse.
     * 
     * @param direction
     *            the direction to set the solenoid, in 1 or -1. 1 will set to
     *            high gear, -1 to low gear.
     */
    public void setSol(int direction) {
	if (direction == -1) {
	    // Set drive in low gear
	    solenoid.set(DoubleSolenoid.Value.kReverse);
	    setGear(0);
	} else if (direction == 1) {
	    // Set drive in high gear
	    solenoid.set(DoubleSolenoid.Value.kForward);
	    setGear(1);
	} else {
	    return;
	}
    }

    public int getGear() {
	return this.gear;
    }

    public String getGearString() {
	if (getGear() == 0) {
	    return "low";
	} else if (getGear() == 1) {
	    return "high";
	} else {
	    return null;
	}
    }

    private void setGear(int state) {
	if (state == 0 || state == 1) {
	    this.gear = state;
	}
    }

    /**
     * Sets the motors to turn the robot in place
     * 
     * @param speed
     *            The speed to turn at, negative to turn right
     */
    public void turn(double speed) {
	setLeftSpeed(speed);
	setRightSpeed(-speed);
    }

    public void turnLeft(double speed) {
	setLeftSpeed(-speed);
	setRightSpeed(speed);
    }

    public void turnRight(double speed) {
	setLeftSpeed(speed);
	setRightSpeed(-speed);
    }
}
