package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.Cock;
import org.usfirst.frc.team236.robot.commands.Eject;
import org.usfirst.frc.team236.robot.commands.Intake;
import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.ShiftUp;
import org.usfirst.frc.team236.robot.commands.ShootCycle;
import org.usfirst.frc.team236.robot.commands.arm.ArmWithJoystick;
import org.usfirst.frc.team236.robot.commands.arm.GoBottomTrust;
import org.usfirst.frc.team236.robot.commands.arm.SetArmAngle;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
    public Joystick leftStick;
    public Joystick rightStick;
    public Joystick controller;
    public Button shiftUp;
    public Button shiftDown;
    public Button intake;
    public Button eject;
    public Button intakeoverride;
    public Button shoot;
    public Button cock;
    public Button manualArmUp;
    public Button manualArmDown;
    public Button armWithJoystick;
    public Button invertDrive;
    public Button normalDrive;
    public Button armWithPOV;
    public Button armPIDTest;
    public Button goBottom;
    public Button goNearBatterHighShotAngle;
    public Button goDefenseHighShotAngle;
    public Button goFarBatterHighShotAngle;
    public Button controllerShoot;
    public Button controllerIntake;

    public OI() {
	leftStick = new Joystick(RobotMap.ControlMap.PORT_STICK_LEFT);
	rightStick = new Joystick(RobotMap.ControlMap.PORT_STICK_RIGHT);
	controller = new Joystick(RobotMap.ControlMap.PORT_CONTROLLER);

	shiftDown = new JoystickButton(rightStick, RobotMap.ControlMap.BUTTON_SHIFT_DOWN);
	shiftDown.whenPressed(new ShiftDown());

	shiftUp = new JoystickButton(rightStick, RobotMap.ControlMap.BUTTON_SHIFT_UP);
	shiftUp.whenPressed(new ShiftUp());

	shoot = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_SHOOT);
	// shoot.whenPressed(new ShootCycle());

	eject = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_EJECT);
	eject.whileHeld(new Eject());

	intake = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_INTAKE);
	intake.whileHeld(new Intake());

	cock = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_COCK);
	cock.whenPressed(new Cock());

	goBottom = new JoystickButton(controller, RobotMap.ControlMap.BUTTON_ARM_BOTTOM);
	goBottom.whenPressed(new GoBottomTrust());

	goNearBatterHighShotAngle = new JoystickButton(controller,
		RobotMap.ControlMap.BUTTON_ARM_HIGH_SHOT_NEAR_BATTER);
	goNearBatterHighShotAngle.whenPressed(new SetArmAngle(6000));

	goFarBatterHighShotAngle = new JoystickButton(controller, RobotMap.ControlMap.BUTTON_ARM_HIGH_SHOT_FAR_BATTER);
	goFarBatterHighShotAngle.whenPressed(new SetArmAngle(5500));

	goDefenseHighShotAngle = new JoystickButton(controller, RobotMap.ControlMap.BUTTON_ARM_HIGH_SHOT_DEFENSE);
	goDefenseHighShotAngle.whenPressed(new SetArmAngle(4200));

	controllerShoot = new JoystickButton(controller, RobotMap.ControlMap.BUTTON_SHOOT_CONTROLLER);
	controllerShoot.whenPressed(new ShootCycle());

	armWithJoystick = new JoystickButton(controller, RobotMap.ControlMap.BUTTON_ARM_JOYSTICK);
	armWithJoystick.whileHeld(new ArmWithJoystick());

	controllerIntake = new JoystickButton(controller, RobotMap.ControlMap.BUTTON_INTAKE_CONTROLLER);
	controllerIntake.whileHeld(new Intake());
    }
}
