package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.arm.ArmWithJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Arm extends PIDSubsystem {

	private SpeedController motorLeft;
	private SpeedController motorRight;
	private Encoder encoder;
	public DigitalInput upperLimit;
	public DigitalInput bottomLimit;

	private static final double kP = RobotMap.ArmMap.PID.kP;
	private static final double kI = RobotMap.ArmMap.PID.kI;
	private static final double kD = RobotMap.ArmMap.PID.kD;

	// Initialize your subsystem here
	public Arm() {
		super("arm", kP, kI, kD);

		motorLeft = new Talon(RobotMap.ArmMap.PWM_MOTOR_LEFT);
		motorLeft.setInverted(RobotMap.ArmMap.INV_MOTOR_LEFT);

		motorRight = new Talon(RobotMap.ArmMap.PWM_MOTOR_RIGHT);
		motorRight.setInverted(RobotMap.ArmMap.INV_MOTOR_RIGHT);

		encoder = new Encoder(RobotMap.ArmMap.DIO_ENCODER_A, RobotMap.ArmMap.DIO_ENCODER_B);
		encoder.setDistancePerPulse(RobotMap.ArmMap.DEGREES_PER_PULSE);
		encoder.setReverseDirection(RobotMap.ArmMap.INV_ENCODER);

		upperLimit = new DigitalInput(RobotMap.ArmMap.DIO_LIMIT_TOP);
		bottomLimit = new DigitalInput(RobotMap.ArmMap.DIO_LIMIT_BOTTOM);

		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
		setOutputRange(0, 100); // TODO add maximum range
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.

	}

	public void setSpeed(double speed) {
		motorLeft.set(speed);
		motorRight.set(speed);
	}
	
	public double getAngle() {
		// This adjusts for bottom limit at some angle (-11) less than zero.
		return encoder.getDistance() + RobotMap.ArmMap.MIN_ANGLE;
	}

	public void stop() {
		setSpeed(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ArmWithJoystick());
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
		motorLeft.set(output);
		motorRight.set(output);
	}

	public void zeroEncoder() {
		encoder.reset();
	}

	public boolean getUpperLimit() {
		return !upperLimit.get();
	}

	public boolean getBottomLimit() {
		return !bottomLimit.get();
	}
}
