
package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.autonomous.CrossLowBar;
import org.usfirst.frc.team236.robot.commands.autonomous.DoNothing;
import org.usfirst.frc.team236.robot.commands.autonomous.HighShot;
import org.usfirst.frc.team236.robot.commands.autonomous.LowShot;
import org.usfirst.frc.team236.robot.commands.autonomous.Reach;
import org.usfirst.frc.team236.robot.subsystems.Arm;
import org.usfirst.frc.team236.robot.subsystems.Drive;
import org.usfirst.frc.team236.robot.subsystems.Intake;
import org.usfirst.frc.team236.robot.subsystems.Shooter;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import motionProfile.Profile;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// Instantiate subsystems
	public static final Drive drive = new Drive();
	public static final Intake intake = new Intake();
	public static final Arm arm = new Arm();
	public static final Shooter shooter = new Shooter();

	public static OI oi;

	Command autonomousCommand;
	SendableChooser chooser;
	CameraServer camera;
	Compressor compressor;
	public static AHRS navx;
	PowerDistributionPanel pdp;

	// Motion Profiles
	public Profile crossLowGoal;
	public Profile reach;
	public Profile toShoot;
	public Profile toLowGoal;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();

		// Generate profiles
		crossLowGoal = new Profile(AutoMap.cross);
		reach = new Profile(AutoMap.reach);
		toShoot = new Profile(AutoMap.toShoot);
		toLowGoal = new Profile(AutoMap.toLowGoal);

		chooser = new SendableChooser();
		chooser.addDefault("Do Nothing", new DoNothing());
		chooser.addDefault("High Shot", new HighShot(toShoot));
		chooser.addDefault("Low Shot", new LowShot(toShoot, toLowGoal));
		chooser.addObject("Cross - low bar", new CrossLowBar(crossLowGoal));
		chooser.addObject("Reach", new Reach(reach));
		SmartDashboard.putData("Auto mode", chooser);

		// Start Camera feed
		camera = CameraServer.getInstance();
		camera.startAutomaticCapture(RobotMap.CAMERA_NAME);

		// Start Compressor
		compressor = new Compressor();
		compressor.start();

		// Start NavX
		navx = new AHRS(SPI.Port.kMXP);

		// Automatically set drive in high gear
		new ShiftDown();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		Robot.drive.zeroEncoders();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		// SmartDashboard
		SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
		SmartDashboard.putNumber("Battery Voltage", DriverStation.getInstance().getBatteryVoltage());
		//SmartDashboard.putNumber("PDP Voltage", pdp.getVoltage());

		SmartDashboard.putNumber("Arm angle", Robot.arm.getAngle());

		SmartDashboard.putNumber("Left encoder", Robot.drive.getLeftDistance());
		SmartDashboard.putNumber("Right encoder", Robot.drive.getRightDistance());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void testInit() {
		LiveWindow.addActuator("Arm", "Arm", arm.getPIDController());
	}
}
