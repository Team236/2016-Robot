package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.Cock;
import org.usfirst.frc.team236.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team236.robot.commands.Eject;
import org.usfirst.frc.team236.robot.commands.Intake;
import org.usfirst.frc.team236.robot.commands.IntakeOverride;
import org.usfirst.frc.team236.robot.commands.InvertedDriveWithJoysticks;
import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.ShiftUp;
import org.usfirst.frc.team236.robot.commands.Shoot;
import org.usfirst.frc.team236.robot.commands.arm.ManualArmDown;
import org.usfirst.frc.team236.robot.commands.arm.ManualArmUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
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

	public Button invertDrive;
	public Button normalDrive;

	public OI() {
		leftStick = new Joystick(RobotMap.ControlMap.PORT_STICK_LEFT);
		rightStick = new Joystick(RobotMap.ControlMap.PORT_STICK_RIGHT);
		controller = new Joystick(RobotMap.ControlMap.PORT_CONTROLLER);

		// Right Stick
		shiftDown = new JoystickButton(rightStick, RobotMap.ControlMap.BUTTON_SHIFT_DOWN);
		shiftDown.whenPressed(new ShiftDown());

		shiftUp = new JoystickButton(rightStick, RobotMap.ControlMap.BUTTON_SHIFT_UP);
		shiftUp.whenPressed(new ShiftUp());

		invertDrive = new JoystickButton(rightStick, RobotMap.ControlMap.BUTTON_INVERT_DRIVE);
		invertDrive.whenPressed(new InvertedDriveWithJoysticks());
		invertDrive.cancelWhenPressed(new DriveWithJoysticks());

		normalDrive = new JoystickButton(rightStick, RobotMap.ControlMap.BUTTON_NORMAL_DRIVE);
		normalDrive.whenPressed(new DriveWithJoysticks());
		normalDrive.cancelWhenPressed(new InvertedDriveWithJoysticks());

		// Left Stick
		shoot = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_SHOOT);
		shoot.whenPressed(new Shoot());

		eject = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_EJECT);
		eject.whileHeld(new Eject());
		
		intake = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_INTAKE);
		intake.whenPressed(new Intake());
		intakeoverride = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_INTAKE_OVERRIDE);
		intakeoverride.whileHeld(new IntakeOverride());

		cock = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_COCK);
		cock.whenPressed(new Cock());

		// Controller
		manualArmUp = new JoystickButton(controller, RobotMap.ControlMap.BUTTON_ARM_UP);
		manualArmUp.whileHeld(new ManualArmUp());

		manualArmDown = new JoystickButton(controller, RobotMap.ControlMap.BUTTON_ARM_DOWN);
		manualArmDown.whileHeld(new ManualArmDown());

	}
}
