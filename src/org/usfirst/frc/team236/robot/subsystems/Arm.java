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
	private DigitalInput limitSwitchTop;
	private DigitalInput limitSwitchBottom;

	private static final double kP = RobotMap.ArmMap.PID.kP;
	private static final double kI = RobotMap.ArmMap.PID.kI;
	private static final double kD = RobotMap.ArmMap.PID.kD;

	// Initialize your subsystem here
	public Arm() {
		super("arm", kP, kI, kD);

		motor = new Talon(RobotMap.ArmMap.PWM_MOTOR);
		motor.setInverted(RobotMap.ArmMap.INV_MOTOR);

		encoder = new Encoder(RobotMap.ArmMap.DIO_ENCODER_A, RobotMap.ArmMap.DIO_ENCODER_B);
		encoder.setDistancePerPulse(RobotMap.ArmMap.DEGREES_PER_PULSE);
		encoder.setReverseDirection(RobotMap.ArmMap.INV_ENCODER);
		
		limitSwitchTop = new DigitalInput(RobotMap.ArmMap.DIO_LIMITSWITCH_TOP);
		limitSwitchBottom = new DigitalInput(RobotMap.ArmMap.DIO_LIMITSWITCH_BOTTOM);
		

		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
		setOutputRange(0, 100); // TODO add maximum range
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		
	}
	public void setSpeed(double speed) {
		if(speed == 1) {
			setSpeed(1);
		}
		else if (speed == -1) {
			setSpeed(-1);
		}
		
	}
	public void set(double speed) {
		if(limitSwitchTop.get() == true) {
			motor.set(0);
			
		}
		else if (limitSwitchBottom.get() == true) {
			motor.set(0);
			encoder.reset();
		}
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return encoder.getDistance();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		motor.set(output);
	}

	public void zeroEncoder() {
		encoder.reset();
	}

}
