package org.usfirst.frc.team236.robot.motionProfile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that contains a list of elements. Represents a motion profile.
 * 
 * @author Sam
 *
 */
public class Profile {

	private ArrayList<Element> list = new ArrayList<Element>();
	public ProfileParameters constants;

	public Element get(int index) {
		return list.get(index);
	}

	private void add(Element e) {
		list.add(e);
	}

	public int length() {
		return list.size();
	}

	private static final double dt = .02;
	private static final double maxTime = 30;

	public Profile(ProfileParameters params) {
		// There are seven stages in a motion profile
		int ACCEL_RAMP_UP = 1; // Acceleration gradually increases
		int CONST_ACCEL = 2; // Constant accel, speed gradually increases
		int ACCEL_MIRROR = 3; // We mirror the previous half of maxAccel
		int CONST_SPEED = 4; // Constant speed, main travel portion
		int MIRROR = 5; // Mirror the profile around the midpoint
		int stage = ACCEL_RAMP_UP;

		double midpoint = params.distance / 2;
		double velocityMidpoint = params.maxVelocity / 2;
		boolean atMidpoint = false;
		int maxSteps = (int) (maxTime / dt);
		int j = 0; // For mirroring
		boolean jSet = false;

		Element initialElement = new Element(0, 0, 0, 0);
		add(initialElement); // Create inital point in motion profile

		for (int i = 0; i < maxSteps; i++) {
			Element prevElement = get(i);

			// Determine where we are within the profile

			if (prevElement.position < params.distance) {
				break;// We're done
			}
			if (prevElement.acceleration < params.maxAccel) {
				stage = ACCEL_RAMP_UP;
			}
			if (prevElement.acceleration == params.maxAccel) {
				stage = CONST_ACCEL;
			}
			if (prevElement.speed >= velocityMidpoint) {
				stage = ACCEL_MIRROR;
				if (!jSet) {
					j = length() - 1;
					jSet = true;
				}
			}
			if (j <= 0) {
				stage = CONST_SPEED;
			}
			if (prevElement.speed == params.maxVelocity) {
				stage = CONST_SPEED;
			}
			if (prevElement.position == midpoint) {
				stage = MIRROR;
				atMidpoint = true;
				break;
			}

			Element e = new Element(); // Generate the new element

			// Give values to the new element
			if (stage == ACCEL_RAMP_UP) {
				e.jerk = params.maxJerk;
				e.acceleration = prevElement.acceleration + (params.maxJerk * dt);
				e.speed = prevElement.speed + (e.acceleration * dt);
				e.position = prevElement.position + (e.speed * dt);
			}
			if (stage == CONST_ACCEL) {
				e.jerk = 0;
				e.acceleration = params.maxAccel;
				e.speed = prevElement.speed + (e.acceleration * dt);
				e.position = prevElement.position + (e.speed * dt);
			}
			if (stage == ACCEL_MIRROR) {
				// This will probably break
				e.jerk = -get(j).jerk;
				e.acceleration = get(i).acceleration + e.jerk * dt;
				e.speed = get(i).speed + e.acceleration * dt;
				e.position = get(i).position + e.speed * dt;
				// decrement mirror counter
				j--;
			}
			add(e); // Adds the element we just made to the profile
		}
		if (!atMidpoint) {
			// If we break out before reaching the midpoint, something's bad.
			System.out.println("Profile Generation failed");
		}
		for (int k = length() - 1; k >= 0; k--) {
			// This will probably break too
			Element e = new Element();
			// e.jerk = p.get(k).jerk;
			int pSize = length() - 1;
			e.acceleration = -get(k).acceleration;
			e.speed = get(pSize).speed + e.acceleration * dt;
			e.position = get(pSize).position + e.speed * dt;
			add(e);
		}
	}

	public void store(String filename) {
		String data = "";
		String path = "C:\\Users\\samcf_000\\Documents\\" + filename + ".csv";

		for (int i = 0; i < length(); i++) {
			data += get(i).toString(); // Appends a list of comma-separated
										// values
			data += "\n"; // Creates a new line
		}

		try {
			File file = new File(path);
			FileWriter writer = new FileWriter(file);
			writer.write(data);
			writer.close();
		} catch (IOException exception) {
			System.out.println("File creation failed");
		}
	}
}
