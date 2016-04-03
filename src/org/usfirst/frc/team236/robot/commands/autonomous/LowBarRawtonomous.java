package org.usfirst.frc.team236.robot.commands.autonomous;

import org.usfirst.frc.team236.robot.commands.Intake;
import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.arm.GoBottomFromTop;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarRawtonomous extends CommandGroup {

    public LowBarRawtonomous() {
	addParallel(new Intake());
	addSequential(new ShiftDown());
	addSequential(new GoBottomFromTop());
	addSequential(new RawDrive(-0.5), 4.0);
    }
}
