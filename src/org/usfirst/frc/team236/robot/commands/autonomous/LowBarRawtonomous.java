package org.usfirst.frc.team236.robot.commands.autonomous;

import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.arm.GoBottomTrust;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarRawtonomous extends CommandGroup {
	
	public LowBarRawtonomous() {
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
		addSequential(new ShiftDown());
		addSequential(new GoBottomTrust());
		addSequential(new RawDrive(-0.5), 4);
	}
}
