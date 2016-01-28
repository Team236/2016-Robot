package org.usfirst.frc.team236.robot.motionProfile;

import org.usfirst.frc.team236.robot.Updatable;

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
	
	public ProfileFollower(Profile p, ProfileSource source, ProfileOutput output, double kP, double kV, double kA) {
		this.profile = p;
		this.source = source;
		this.output = output;
		this.kV = kV;
		this.kA = kA;
		this.kP = kP;
		
		this.endPosition = p.get(p.length() - 1).position;
	}
	
	@Override
	public void update() {
		if (i < 0) {
			i = 0;
		} else if (i > profile.length()) {
			i = profile.length();
		}
		
	}

}
