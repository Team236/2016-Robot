package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;
import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoDefenseAngle extends Command {

    public boolean go;
    public boolean done;

    public GoDefenseAngle() {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	if (Robot.arm.getUpperLimit()) {
	    go = true;
	    Robot.arm.zeroEncoder();
	} else {
	    go = false;
	}
	done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	if (Robot.arm.getRawEncoder() < -4100) {
	    Robot.arm.stop();
	    done = true;
	} else {
	    Robot.arm.setSpeed(-0.3);
	    done = false;
	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return done;
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.arm.setSetpointRelative(0);
	Robot.arm.getPIDController().setPID(RobotMap.ArmMap.PID.kP, RobotMap.ArmMap.PID.kI, RobotMap.ArmMap.PID.kD);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
