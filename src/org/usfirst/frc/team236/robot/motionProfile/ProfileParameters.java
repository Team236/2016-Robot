package org.usfirst.frc.team236.robot.motionProfile;

/**
 * A class to contain the parameters necessary to form a motion profile. Based
 * on 2015-Robot
 * 
 * @author Sam
 * @param d
 *            The maximum distance of the profile
 * @param v
 *            The maximum allowable maxVelocity
 * @param a
 *            The maximum allowable maxAccel
 * @param j
 *            The maximum allowable maxJerk
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
