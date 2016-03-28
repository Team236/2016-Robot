package org.usfirst.frc.team236.robot;

import motionProfile.FollowParameters;
import motionProfile.ProfileParameters;

public class AutoMap {
    // The distance we turn to shoot for the goal
    public static final double turnForGoalDegrees = 58;

    public static final FollowParameters params = new FollowParameters(0, 1 / 120, 1 / 120);

    /*
     * Drives from the midfield to the position aligned with the left goal by
     * going under the low bar. // TODO add v, a, j
     */
    public static final ProfileParameters toShoot = new ProfileParameters(223, 60, 48, 24);

    /*
     * Drives from midfield line through the low goal into the opponent's
     * courtyard. // TODO testing
     */
    public static final ProfileParameters crossLowBar = new ProfileParameters(72, 60, 120, 240);
    
    /*
     * Drives from auto line over any bumpy defense into the courtyard, with a
     * considerable buffer.
     */
    public static final ProfileParameters bigCross = new ProfileParameters(200, 60, 140, 240);

    /*
     * Drives from auto line onto the ramp of any defense. // TODO testing
     */
    public static final ProfileParameters reach = new ProfileParameters(35, 25, 60, 120);

    /*
     * Drives from our roundabout point to the edge of the batter to shoot
     */
    public static final ProfileParameters toLowGoal = new ProfileParameters(98, 30, 50, 24);
}
