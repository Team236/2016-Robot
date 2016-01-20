package org.usfirst.frc.team236.robot;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Updater {
	static Updater updater; // Only one instance of this class allowed
	ThreadedUpdater threadedUpdater; // Creates the threaded updater
	public boolean controlLoopsStarted = false;

	// List of objects scheduled for updating
	ArrayList<Updatable> updatables = new ArrayList<Updatable>();

	private Updater() {
		threadedUpdater = new ThreadedUpdater();
	}

	private class ThreadedUpdater extends TimerTask {
		// List of updatables that get their own thread
		ArrayList<Updatable> threadedUpdatables = new ArrayList<Updatable>();

		// Adds a new updatable with its own thread
		public void add(Updatable addition) {
			threadedUpdatables.add(addition);
		}

		// This is called automatically by the timer
		@Override
		public void run() {
			for (int i = 0; i < threadedUpdatables.size(); i++) {
				threadedUpdatables.get(i).update();
			}
		}
	}

	public static Updater getInstance() {
		// Don't instantiate the class until we need to
		if (updater == null) {
			updater = new Updater();
		}
		return updater;
	}

	public void start() {
		if (!controlLoopsStarted) {
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(threadedUpdater, 0, 20);
		}
		controlLoopsStarted = true;
	}

	public void addUpdatable(Updatable addition) {
		updatables.add(addition);
	}

	public void addThreadedUpdatable(Updatable addition) {
		threadedUpdater.add(addition);
	}

	public void updateAll() {
		for (int i = 0; i < updatables.size(); i++) {
			updatables.get(i).update();
		}
	}

}
