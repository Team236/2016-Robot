package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
	private SpeedController motorLeft;
	private SpeedController motorRight;
	//private Encoder encoder;
	private DoubleSolenoid solenoid;
	
	

	public Shooter() {
		motorLeft = new Spark(RobotMap.ShooterMap.PWM_MOTOR_LEFT);
		motorLeft.setInverted(RobotMap.ShooterMap.INV_MOTOR_LEFT);
		
		motorRight = new Spark(RobotMap.ShooterMap.PWM_MOTOR_RIGHT);
		motorRight.setInverted(RobotMap.ShooterMap.INV_MOTOR_RIGHT);
		
		//encoder = new Encoder(RobotMap.ShooterMap.DIO_ENCODER_A, RobotMap.ShooterMap.DIO_ENCODER_B);
		//encoder.setReverseDirection(RobotMap.ShooterMap.INV_ENCODER);
		//encoder.setDistancePerPulse(RobotMap.ShooterMap.DISTANCE_PER_PULSE);
		
		solenoid = new DoubleSolenoid(RobotMap.ShooterMap.SOL_FORWARD, RobotMap.ShooterMap.SOL_REVERSE);
		
		
		
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void setSpeed(double speed) {
		motorLeft.set(speed);
		motorRight.set(speed);
		
	}
    public void setSol(int direction) {
    	if (direction == 1) {
    		solenoid.set(DoubleSolenoid.Value.kForward);
    	}
    	else if (direction == 0) {
    		solenoid.set(DoubleSolenoid.Value.kOff);
    	}
    	else if (direction == -1) {
    		solenoid.set(DoubleSolenoid.Value.kReverse);
    	}
    }
	//public double getDistance() {
		//return encoder.getDistance();
	//}
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    //public void zeroEncoder() {
    	//encoder.reset();
    //}
}

