package org.usfirst.frc.team236.robot;

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

	public static final String CAMERA_NAME = "cam0";

	public class DriveMap {
		public static final int PWM_LEFT_FRONT = 0;
		public static final int PWM_LEFT_MID = 1;
		public static final int PWM_LEFT_BACK = 2;
		public static final int PWM_RIGHT_FRONT = 3;
		public static final int PWM_RIGHT_MID = 4;
		public static final int PWM_RIGHT_BACK = 5;

		public static final int DIO_ENCODER_LEFT_A = 0;
		public static final int DIO_ENCODER_LEFT_B = 1;
		public static final int DIO_ENCODER_RIGHT_A = 2;
		public static final int DIO_ENCODER_RIGHT_B = 3;

		public static final boolean INV_LEFT_FRONT = false;
		public static final boolean INV_LEFT_MID = false;
		public static final boolean INV_LEFT_BACK = false;

		public static final boolean INV_RIGHT_FRONT = false;
		public static final boolean INV_RIGHT_MID = false;
		public static final boolean INV_RIGHT_BACK = false;

		public static final boolean INV_ENCODER_LEFT = false;
		public static final boolean INV_ENCODER_RIGHT = false;

		public static final double DISTANCE_PER_PULSE = 1;
	}

	public class ControlMap {
		public static final int PORT_STICK_LEFT = 0;
		public static final int PORT_STICK_RIGHT = 1;
		public static final int PORT_CONTROLLER = 2;
	}
}
