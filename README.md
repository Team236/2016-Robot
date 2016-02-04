# 2016-Robot [![Build Status](https://travis-ci.com/Team236/2016-Robot.svg?token=4mPK9HonddnzMs2vmEpY&branch=master)](https://travis-ci.com/Team236/2016-Robot)
Team 236's 2016 bot for FRC Stronghold

### Goals
Here are some of my goals for this season. Best case scenario, I can change the header above to "Features" by the end of
the season.

#### Motion Profiles for Autonomous
The MotionProfile code can now automatically generate smooth and continuous motion profiles based on profile parameters.
The robot can (probably) follow these profiles too, using appropriate commands. This needs testing, but we're really
happy with where we are. More on how this works in the wiki.

#### Smooth PID control
PID was very easy. All we needed PID for was the arm control, so virtually no additional code for us. This also needs
to be tested, but that's a hardware problem.

#### Gyro control
We recently got a brand new NavX MXP gyro, primarily for use in the autonomous mode. we've been able to get readings,
and controlling the robot based on these readings is just a step away.

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
