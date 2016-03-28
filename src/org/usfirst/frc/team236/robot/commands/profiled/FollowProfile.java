package org.usfirst.frc.team236.robot.commands.profiled;

import org.usfirst.frc.team236.robot.AutoMap;
import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import motionProfile.DriveSide;
import motionProfile.Profile;
import motionProfile.ProfileFollower;
import updater.Updater;

/**
 *
 */
public class FollowProfile extends Command {

    Profile leftProfile, rightProfile;
    ProfileFollower leftFollower, rightFollower;
    DriveSide leftSide, rightSide;
    boolean isInverted;

    public FollowProfile(Profile bothSides, boolean isInverted) {
	this(bothSides, bothSides, isInverted);
    }

    public FollowProfile(Profile _left, Profile _right, boolean _isInverted) {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.drive);

	this.leftProfile = _left;
	this.rightProfile = _right;

	this.leftSide = Robot.drive.leftSide;
	this.rightSide = Robot.drive.rightSide;
	
	this.isInverted = _isInverted;

	System.out.println("FollowProfile command created");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	System.out.println("FollowProfile command started");

	if (leftProfile == null || rightProfile == null) {
	    System.out.println("Null profile(s)");
	} else {
	    Robot.drive.zeroEncoders();
	    leftFollower = new ProfileFollower(leftProfile, leftSide, leftSide, AutoMap.params, isInverted);
	    rightFollower = new ProfileFollower(rightProfile, rightSide, rightSide, AutoMap.params, isInverted);

	    Updater.getInstance().addThreadedUpdatable(leftFollower);
	    Updater.getInstance().addThreadedUpdatable(rightFollower);

	    leftFollower.enable();
	    rightFollower.enable();
	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	System.out.println("FollowProfile Command running");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	// TODO
	/*
	 * if (leftFollower.error < .1 && rightFollower.error < .1) { return
	 * true; }
	 */
	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
	leftFollower.isEnabled = false;
	rightFollower.isEnabled = false;
	Robot.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	leftFollower.isEnabled = false;
	rightFollower.isEnabled = false;
    }
}
