package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmWithPOV extends Command {

	public ArmWithPOV() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.arm.disable(); // Stop PID loop
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Stop arm at either limit
		if (Robot.arm.getBottomLimit() || Robot.arm.getUpperLimit()) {
			Robot.arm.stop();
		} else {
			// Use POV to control arm
			if (Robot.oi.controller.getPOV(0) == 0) {
				// Raise arm if POV up
				Robot.arm.setSpeed(0.5);
			} else if (Robot.oi.controller.getPOV(0) == 180) {
				// Lower arm if POV down
				Robot.arm.setSpeed(-0.5);
			} else if (Robot.oi.controller.getPOV(0) == -1) {
				// Stop arm if no POV pressed
				Robot.arm.stop();
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.arm.setSetpointRelative(0); // Keep arm at this angle
		Robot.arm.enable(); // Re-enable PID loop
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.arm.setSetpointRelative(0); // Keep arm at this angle
		Robot.arm.enable();
	}
}
