package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @param _goal
 *            The angle for the arm to go to.
 */
public class SetArmAngle extends Command {
    private double goal;
    private final double margin = 20;

    public SetArmAngle(double _goal) {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.arm);
	this.goal = _goal;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.arm.setSetpoint(goal);
	Robot.arm.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	// TODO ask for low speed
	return Math.abs(Robot.arm.getPIDController().getError()) < margin;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
