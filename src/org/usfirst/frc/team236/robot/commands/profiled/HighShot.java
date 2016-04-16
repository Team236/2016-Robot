package org.usfirst.frc.team236.robot.commands.profiled;

import org.usfirst.frc.team236.robot.AutoMap;
import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.Cock;
import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.ShiftUp;
import org.usfirst.frc.team236.robot.commands.ShootCycle;
import org.usfirst.frc.team236.robot.commands.Wait;
import org.usfirst.frc.team236.robot.commands.arm.GoBottomFromTop;
import org.usfirst.frc.team236.robot.commands.arm.SetArmAngle;
import org.usfirst.frc.team236.robot.commands.autonomous.TurnWithGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;
import motionProfile.Profile;

/**
 *
 */
public class HighShot extends CommandGroup {

    public HighShot(Profile profile) {

	addParallel(new ShiftDown());
	addSequential(new GoBottomFromTop());

	addParallel(new Cock());
	addSequential(new FollowProfile(profile, true));

	addSequential(new ShiftUp());

	addParallel(new SetArmAngle(RobotMap.ArmMap.AUTO_HIGH_SHOT_ANGLE));
	addSequential(new TurnWithGyro(180 - AutoMap.turnForGoalDegrees));

	addParallel(new ShiftDown());
	addSequential(new Wait(1));

	addSequential(new ShootCycle());
    }
}
