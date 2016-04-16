package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GoBottomFromTop extends Command {
    public boolean go;
    public boolean done;

    public GoBottomFromTop() {
	requires(Robot.arm);
    }

    protected void initialize() {
	if (Robot.arm.getUpperLimit()) {
	    go = true;
	    Robot.arm.zeroEncoder();
	} else {
	    go = false;
	}
	done = false;
	Robot.arm.disable();
    }

    protected void execute() {
	if (Robot.arm.getRawEncoder() < -6500) {
	    Robot.arm.stop();
	    done = true;
	} else {
	    Robot.arm.setSpeed(-0.3);
	    done = false;
	}
    }

    protected boolean isFinished() {
	return (done) || (Robot.arm.getBottomLimit());
    }

    protected void end() {
	Robot.arm.stop();
	Robot.arm.setSetpointRelative(0);
	Robot.arm.enable();
    }

    protected void interrupted() {
	end();
    }
}
