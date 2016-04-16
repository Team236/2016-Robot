package org.usfirst.frc.team236.robot.commands.autonomous;

import org.usfirst.frc.team236.robot.commands.Intake;
import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.ShiftUp;
import org.usfirst.frc.team236.robot.commands.Wait;
import org.usfirst.frc.team236.robot.commands.arm.GoDefenseAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BackwardRawtonomous extends CommandGroup {

    public BackwardRawtonomous() {

	addParallel(new Intake());
	addParallel(new ShiftDown());
	addSequential(new GoDefenseAngle());

	addParallel(new Intake());
	addSequential(new RawDrive(-1), 2);

	addParallel(new ShiftUp());
	addSequential(new Wait(1));

	addParallel(new Intake());
	addSequential(new TurnWithGyro(180));
    }
}
