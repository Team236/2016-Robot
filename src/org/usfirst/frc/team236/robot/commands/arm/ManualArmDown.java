package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualArmDown extends Command {

	public ManualArmDown() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.arm.disable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.arm.setSpeed(-0.5);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.arm.limitSwitchBottom.get();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.arm.enable();
		Robot.arm.setSetpoint(Robot.arm.getAngle());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.arm.enable();
		Robot.arm.setSetpoint(Robot.arm.getAngle());
	}
}
