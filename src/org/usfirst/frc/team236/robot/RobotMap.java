package org.usfirst.frc.team236.robot;

import standard.LogitechF310;

public class RobotMap {
    public static final String MID_CAMERA_NAME = "cam3";
    public static final String INTAKE_CAMERA_NAME = "cam4";

    public class ShooterMap {
	public static final int PWM_MOTOR = 7;
	public static final boolean INV_MOTOR = false;
	public static final int SOL_FORWARD = 2;
	public static final int SOL_REVERSE = 3;

	public ShooterMap() {
	}
    }

    public class ControlMap {
	public static final int PORT_STICK_LEFT = 0;
	public static final int PORT_STICK_RIGHT = 1;
	public static final int PORT_CONTROLLER = 2;
	public static final int BUTTON_SHOOT = 1;
	public static final int BUTTON_EJECT = 2;
	public static final int BUTTON_INTAKE = 3;
	public static final int BUTTON_COCK = 4;
	public static final int BUTTON_INTAKE_OVERRIDE = 5;
	public static final int BUTTON_SHIFT_DOWN = 2;
	public static final int BUTTON_SHIFT_UP = 3;
	public static final int BUTTON_INVERT_DRIVE = 4;
	public static final int BUTTON_NORMAL_DRIVE = 5;
	public static final int BUTTON_ARM_BOTTOM = 1;
	public static final int BUTTON_ARM_HIGH_SHOT_NEAR_BATTER = 2;
	public static final int BUTTON_ARM_HIGH_SHOT_FAR_BATTER = 4;
	public static final int BUTTON_ARM_HIGH_SHOT_DEFENSE = 3;
	public static final int BUTTON_INTAKE_CONTROLLER = 5;
	public static final int BUTTON_SHOOT_CONTROLLER = 6;
	public static final int BUTTON_ARM_JOYSTICK = 9;
	public static final int AXIS_ARM = 1;
	public static final int BUTTON_TURN = LogitechF310.START;

	public ControlMap() {
	}
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
	public static final double MIN_COUNT = 0.0D;
	public static final double MAX_COUNT = 6600.0D;
	public static final double NEAR_BATTER_HIGH_SHOT_ANGLE = 6000.0D;
	public static final double FAR_BATTER_HIGH_SHOT_ANGLE = 5500.0D;
	public static final double DEFENSE_HIGH_SHOT_ANGLE = 4200.0D;
	public static final double CROSSING_ANGLE = -4100.0D;
	public static final boolean INV_ENCODER = false;
	public static final double ACTUATOR_MIN_LENGTH = 12.25D;
	public static final double INCHES_PER_THREAD = 0.0D;
	public static final double ROTATIONS_PER_PULSE = 0.0D;
	public static final double SCALE_FACTOR = 1.0D;
	public static final double DISTANCE_PER_PULSE = 0.0D;
	public static final double AXLE_ACTUATOR_DISTANCE = 4.75D;
	public static final double AXLE_ANCHOR_MID_DISTANCE = 15.5D;
	public static final double MID_ANCHOR_DISTANCE = 2.625D;
	public static final double MID_ANCHOR_ANGLE = Math.atan(0.1693548387096774D);
	public static final double AXLE_ANCHOR_DISTANCE = Math.sqrt(Math.pow(15.5D, 2.0D) + Math.pow(2.625D, 2.0D));

	public class PID {
	    public static final double kP = 0.001D;
	    public static final double kI = 0.0D;
	    public static final double kD = 0.0D;

	    public PID() {
	    }
	}
    }

    public class IntakeMap {
	public static final int PWM_MOTOR = 4;
	public static final boolean INV_MOTOR = false;
	public static final int DIO_LIMIT = 8;

	public IntakeMap() {
	}
    }

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
	public static final double CIRCUMFERENCE = 28.0D;
	public static final double DISTANCE_PER_PULSE = 0.018229166666666668D;
    }
}
