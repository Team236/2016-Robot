package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;
import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualArmUp extends Command {
	private double setPoint;

    public ManualArmUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setPoint = Robot.arm.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	setPoint = Robot.arm.getAngle() + RobotMap.ArmMap.MAN_INCREMENT;
    	Robot.arm.setSetpoint(setPoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
