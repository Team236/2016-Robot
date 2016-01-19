package org.usfirst.frc.team236.robot.motionProfile;

import java.util.ArrayList;

/**
 * A class that contains a list of elements. Represents a motion profile.
 * 
 * @author Sam
 *
 */
public class Profile {

	private ArrayList<Element> list = new ArrayList<Element>();

	public Element get(int index) {
		return list.get(index);
	}

	public void add(Element e) {
		list.add(e);
	}

	public int length() {
		return list.size();
	}
}
