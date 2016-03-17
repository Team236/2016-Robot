package org.usfirst.frc.team236.robot.commands.profiled;

import org.usfirst.frc.team236.robot.commands.Cock;
import org.usfirst.frc.team236.robot.commands.Shoot;
import org.usfirst.frc.team236.robot.commands.autonomous.AimWithVision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import motionProfile.Profile;

public class HighShotWithVision extends CommandGroup {

	public HighShotWithVision(Profile profile) {
		addParallel(new Cock());
		addSequential(new AimWithVision());
		addSequential(new Shoot());
	}
}
