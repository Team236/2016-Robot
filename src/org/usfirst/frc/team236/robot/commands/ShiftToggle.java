package org.usfirst.frc.team236.robot.commands;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Toggles between gears. Goes to high gear if in low, and vice versa.
 */
public class ShiftToggle extends CommandGroup {

    public ShiftToggle() {
	// Add Commands here:
	// e.g. addSequential(new Command1());
	// addSequential(new Command2());
	// these will run in order.

	// To run multiple commands at the same time,
	// use addParallel()
	// e.g. addParallel(new Command1());
	// addSequential(new Command2());
	// Command1 and Command2 will run in parallel.

	// A command group will require all of the subsystems that each member
	// would require.
	// e.g. if Command1 requires chassis, and Command2 requires arm,
	// a CommandGroup containing them would require both the chassis and the
	// arm.
	if (Robot.drive.getGear() == 0) {
	    addSequential(new ShiftUp());
	} else if (Robot.drive.getGear() == 1) {
	    addSequential(new ShiftDown());
	}
    }
}
