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
	leftStick = new Joystick(0);
	rightStick = new Joystick(1);
	controller = new Joystick(2);

	shiftDown = new JoystickButton(rightStick, 2);
	shiftDown.whenPressed(new ShiftDown());

	shiftUp = new JoystickButton(rightStick, 3);
	shiftUp.whenPressed(new ShiftUp());

	shoot = new JoystickButton(leftStick, 1);
	// shoot.whenPressed(new ShootCycle());

	eject = new JoystickButton(leftStick, 2);
	eject.whileHeld(new Eject());

	intake = new JoystickButton(leftStick, 3);
	intake.whileHeld(new Intake());

	cock = new JoystickButton(leftStick, 4);
	cock.whenPressed(new Cock());

	goBottom = new JoystickButton(controller, 1);
	goBottom.whenPressed(new GoBottomTrust());

	goNearBatterHighShotAngle = new JoystickButton(controller, 2);

	goNearBatterHighShotAngle.whenPressed(new SetArmAngle(6000.0D));

	goFarBatterHighShotAngle = new JoystickButton(controller, 4);
	goFarBatterHighShotAngle.whenPressed(new SetArmAngle(5500.0D));

	goDefenseHighShotAngle = new JoystickButton(controller, 3);
	goDefenseHighShotAngle.whenPressed(new SetArmAngle(4200.0D));

	controllerShoot = new JoystickButton(controller, 6);
	controllerShoot.whenPressed(new ShootCycle());

	armWithJoystick = new JoystickButton(controller, 9);
	armWithJoystick.whileHeld(new ArmWithJoystick());

	controllerIntake = new JoystickButton(controller, 5);
	controllerIntake.whileHeld(new Intake());
    }
}
