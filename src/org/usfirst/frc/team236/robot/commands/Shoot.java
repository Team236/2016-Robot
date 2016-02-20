package org.usfirst.frc.team236.robot.commands;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {
	private int i;

	public Shoot() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		i = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooter.retractPin();
		Robot.shooter.setIsCocked(false);
		i++;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (i / 50) > 1; // Time out after 1 second
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
