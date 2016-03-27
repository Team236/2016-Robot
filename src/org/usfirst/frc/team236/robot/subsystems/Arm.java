package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Arm extends PIDSubsystem {

    private SpeedController motor;
    //private Encoder encoder;
    public DigitalInput upperLimit;
    public DigitalInput bottomLimit;

    //private Relay flashlight;

    private static final double kP_up = RobotMap.ArmMap.PID.kP;
    private static final double kI_up = RobotMap.ArmMap.PID.kI;
    private static final double kD_up = RobotMap.ArmMap.PID.kD;

    // Initialize your subsystem here
    public Arm() {
	super("arm", kP_up, kI_up, kD_up);

	motor = new Talon(RobotMap.ArmMap.PWM_MOTOR);
	motor.setInverted(RobotMap.ArmMap.INV_MOTOR);

	//encoder = new Encoder(RobotMap.ArmMap.DIO_ENCODER_A, RobotMap.ArmMap.DIO_ENCODER_B);
	//encoder.setDistancePerPulse(RobotMap.ArmMap.DISTANCE_PER_PULSE);
	//encoder.setReverseDirection(RobotMap.ArmMap.INV_ENCODER);

	upperLimit = new DigitalInput(RobotMap.ArmMap.DIO_LIMIT_TOP);
	bottomLimit = new DigitalInput(RobotMap.ArmMap.DIO_LIMIT_BOTTOM);

	//flashlight = new Relay(RobotMap.ArmMap.FLASHLIGHT_RELAY);

	setAbsoluteTolerance(0.05);
	getPIDController().setContinuous(false);
    }

    public void setSpeed(double speed) {
	if (getUpperLimit() && speed > 0) {
	    motor.set(0);
	} else if (getBottomLimit() && speed < 0) {
	    motor.set(0);
	} else {
	    motor.set(speed);
	}
    }

    public double getAngle() {
	// This adjusts for bottom limit at some angle (-11) less than zero.
	double angle = solveAngle(RobotMap.ArmMap.AXLE_ACTUATOR_DISTANCE, RobotMap.ArmMap.AXLE_ANCHOR_DISTANCE,
		getActuatorLength());

	angle += RobotMap.ArmMap.MIN_ANGLE;
	angle += RobotMap.ArmMap.MID_ANCHOR_ANGLE;

	return angle;
    }

    public void flashlightOn() {
	//flashlight.set(Relay.Value.kOn);
    }

    public void flashlightOff() {
	//flashlight.set(Relay.Value.kOff);
    }

    private double getActuatorLength() {
	return RobotMap.ArmMap.ACTUATOR_MIN_LENGTH;
    }

    private double solveAngle(double a, double b, double opp) {
	double radians = Math.acos((a * a + b * b - opp * opp) / 2 * a * b);
	double degrees = Math.toDegrees(radians);
	return degrees;
    }

    public void stop() {
	setSpeed(0);
    }

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
	// setDefaultCommand(new ArmWithJoystick());
    }

    protected double returnPIDInput() {
	// Return your input value for the PID loop
	// e.g. a sensor, like a potentiometer:
	// yourPot.getAverageVoltage() / kYourMaxVoltage;
	return getAngle();
    }

    protected void usePIDOutput(double output) {
	// Use output to drive your system, like a motor
	// e.g. yourMotor.set(output);
	motor.set(output);
    }

    public void zeroEncoder() {
	//encoder.reset();
    }

    public double getRawEncoder() {
	return 512;
    }

    public boolean getUpperLimit() {
	return !upperLimit.get();
    }

    public boolean getBottomLimit() {
	return !bottomLimit.get();
    }
}
