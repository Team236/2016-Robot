package org.usfirst.frc.team236.robot.motionProfile;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * 
 * @author samcf_000
 * @param _motorA A SpeedController to set speed
 * @param _motorB A SpeedController to set speed
 * @param _enc An Encoder to read distance from
 */
public class DriveSide implements ProfileSource, ProfileOutput {

	private SpeedController motorA;
	private SpeedController motorB;
	private Encoder enc;

	public DriveSide(SpeedController _motorA, SpeedController _motorB, Encoder _enc) {
		this.motorA = _motorA;
		this.motorB = _motorB;
		this.enc = _enc;
	}

	@Override
	public void setSpeed(double speed) {
		motorA.set(speed);
		motorB.set(speed);
	}

	@Override
	public double getDistance() {
		return enc.getDistance();
	}
}
