package org.usfirst.frc.team236.robot.commands.autonomous;

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
	ProfileFollower followLeft, followRight;
	DriveSide leftSide, rightSide;

	public FollowProfile(Profile bothSides) {
		this(bothSides, bothSides);
	}

	public FollowProfile(Profile _left, Profile _right) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);

		this.leftProfile = _left;
		this.rightProfile = _right;

		this.leftSide = Robot.drive.leftSide;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (leftProfile == null || rightProfile == null) {
			System.out.println("Null profile(s)");
		} else {
			Robot.drive.zeroEncoders();
			followLeft = new ProfileFollower(leftProfile, leftSide, leftSide, AutoMap.params);
			followRight = new ProfileFollower(rightProfile, rightSide, rightSide, AutoMap.params);

			Updater.getInstance().addThreadedUpdatable(followLeft);
			Updater.getInstance().addThreadedUpdatable(followRight);

			followLeft.isEnabled = true;
			followRight.isEnabled = true;
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// TODO
		//if (followLeft.error < .1 || followRight.error < .1) {
		//	return true;
		//}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		followLeft.isEnabled = false;
		followRight.isEnabled = false;
		Robot.drive.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		followLeft.isEnabled = false;
		followRight.isEnabled = false;
	}
}
