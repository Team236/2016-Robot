package org.usfirst.frc.team236.robot.commands.autonomous;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class AimWithVision extends Command {

	private NetworkTable ntserver = NetworkTable.getTable("SmartDashboard");
	private int step;
	private boolean isComplete;

	////////////////////////////////////////////
	// Vision data
	
	// Constants
	private final double ImageW = 640;				// Camera Image Width
	private final double ImageH = 360;				// Camera Image Height
	private final double FoV = 68.5;				// Camera horizontal field-of-view
	private final double CameraPivotHeight = 0.3;	// Height of camera above ground in meters
	private final double CameraArmLength = 0.7;		// Length of camera arm
	private final double TargetHeight = 8.0*0.3048;	// Height of center of target in meters
	private final double G = 9.801;					// Gravity constant in m/s/s
	private final double Vfire = 15.0;				// Firing velocity, in m/s
	// Computed
	private double aspect;
	private double vFoV;
	private double Vfire2;

	////////////////////////////////////////////
	// Turning data

	// Constants
	// How close we have to get (in degrees) to targetHeading before we're satisfied 
	// Depending on how fast the robot turns, this number may need to be increased to get it to stop turning;
	// however, the larger the number, the greater the error.  We want the smallest number we can get where
	// the robot will stop on its own without oscillating.
	private final double turnTolerance = 0.15;		
	// Conversion factor for our difference in degrees to the speed
	// we're going to ask for.  At this value or more, we will ask
	// for full turning speed, which will scale down to 0 at 0 degrees.
	// This value should be tuned if the robot turns too jerky or too slow.
	// Increasing this number will slow the robot down; decreasing it will speed the robot up.
	private final double turnDegreesToSpeedScale = 30.0;	
	
	// Orders
	// The angle we've been asked to turn to, according to our gyro
	private double targetHeading;	
	// Our current angle, according to our gyro
	private double currentHeading;	

	
	////////////////////////////////////////////
	// Elevation data
	
	// Constants
	// How close we have to get (in degrees) to targetHeading before we're satisfied 
	// Depending on how fast the arm moves, this number may need to be increased to get it to stop moving;
	// however, the larger the number, the greater the error.  We want the smallest number we can get where
	// the arm will stop on its own without oscillating.
	private final double elevTolerance = 0.15;		
	// Conversion factor for our difference in degrees to the speed
	// we're going to ask for.  At this value or more, we will ask
	// for full turning speed, which will scale down to 0 at 0 degrees.
	private final double elevDegreesToSpeedScale = 30.0;	
	
	// Orders
	// The angle we've been asked to turn to, according to our gyro
	private double targetElevation;	
	// Our current angle, according to our gyro
	private double currentElevation;	

	
	public AimWithVision(){
    	requires(Robot.drive);
    	requires(Robot.arm);
	}
	
	@Override
	protected void initialize() {
		step = 0;
		isComplete = false;
		
		aspect = ImageW/ImageH;
		vFoV = FoV/aspect;
		Vfire2 = Vfire*Vfire;
	}

	@Override
	protected void execute() {
		switch (step){
		case 0:	// Look
			// Waiting for our first successful recognition
			if (updateVision()){
				step++;
			}
			break;
		case 1:	// Aim 1
			{
				boolean turnComplete = updateTurn();
				boolean elevComplete = updateElevation();
				if (turnComplete && elevComplete){
					// Look again
					if (updateVision()){
						step++;
					}
				}
			}
			break;
		case 2: // Aim 2
			{
				boolean turnComplete = updateTurn();
				boolean elevComplete = updateElevation();
				if (turnComplete && elevComplete){
					step++;
				}
			}
			break;
		case 3: // Fire!
			if (Robot.shooter.isCocked()){
				isComplete = true;
				step++;
			}
			break;
		}
	}

	protected boolean updateVision(){
		boolean reslt = false;
		// Get the values we need from current vision results
		try
		{
			final NumberArray targetNum = new NumberArray();
			ntserver.retrieveValue("BLOBS", targetNum);
			if (targetNum.size()>0)
			{
				double blobx = targetNum.get(0);
				double bloby = targetNum.get(1);
				
				// Compute firing angle
				double baseCameraAngle = Robot.arm.getAngle();
				double targetInclineAngle = baseCameraAngle + vFoV*(bloby - ImageH/2)*ImageH;
				
				double cameraHeight = CameraPivotHeight + CameraArmLength*Math.sin(Math.toRadians(baseCameraAngle));
				double towerRange = (TargetHeight - cameraHeight)/Math.tan(Math.toRadians(targetInclineAngle));
				
				double firingAngle = Math.toDegrees(Math.atan(Vfire2/(G*towerRange)-Math.sqrt(Vfire2*(Vfire2-2.0*G*(TargetHeight-cameraHeight))/(G*G*towerRange*towerRange)-1)));
				Elevate(firingAngle);
				
				// Compute turn angle
				double targetTurnAngle = FoV*(blobx-ImageW/2)*ImageW;
				Turn(targetTurnAngle);
				
				reslt = true;
			}
		}
		catch (TableKeyNotDefinedException exp)
		{
		}		
		return reslt;
	}
	
	private void Turn(double firingAngle) {
    	currentHeading = Robot.navx.getRawGyroY();
    	targetHeading = currentHeading + firingAngle;	
	}
	
	private boolean updateTurn() {
		boolean turnComplete = false;
		
    	currentHeading = Robot.navx.getRawGyroY();
    	if (Math.abs(targetHeading - currentHeading) > turnTolerance){
    		// Still not within tolerance; convert the error to a turn speed
	    	double targetSpd = turnDegreesToSpeedScale * (targetHeading - currentHeading);
	    	// And keep us turning
	    	Robot.drive.setLeftSpeed(targetSpd);
	    	Robot.drive.setRightSpeed(-targetSpd);
    	} else {
    		// Within tolerance, we're good!
    		turnComplete = true;
    		Robot.drive.stop();
    	}
    	
        return turnComplete;
	}

	private void Elevate(double firingAngle) {
		currentElevation = Robot.arm.getAngle();
		targetElevation = firingAngle;
	}

	private boolean updateElevation() {
		boolean elevComplete = false;
		
    	currentHeading = Robot.navx.getRawGyroY();
    	if (Math.abs(targetElevation - currentElevation) > elevTolerance){
    		// Still not within tolerance; convert the error to a turn speed
	    	double targetSpd = elevDegreesToSpeedScale * (targetElevation - currentElevation);
	    	// And keep us turning
	    	Robot.arm.setSpeed(targetSpd);
    	} else {
    		// Within tolerance, we're good!
    		elevComplete = true;
    		Robot.arm.stop();
    	}
    	
        return elevComplete;
	}


	@Override
	protected boolean isFinished() {
		return isComplete;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
