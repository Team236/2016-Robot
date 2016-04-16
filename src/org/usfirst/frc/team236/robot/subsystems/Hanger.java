package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hanger extends Subsystem {
    private SpeedController motor;

    public Hanger() {
	motor = new Talon(RobotMap.HangerMap.PWM_MOTOR);
	motor.setInverted(RobotMap.HangerMap.INV_MOTOR);
    }

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
    }

    public void setSpeed(double speed) {
	motor.set(speed);
    }

    public void stop() {
	setSpeed(0);
    }
}
