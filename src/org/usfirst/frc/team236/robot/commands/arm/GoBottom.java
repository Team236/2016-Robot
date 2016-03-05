package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoBottom extends Command {

	public GoBottom() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.arm.enable();
		Robot.arm.getPIDController().setPID(.02, 0, 0);
		Robot.arm.setSetpoint(-10.5);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.arm.bottomLimit.get();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.arm.disable();
		Robot.arm.stop();
		Robot.arm.zeroEncoder();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
