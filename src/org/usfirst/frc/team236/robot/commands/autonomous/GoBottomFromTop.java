package org.usfirst.frc.team236.robot.commands.autonomous;

import org.usfirst.frc.team236.robot.Robot;
import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoBottomFromTop extends Command {
	private int i;
	private double startAngle;
	private double currentAngle;
	private double dAngle;
	private double estimateAngle;
	private double seconds;
	
	public GoBottomFromTop() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.arm);
	}
	
	// Called just before this Command runs the first time
// Called just before this Command runs the first time
	protected void initialize() {
		startAngle = Robot.arm.getAngle();
		i = 0;
		Robot.arm.disable();
		System.out.print("Arm coming down");
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		currentAngle = Robot.arm.getAngle(); // Get reported angle of the arm
		dAngle = currentAngle - startAngle; // Calculate change in arm angle
		estimateAngle = RobotMap.ArmMap.MAX_ANGLE + dAngle; // Guess the angle
		
		if (estimateAngle > 60) {
			Robot.arm.setSpeed(-0.25);
		} else {
			Robot.arm.setSpeed(-0.1);
		}
		i++;
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		seconds = i / 50; // Calculate how many seconds the command has run for
		return (estimateAngle < 10 && seconds > 3) || Robot.arm.getBottomLimit();
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
