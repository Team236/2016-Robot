package org.usfirst.frc.team236.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootCycle extends CommandGroup {
    public ShootCycle() {
	addSequential(new Shoot());
	addSequential(new Wait(0.0D));
	addSequential(new Cock());
    }
}
