package org.usfirst.frc.team236.robot.commands.profiled;

import org.usfirst.frc.team236.robot.AutoMap;
import org.usfirst.frc.team236.robot.commands.Cock;
import org.usfirst.frc.team236.robot.commands.Eject;
import org.usfirst.frc.team236.robot.commands.Intake;
import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.ShootCycle;
import org.usfirst.frc.team236.robot.commands.arm.GoBottomFromTop;
import org.usfirst.frc.team236.robot.commands.autonomous.TurnWithGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;
import motionProfile.Profile;

/**
 *
 */
public class LowShot extends CommandGroup {

    public LowShot(Profile toSpin, Profile toLowGoal) {

	addParallel(new ShiftDown());
	addParallel(new Intake());
	addSequential(new GoBottomFromTop());

	addParallel(new Cock());
	addParallel(new Intake());
	addSequential(new FollowProfile(toSpin, true));

	addParallel(new Intake());
	addSequential(new TurnWithGyro(180 - AutoMap.turnForGoalDegrees));

	addParallel(new Intake());
	addSequential(new FollowProfile(toLowGoal, false));

	addSequential(new Eject(), 3);

	addSequential(new ShootCycle());
    }
}
