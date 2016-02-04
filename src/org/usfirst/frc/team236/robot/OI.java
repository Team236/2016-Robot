package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.Cock;
import org.usfirst.frc.team236.robot.commands.IntakeControl;
import org.usfirst.frc.team236.robot.commands.ManualArmDown;
import org.usfirst.frc.team236.robot.commands.ManualArmUp;
import org.usfirst.frc.team236.robot.commands.Shift;
import org.usfirst.frc.team236.robot.commands.Shoot;
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
	
	public Button shoot;
	public Button cock;
	
	public Button manualArmUp;
	public Button manualArmDown;

	public OI() {
		leftStick = new Joystick(RobotMap.ControlMap.PORT_STICK_LEFT);
		rightStick = new Joystick(RobotMap.ControlMap.PORT_STICK_RIGHT);

		shiftUp = new JoystickButton(rightStick, RobotMap.ControlMap.BUTTON_SHIFT_UP);
		shiftUp.whenPressed(new Shift(0));

		shiftDown = new JoystickButton(rightStick, RobotMap.ControlMap.BUTTON_SHIFT_DOWN);
		shiftDown.whenPressed(new Shift(1));

		intake = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_INTAKE);
		intake.whileHeld(new IntakeControl(1));

		eject = new JoystickButton(rightStick, RobotMap.ControlMap.BUTTON_EJECT);
		eject.whileHeld(new IntakeControl(-1));
		
		shoot = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_SHOOT);
		shoot.whenPressed(new Shoot());
		
		cock = new JoystickButton(leftStick, RobotMap.ControlMap.BUTTON_COCK);
		cock.whenPressed(new Cock());
	
		manualArmUp = new JoystickButton(controller, RobotMap.ControlMap.BUTTON_ARM_UP);
		manualArmUp.whileHeld(new ManualArmUp());
				
		manualArmDown = new JoystickButton(controller, RobotMap.ControlMap.BUTTON_ARM_DOWN);
		manualArmDown.whileHeld(new ManualArmDown());
	}

}
