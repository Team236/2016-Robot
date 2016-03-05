package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RawArmDown extends Command {

    public RawArmDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.disable();
    	System.out.print("Arm coming down");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.arm.getAngle() > 75) {
    		Robot.arm.setSpeed(-0.2);
    	} else {
    		Robot.arm.setSpeed(-0.05);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.arm.getBottomLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.stop();
    	Robot.arm.setSetpointRelative(0);
    	Robot.arm.enable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
