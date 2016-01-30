# 2016-Robot [![Build Status](https://travis-ci.com/Team236/2016-Robot.svg?token=4mPK9HonddnzMs2vmEpY&branch=master)](https://travis-ci.com/Team236/2016-Robot)
Team 236's 2016 bot for FRC Stronghold

### Goals
This robot code has hardly begun. Here are some of my goals for this season.
Best case scenario, I can change the header above to "Features" by the end of
the season.

#### Motion Profiles for Autonomous
The team (me included) has agreed that auto mode is very important this year.
The dream is to have the robot cross the low bar and score a ball in the low 
goal. I think that is extremely optimistic, not least because of the inherent
inaccuracy in turning, even with gyros. I'll definitely be doing my best to
make it happen.

#### Smooth PID control
I've worked with PID Subsystems before, so that's not a terrible challenge.
However, it becomes significantly more difficult when we need to incorporate
PID control into the robot in ways WPIlib doesn't support.

### Ports
Ports are imported from RobotMap throughout the code.

##### PWM

| Port | Subsystem | Assignment
|------|-----------|-----------
| 0 | Drive | Left Front
| 1 | Drive | Left Back
| 2 | Drive | Right Front
| 3 | Drive | Right Back
| 4 | Intake| Intake
| 5 | Arm   | Pivot

##### DIO

| Port | Subsystem | Assignment
|------|-----------|-----------
| 0 | Drive | Left Encoder, channel A
| 1 | Drive | Left Encoder, channel B
| 2 | Drive | Right Encoder, channel A
| 3 | Drive | Right Encoder, channel B
| 4 | Arm   | Pivot

##### USB
| Port | Assignment
|------|-----------
| 0 | Left Joystick
| 1 | Right Joystick
| 2 | Controller
