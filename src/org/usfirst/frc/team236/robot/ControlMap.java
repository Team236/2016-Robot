package org.usfirst.frc.team236.robot;

import standard.LogitechF310;

public class ControlMap {
    public static final int PORT_STICK_LEFT = 0;
    public static final int PORT_STICK_RIGHT = 1;
    public static final int PORT_CONTROLLER = 2;

    public class LeftStick {
	public static final int EJECT = 2;
	public static final int INTAKE = 3;
	public static final int COCK = 4;
	public static final int INTAKE_OVERRIDE = 5;
    }

    public class RightStick {

	public static final int SHIFT_DOWN = 2;
	public static final int SHIFT_UP = 3;
    }

    public class Controller {
	public static final int ARM_BOTTOM = LogitechF310.A;
	public static final int ARM_HIGH_SHOT_NEAR_BATTER = LogitechF310.B;
	public static final int ARM_HIGH_SHOT_FAR_BATTER = LogitechF310.Y;
	public static final int ARM_HIGH_SHOT_DEFENSE = LogitechF310.X;
	public static final int HANGER_UP = LogitechF310.LB;
	public static final int SHOOT = LogitechF310.RB;
	public static final int ARM_JOYSTICK = LogitechF310.LEFT_PRESS;
	public static final int AXIS_ARM = LogitechF310.Axes.LEFT_Y;

    }
}