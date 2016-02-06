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
		public static final int PWM_LEFT_BACK = 1;
		public static final int PWM_RIGHT_FRONT = 2;
		public static final int PWM_RIGHT_BACK = 3;

		public static final int DIO_ENCODER_LEFT_A = 0;
		public static final int DIO_ENCODER_LEFT_B = 1;
		public static final int DIO_ENCODER_RIGHT_A = 2;
		public static final int DIO_ENCODER_RIGHT_B = 3;

		public static final int SOL_FORWARD = 0;
		public static final int SOL_REVERSE = 1;

		public static final boolean INV_LEFT_FRONT = false;
		public static final boolean INV_LEFT_MID = false;
		public static final boolean INV_LEFT_BACK = false;

		public static final boolean INV_RIGHT_FRONT = false;
		public static final boolean INV_RIGHT_MID = false;
		public static final boolean INV_RIGHT_BACK = false;

		public static final boolean INV_ENCODER_LEFT = false;
		public static final boolean INV_ENCODER_RIGHT = false;

		public static final double DISTANCE_PER_PULSE = 1; // TODO get distance
	}

	public class IntakeMap {
		public static final int PWM_MOTOR = 4;
		public static final boolean INV_MOTOR = false;
	}

	public class ArmMap {
		public static final int PWM_MOTOR = 5;
		public static final boolean INV_MOTOR = false;

		public static final int DIO_ENCODER_A = 4;
		public static final int DIO_ENCODER_B = 5;
		public static final double DEGREES_PER_PULSE = 1; // TODO get degrees
		public static final boolean INV_ENCODER = false;

		public static final int DIO_LIMITSWITCH_TOP = 6;
		public static final int DIO_LIMITSWITCH_BOTTOM = 7;

		public static final int MAN_INCREMENT = 1; // TODO test, get arm speed
		
		public static final double MIN_ANGLE = -15; // Lowest angle of arm

		public class PID {
			// TODO tune PID
			public static final double kP = 1;
			public static final double kI = .5;
			public static final double kD = 17;

		}
	}

	public class ControlMap {
		// USB
		public static final int PORT_STICK_LEFT = 0;
		public static final int PORT_STICK_RIGHT = 1;
		public static final int PORT_CONTROLLER = 2;

		// Right stick
		public static final int BUTTON_EJECT = 1;
		public static final int BUTTON_SHIFT_DOWN = 2;
		public static final int BUTTON_SHIFT_UP = 3;

		// Left stick
		public static final int BUTTON_INTAKE = 1;
		public static final int BUTTON_INVERT_DRIVE = 2;
		public static final int BUTTON_COCK = 4;
		public static final int BUTTON_SHOOT = 5;

		// Controller
		public static final int BUTTON_ARM_DOWN = 2;
		public static final int BUTTON_ARM_UP = 4;
	}

	public class ShooterMap {
		public static final int PWM_MOTOR_LEFT = 6;
		public static final boolean INV_MOTOR_LEFT = false;
		public static final int PWM_MOTOR_RIGHT = 7;
		public static final boolean INV_MOTOR_RIGHT = false;

		// Not used
		/*
		public static final int DIO_ENCODER_A = 6;
		public static final int DIO_ENCODER_B = 7;
		public static final double DISTANCE_PER_PULSE = 1; // TODO get distance
		public static final boolean INV_ENCODER = false;
		*/

		public static final int SOL_FORWARD = 2;
		public static final int SOL_REVERSE = 3;
	}
}
