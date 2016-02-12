package org.usfirst.frc.team236.robot.commands.autonomous;

import org.usfirst.frc.team236.robot.AutoMap;
import org.usfirst.frc.team236.robot.Robot;
import org.usfirst.frc.team236.robot.motionProfile.DriveSide;
import org.usfirst.frc.team236.robot.motionProfile.Profile;
import org.usfirst.frc.team236.robot.motionProfile.ProfileFollower;

import edu.wpi.first.wpilibj.command.Command;
import updater.Updater;

/**
 *
 */
public class FollowProfile extends Command {

	Profile leftProfile, rightProfile;
	ProfileFollower left, right;
	DriveSide leftSide, rightSide;
	ProfileFollower followLeft, followRight;

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
		if (leftProfile != null || rightProfile != null) {
			Robot.drive.zeroEncoders();
			followLeft = new ProfileFollower(leftProfile, leftSide, leftSide, AutoMap.params);
			followRight = new ProfileFollower(rightProfile, rightSide, rightSide, AutoMap.params);

			Updater.getInstance().addThreadedUpdatable(followLeft);
			Updater.getInstance().addThreadedUpdatable(followRight);

			left.isEnabled = true;
			right.isEnabled = true;
		} else {
			System.out.println("Null profile(s)");
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		left.isEnabled = false;
		right.isEnabled = false;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		left.isEnabled = false;
		right.isEnabled = false;
	}
}
