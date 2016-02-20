package org.usfirst.frc.team236.robot.commands;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Cock extends Command {
	private int i;

	public Cock() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		i = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooter.setSpeed(.5); // Retract spring
		Robot.shooter.insertPin();
		i++;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (i / 50) > 5; // Time out after 5 seconds
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.shooter.stop();
		Robot.shooter.SetIsCocked(true);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
