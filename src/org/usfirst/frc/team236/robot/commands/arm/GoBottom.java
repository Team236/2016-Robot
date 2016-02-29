package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;
import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoBottom extends Command {

	private static final double kP_down = RobotMap.ArmMap.downPID.kP;
	private static final double kI_down = RobotMap.ArmMap.downPID.kI;
	private static final double kD_down = RobotMap.ArmMap.downPID.kD;

	public GoBottom() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.arm.getPIDController().setPID(kP_down, kI_down, kD_down);
		Robot.arm.setSetpoint(-10.5);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.arm.bottomLimit.get() || Robot.arm.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.arm.stop();
		Robot.arm.zeroEncoder();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
