package org.usfirst.frc.team236.robot;

import standard.LogitechF310;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;

    public static final String MID_CAMERA_NAME = "cam3";
    public static final String INTAKE_CAMERA_NAME = "cam4";

    public class DriveMap {
	public static final int PWM_LEFT_FRONT = 0;
	public static final int PWM_LEFT_BACK = 1;
	public static final int PWM_RIGHT_FRONT = 2;
	public static final int PWM_RIGHT_BACK = 3;

	public static final int DIO_ENCODER_LEFT_A = 0;
	public static final int DIO_ENCODER_LEFT_B = 1;
	public static final int DIO_ENCODER_RIGHT_A = 2;
	public static final int DIO_ENCODER_RIGHT_B = 3;

	public static final int SOL_FORWARD = 0;
	public static final int SOL_REVERSE = 1;

	public static final boolean INV_LEFT_FRONT = true;
	public static final boolean INV_LEFT_BACK = true;

	public static final boolean INV_RIGHT_FRONT = false;
	public static final boolean INV_RIGHT_BACK = false;

	public static final boolean INV_ENCODER_LEFT = false;
	public static final boolean INV_ENCODER_RIGHT = false;

	// Circumference of Drive Wheel may need tweaking
	public static final double CIRCUMFERENCE = 28;
	// Gear ratio is 3:1, and raw encoder count is 512 per full revolution
	public static final double DISTANCE_PER_PULSE = CIRCUMFERENCE / (3 * 512);
    }

    public class IntakeMap {
	public static final int PWM_MOTOR = 4;
	public static final boolean INV_MOTOR = false;

	public static final int DIO_LIMIT = 8;
    }

    public static class ArmMap {
	public static final int FLASHLIGHT_RELAY = 0;

	public static final int PWM_MOTOR = 5;
	public static final boolean INV_MOTOR = false;

	public static final int PWM_MOTOR_RIGHT = 6;
	public static final boolean INV_MOTOR_RIGHT = false;

	public static final int DIO_ENCODER_A = 4;
	public static final int DIO_ENCODER_B = 5;

	public static final int DIO_LIMIT_TOP = 6;
	public static final int DIO_LIMIT_BOTTOM = 7;

	public static final double MIN_ANGLE = -10.5; // Lowest angle of arm
	public static final double MAX_ANGLE = 83; // TODO Highest angle of arm

	public static final double BATTER_HIGH_SHOT_ANGLE = 74.0;
	public static final double DEFENSE_HIGH_SHOT_ANGLE = 37.948;

	// TODO Trig numbers
	// Numbers to find actuator side length
	public static final boolean INV_ENCODER = false;
	public static final double ACTUATOR_MIN_LENGTH = 10;
	public static final double INCHES_PER_THREAD = (1 / 2);
	public static final double ROTATIONS_PER_PULSE = (1 / 512);
	public static final double SCALE_FACTOR = (36 / 34);
	public static final double DISTANCE_PER_PULSE = SCALE_FACTOR * ROTATIONS_PER_PULSE * INCHES_PER_THREAD;

	// Axle to pivot side length
	public static final double AXLE_ACTUATOR_DISTANCE = 4.75;

	// Axle to anchor side length
	public static final double AXLE_ANCHOR_MID_DISTANCE = 10;
	public static final double MID_ANCHOR_DISTANCE = 4;
	public static final double MID_ANCHOR_ANGLE = Math.atan(MID_ANCHOR_DISTANCE / AXLE_ANCHOR_MID_DISTANCE);
	public static final double AXLE_ANCHOR_DISTANCE = Math
		.sqrt(Math.pow(AXLE_ANCHOR_MID_DISTANCE, 2) + Math.pow(MID_ANCHOR_DISTANCE, 2));

	public class PID {
	    public static final double kP = .05;
	    public static final double kI = 0;
	    public static final double kD = 0;
	}
    }

    public class ControlMap {
	// USB
	public static final int PORT_STICK_LEFT = 0;
	public static final int PORT_STICK_RIGHT = 1;
	public static final int PORT_CONTROLLER = 2;

	// Left stick
	public static final int BUTTON_SHOOT = 1;
	public static final int BUTTON_EJECT = 2;
	public static final int BUTTON_INTAKE = 3;
	public static final int BUTTON_COCK = 4;
	public static final int BUTTON_INTAKE_OVERRIDE = 5;

	// Right stick
	public static final int BUTTON_SHIFT_DOWN = 2;
	public static final int BUTTON_SHIFT_UP = 3;
	public static final int BUTTON_INVERT_DRIVE = 4;
	public static final int BUTTON_NORMAL_DRIVE = 5;

	// Controller
	public static final int BUTTON_ARM_BOTTOM = LogitechF310.A;
	public static final int BUTTON_ARM_HIGH_SHOT_BATTER = LogitechF310.Y;
	public static final int BUTTON_ARM_WITH_POV = LogitechF310.LB;
	public static final int BUTTON_SHOOT_CONTROLLER = LogitechF310.RB;
	public static final int BUTTON_ARM_JOYSTICK = LogitechF310.LEFT_PRESS;
	public static final int BUTTON_ARM_HIGH_SHOT_DEFENSE = LogitechF310.X;
	public static final int AXIS_ARM = LogitechF310.Axes.LEFT_Y;
	public static final int BUTTON_FLASHLIGHT = LogitechF310.LB;
    }

    public class ShooterMap {
	public static final int PWM_MOTOR = 7;
	public static final boolean INV_MOTOR = false;

	public static final int SOL_FORWARD = 2;
	public static final int SOL_REVERSE = 3;
    }
}
