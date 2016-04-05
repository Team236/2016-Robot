package org.usfirst.frc.team236.robot;

public class RobotMap {

    public class ShooterMap {
	public static final int PWM_MOTOR = 7;
	public static final boolean INV_MOTOR = false;
	public static final int SOL_FORWARD = 2;
	public static final int SOL_REVERSE = 3;
    }

    public static class ArmMap {
	public static final int PWM_MOTOR = 5;
	public static final boolean INV_MOTOR = false;
	public static final int PWM_MOTOR_RIGHT = 6;
	public static final boolean INV_MOTOR_RIGHT = false;

	public static final int DIO_ENCODER_A = 4;
	public static final int DIO_ENCODER_B = 5;
	public static final boolean INV_ENCODER = false;

	public static final int DIO_LIMIT_TOP = 6;
	public static final int DIO_LIMIT_BOTTOM = 7;

	public static final double MIN_COUNT = 0;
	public static final double MAX_COUNT = 6600;

	public static final double NEAR_BATTER_HIGH_SHOT_ANGLE = 6000;
	public static final double FAR_BATTER_HIGH_SHOT_ANGLE = 5500;
	public static final double DEFENSE_HIGH_SHOT_ANGLE = 4200;
	public static final double CROSSING_ANGLE = -4100;
	public static final double DISTANCE_PER_PULSE = 1;

	public class PID {
	    public static final double kP = 0.001;
	    public static final double kI = 0.0;
	    public static final double kD = 0.0;
	}
    }

    public class IntakeMap {
	public static final int PWM_MOTOR = 4;
	public static final boolean INV_MOTOR = false;
	public static final int DIO_LIMIT = 8;
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

	public static final double CIRCUMFERENCE = 28;
	public static final double DISTANCE_PER_PULSE = CIRCUMFERENCE / (3 * 512);
    }
}
