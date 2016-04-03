package org.usfirst.frc.team236.robot.commands.autonomous;

import org.usfirst.frc.team236.robot.commands.Intake;
import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.Wait;
import org.usfirst.frc.team236.robot.commands.arm.GoDefenseAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BackwardRawtonomous extends CommandGroup {

    public BackwardRawtonomous() {
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
	addParallel(new Intake());
	addParallel(new ShiftDown());
	addSequential(new GoDefenseAngle());

	addParallel(new Intake());
	addSequential(new RawDrive(-1), 2);

	addSequential(new Wait(1));
	addParallel(new Intake());
	addSequential(new TurnWithGyro(180));
    }
}
