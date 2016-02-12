package org.usfirst.frc.team236.robot.motionProfile;

import org.usfirst.frc.team236.robot.Updatable;

/**
 * 
 * @author samcf_000
 *
 */

public class ProfileFollower implements Updatable {
	// Profile to be followed
	Profile profile;
	// Step of profile
	private int i = 0;
	// Source for position feedback
	public ProfileSource source;
	// Output for the motor power
	public ProfileOutput output;
	// Constants + Gains
	double kV, kA, kP;
	double endPosition;
	public volatile boolean isEnabled;
	public volatile boolean onTarget;

	public ProfileFollower(Profile p, ProfileSource source, ProfileOutput output, FollowParameters params) {
		this.profile = p;
		this.source = source;
		this.output = output;
		this.kV = params.kV;
		this.kA = params.kA;
		this.kP = params.kP;

		this.endPosition = p.get(p.length() - 1).position;
	}

	@Override
	public void update() {
		// Make sure i is within bounds of profile
		if (i < 0) {
			i = 0;
		} else if (i > profile.length()) {
			i = profile.length();
		}
		// Get position from the profile
		double position = profile.get(i).position;
		// Determine if we are on target. Allowing error of +/- .1
		onTarget = Math.abs(endPosition + source.getDistance()) < .1;

		if (isEnabled) {
			// Calculate the acceleration and velocity feedforward
			double a = kA * profile.get(i).acceleration;
			double v = kV * profile.get(i).speed;
			// Calculate the correction
			double correction = kP * Math.abs(source.getDistance() - position);
			// Set the speed of the motor with correction
			output.setSpeed(a + v + correction);
			// Increment our loop counter
			i++;
		}
	}
}
