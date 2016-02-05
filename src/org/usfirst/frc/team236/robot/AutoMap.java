package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.motionProfile.ProfileParameters;

public class AutoMap {
	public static ProfileParameters test = new ProfileParameters(10, 5, 4, 2);
	/*
	 * Drives from the midfield to the position aligned with the left goal by
	 * going under the low bar. // TODO add v, a, j
	 */
	public static ProfileParameters toShoot = new ProfileParameters(6.19, 5, 4, 2);

	/*
	 * Drives from midfield line through the low goal into the opponent's
	 * courtyard. // TODO testing
	 */
	public static ProfileParameters cross = new ProfileParameters(12, 5, 10, 20);
}