package org.usfirst.frc.team236.robot.commands.autonomous;

import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.ShootCycle;
import org.usfirst.frc.team236.robot.commands.arm.SetArmAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShoot extends CommandGroup {

    public AutoShoot() {

	addSequential(new SetArmAngle(-6600 + RobotMap.ArmMap.CORNER_HIGH_SHOT_ANGLE));

	addSequential(new ShootCycle());
    }
}
