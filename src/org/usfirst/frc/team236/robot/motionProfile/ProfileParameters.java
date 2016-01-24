package org.usfirst.frc.team236.robot.motionProfile;

/**
 * A ProfileParameters object contains all of the parameters necessary to build
 * a motion profile. You can pass an instance of ProfileParameters to the
 * profile constructor.
 * 
 * @author Sam
 * @param d The maximum distance of the profile
 * @param v The maximum allowable maxVelocity
 * @param a The maximum allowable maxAccel
 * @param j The maximum allowable maxJerk
 */
public class ProfileParameters {
	public double distance, maxVelocity, maxAccel, maxJerk;

	public ProfileParameters(double d, double v, double a, double j) {
		distance = d;
		maxVelocity = v;
		maxAccel = a;
		maxJerk = j;
	}

}
