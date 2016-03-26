package org.usfirst.frc.team236.robot.commands;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {
    private int i;
    private boolean isLimitWorking;

    public Shoot() {
	// Use requires() here to declare subsystem dependencies
	requires(Robot.shooter);
	// If the limit switch is depressed when we call the command, then the
	// switch is a valid way to determine if the ball is still in the robot
	isLimitWorking = Robot.intake.getLimit();
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
	if (isLimitWorking) {
	    // If the limit switch is a valid way to determine if the ball is
	    // in the bot, then we can stop the command when the limit switch
	    // is no longer pressed.
	    return !Robot.intake.getLimit();
	}
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
