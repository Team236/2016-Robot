package org.usfirst.frc.team236.robot.commands.arm;

import org.usfirst.frc.team236.robot.Robot;
import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoTop extends Command {
	
	private static final double kP_up = RobotMap.ArmMap.PID.kP;
	private static final double kI_up = RobotMap.ArmMap.PID.kI;
	private static final double kD_up = RobotMap.ArmMap.PID.kD;
	
	public GoTop() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.arm);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.arm.getPIDController().setPID(kP_up, kI_up, kD_up);
		Robot.arm.setSetpoint(RobotMap.ArmMap.MAX_ANGLE);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.arm.upperLimit.get() || Robot.arm.onTarget();
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
