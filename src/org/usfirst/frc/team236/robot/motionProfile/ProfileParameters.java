package org.usfirst.frc.team236.robot.motionProfile;

/**
 * A class to contain the parameters necessary to form a motion profile. Based
 * on 2015-Robot
 * 
 * @author Sam
 * @param d
 *            The maximum distance of the profile
 * @param v
 *            The maximum allowable velocity
 * @param a
 *            The maximum allowable acceleration
 * @param j
 *            The maximum allowable jerk
 */
public class ProfileParameters {
	public double distance, velocity, acceleration, jerk;

	public ProfileParameters(double d, double v, double a, double j) {
		distance = d;
		velocity = v;
		acceleration = a;
		jerk = j;
	}

}
