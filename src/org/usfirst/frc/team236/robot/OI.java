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

	// Ports
	leftStick = new Joystick(ControlMap.PORT_STICK_LEFT);
	rightStick = new Joystick(ControlMap.PORT_STICK_RIGHT);
	controller = new Joystick(ControlMap.PORT_CONTROLLER);

	// Right Stick
	shiftDown = new JoystickButton(rightStick, ControlMap.RightStick.SHIFT_DOWN);
	shiftDown.whenPressed(new ShiftDown());

	shiftUp = new JoystickButton(rightStick, ControlMap.RightStick.SHIFT_UP);
	shiftUp.whenPressed(new ShiftUp());

	// Left Stick
	eject = new JoystickButton(leftStick, ControlMap.LeftStick.EJECT);
	eject.whileHeld(new Eject());

	intake = new JoystickButton(leftStick, ControlMap.LeftStick.INTAKE);
	intake.whileHeld(new Intake());

	cock = new JoystickButton(leftStick, ControlMap.LeftStick.COCK);
	cock.whenPressed(new Cock());

	// Controller
	goBottom = new JoystickButton(controller, ControlMap.Controller.ARM_BOTTOM);
	goBottom.whenPressed(new GoBottomTrust());

	goNearBatterHighShotAngle = new JoystickButton(controller, ControlMap.Controller.ARM_HIGH_SHOT_NEAR_BATTER);
	goNearBatterHighShotAngle.whenPressed(new SetArmAngle(6000));

	goFarBatterHighShotAngle = new JoystickButton(controller, ControlMap.Controller.ARM_HIGH_SHOT_FAR_BATTER);
	goFarBatterHighShotAngle.whenPressed(new SetArmAngle(5500));

	goDefenseHighShotAngle = new JoystickButton(controller, ControlMap.Controller.ARM_HIGH_SHOT_DEFENSE);
	goDefenseHighShotAngle.whenPressed(new SetArmAngle(4200));

	controllerShoot = new JoystickButton(controller, ControlMap.Controller.SHOOT);
	controllerShoot.whenPressed(new ShootCycle());

	armWithJoystick = new JoystickButton(controller, ControlMap.Controller.ARM_JOYSTICK);
	armWithJoystick.whileHeld(new ArmWithJoystick());

	controllerIntake = new JoystickButton(controller, ControlMap.Controller.INTAKE_CONTROLLER);
	controllerIntake.whileHeld(new Intake());
    }
}
