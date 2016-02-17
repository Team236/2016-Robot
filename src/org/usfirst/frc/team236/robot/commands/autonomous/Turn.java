package org.usfirst.frc.team236.robot.commands.autonomous;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @param degrees The number of degrees to turn. Positive values turn right.
 *
 */
public class Turn extends Command {
	
	private double degrees;
	private double heading;
	private double initialHeading;
	private double distanceTurned;
	private int turnDirectionCoeff;
	private final double turnSpeed = 0.5;

    public Turn(double degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	
    	this.degrees = degrees;
    	// This coefficient should be positive when turning right
    	this.turnDirectionCoeff = (int) (degrees / (Math.abs(degrees)));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.stop();
    	initialHeading = Robot.navx.getRawGyroY();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	heading = Robot.navx.getRawGyroY();
    	distanceTurned = heading - initialHeading;
    	Robot.drive.setLeftSpeed(turnSpeed * turnDirectionCoeff);
    	Robot.drive.setRightSpeed(turnSpeed * -turnDirectionCoeff);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (this.distanceTurned == this.degrees);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
