package org.usfirst.frc.team236.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {
	double waitTime;
	int i = 0;

	public Wait(double seconds) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.waitTime = seconds;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		i++;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (i / 50) > waitTime;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
