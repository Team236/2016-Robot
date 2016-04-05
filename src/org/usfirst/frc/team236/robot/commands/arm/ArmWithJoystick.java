package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.ControlMap;
import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmWithJoystick extends Command {
    private final double scaleFactorUp = 1.5;
    private final double scaleFactorDown = 4;
    private final int axis = ControlMap.Controller.AXIS_ARM;

    public ArmWithJoystick() {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.arm.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	double joySpeed = -Robot.oi.controller.getRawAxis(axis);

	if (joySpeed / joySpeed == 1) {
	    // Arm is going up
	    Robot.arm.setSpeed(joySpeed / scaleFactorUp);
	} else {
	    // Arm is going down
	    Robot.arm.setSpeed(joySpeed / scaleFactorDown);
	}

	if (Robot.arm.getBottomLimit()) {
	    Robot.arm.zeroEncoder();
	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.arm.setSetpointRelative(0); // Keep arm at this angle
	Robot.arm.enable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
