package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private SpeedController motor;

	public Intake() {
		motor = new Victor(RobotMap.IntakeMap.PWM_MOTOR);

		motor.setInverted(RobotMap.IntakeMap.INV_MOTOR);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void set(double speed) {
		motor.set(speed);
	}

	public void intake() {
		set(1);
	}

	public void eject() {
		set(-1);
	}

	public void stop() {
		set(0);
	}
}
