package org.usfirst.frc.team236.robot.motionProfile;

import org.usfirst.frc.team236.robot.Robot;

public class DriveSide {

	public final String side;
	public final String left = "left";
	public final String right = "right";

	public DriveSide(String side) {
		if (side == "left") {
			this.side = "left";
		} else if (side == "right") {
			this.side = "right";
		} else {
			this.side = null;
			throw new IllegalArgumentException("Enter either 'left' or 'right");
		}
	}

	public void setSpeed(double speed) {
		if (side == left) {
			Robot.drive.setLeftSpeed(speed);
		} else if (side == right) {
			Robot.drive.setRightSpeed(speed);
		}
	}

	public double getDistance() {
		if (side == left) {
			return Robot.drive.getLeftDistance();
		} else if (side == right) {
			return Robot.drive.getRightDistance();
		} else {
			return 0;
		}
	}

}
