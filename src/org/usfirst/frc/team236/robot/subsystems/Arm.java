package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Arm extends PIDSubsystem {

	private SpeedController motor;
	private Encoder encoder;
	public DigitalInput upperLimit;
	public DigitalInput bottomLimit;

	private static final double kP_up = RobotMap.ArmMap.upPID.kP;
	private static final double kI_up = RobotMap.ArmMap.upPID.kI;
	private static final double kD_up = RobotMap.ArmMap.upPID.kD;

	// Initialize your subsystem here
	public Arm() {
		super("arm", kP_up, kI_up, kD_up);

		motor = new Talon(RobotMap.ArmMap.PWM_MOTOR_LEFT);
		motor.setInverted(RobotMap.ArmMap.INV_MOTOR_LEFT);

		encoder = new Encoder(RobotMap.ArmMap.DIO_ENCODER_A, RobotMap.ArmMap.DIO_ENCODER_B);
		encoder.setDistancePerPulse(RobotMap.ArmMap.DEGREES_PER_PULSE);
		encoder.setReverseDirection(RobotMap.ArmMap.INV_ENCODER);

		upperLimit = new DigitalInput(RobotMap.ArmMap.DIO_LIMIT_TOP);
		bottomLimit = new DigitalInput(RobotMap.ArmMap.DIO_LIMIT_BOTTOM);

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
		return encoder.getDistance() + RobotMap.ArmMap.MIN_ANGLE;
	}

	public void stop() {
		setSpeed(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new ArmWithJoystick());
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
		encoder.reset();
	}

	public double getRawEncoder() {
		return encoder.getRaw();
	}

	public boolean getUpperLimit() {
		return !upperLimit.get();
	}

	public boolean getBottomLimit() {
		return !bottomLimit.get();
	}
}
