package org.usfirst.frc.team236.robot.commands.autonomous;

import java.util.InputMismatchException;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Make the robot drive a distance without motion profiles
 * 
 * @param _speed
 *            The speed for the robot to drive (-1 to 1)
 * @param _distance
 *            The distance for the robot to drive in inches. Must be negative if
 *            the robot is going backwards
 */
public class RawDriveDistance extends Command {
    double speed;
    double distance; // Distance to drive in inches

    public RawDriveDistance(double _speed, double _distance) {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.drive);
	this.speed = _speed;
	this.distance = _distance;

	if (_speed / _speed != _distance / _distance) {
	    throw (new InputMismatchException());
	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.drive.zeroEncoders();
	System.out.println("Started RawDrive");
	Robot.drive.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	Robot.drive.setSpeeds(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return Robot.drive.getLeftDistance() > distance || Robot.drive.getLeftDistance() > distance;
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
