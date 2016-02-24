package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmWithJoystick extends Command {

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

		if (Robot.arm.getUpperLimit()) {
			if (-Robot.oi.controller.getRawAxis(1) > 0) {
				Robot.arm.stop();
			} else {
				Robot.arm.setSpeed(-Robot.oi.controller.getRawAxis(1) / 2);
			}
		} else if (Robot.arm.getBottomLimit()) {
			if (-Robot.oi.controller.getRawAxis(1) < 0) {
				Robot.arm.stop();
			} else {
				Robot.arm.setSpeed(-Robot.oi.controller.getRawAxis(1) / 2);
			}
		}

		//Robot.arm.setSpeed(-Robot.oi.controller.getRawAxis(1) / 3);

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
		Robot.arm.setSetpointRelative(0); // Keep arm at this angle
		Robot.arm.enable();
	}
}
