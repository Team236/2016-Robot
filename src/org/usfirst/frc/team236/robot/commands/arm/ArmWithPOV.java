package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmWithPOV extends Command {

    public ArmWithPOV() {
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
    	if (Robot.oi.controller.getPOV(0) == 0) {
    		Robot.arm.setSpeed(0.5);
    	} else if (Robot.oi.controller.getPOV(0) == 180) {
    		Robot.arm.setSpeed(-0.5);
    	} else if (Robot.oi.controller.getPOV(0) == -1) {
    		Robot.arm.stop();
    	}
    	if (Robot.arm.upperLimit.get()) {
    		Robot.arm.stop();
    	} else if (Robot.arm.bottomLimit.get()) {
    		Robot.arm.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.enable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.enable();
    }
}
