package org.usfirst.frc.team236.robot.commands.autonomous;

import org.usfirst.frc.team236.robot.AutoMap;
import org.usfirst.frc.team236.robot.commands.Cock;
import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.Shoot;
import org.usfirst.frc.team236.robot.commands.arm.GoBottom;

import edu.wpi.first.wpilibj.command.CommandGroup;
import motionProfile.Profile;

/**
 *
 */
public class LowShot extends CommandGroup {

	public LowShot(Profile toSpin, Profile toLowGoal) {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		//      addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		//      addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		addParallel(new ShiftDown());
		addSequential(new GoBottom());

		addParallel(new Cock());
		addSequential(new FollowProfile(toSpin));
		addSequential(new TurnWithGyro(AutoMap.turnForGoalDegrees));
		addSequential(new FollowProfile(toLowGoal));
		addSequential(new Shoot());
	}
}
