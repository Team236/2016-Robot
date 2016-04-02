
package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.autonomous.BackwardRawtonomous;
import org.usfirst.frc.team236.robot.commands.autonomous.DoNothing;
import org.usfirst.frc.team236.robot.commands.autonomous.ForwardRawtonomous;
import org.usfirst.frc.team236.robot.commands.profiled.BumpyCross;
import org.usfirst.frc.team236.robot.commands.profiled.CrossLowBar;
import org.usfirst.frc.team236.robot.commands.profiled.Reach;
import org.usfirst.frc.team236.robot.subsystems.Arm;
import org.usfirst.frc.team236.robot.subsystems.Drive;
import org.usfirst.frc.team236.robot.subsystems.Intake;
import org.usfirst.frc.team236.robot.subsystems.Shooter;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;
import motionProfile.Profile;
import updater.Updater;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    // Instantiate subsystems
    public static Drive drive;
    public static Intake intake;
    public static Arm arm;
    public static Shooter shooter;

    public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;

    CameraServer server;
    USBCamera camera;

    Compressor compressor;

    public static AHRS navx;

    // Motion Profiles
    public Profile crossLowBar;
    public Profile reach;
    public Profile toShoot;
    public Profile toLowGoal;
    public Profile bigCross;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	drive = new Drive();
	intake = new Intake();
	arm = new Arm();
	shooter = new Shooter();

	oi = new OI();

	// Generate profiles
	crossLowBar = new Profile(AutoMap.crossLowBar);
	reach = new Profile(AutoMap.reach);
	toShoot = new Profile(AutoMap.toShoot);
	toLowGoal = new Profile(AutoMap.toLowGoal);
	bigCross = new Profile(AutoMap.bigCross);

	// Choose auto mode
	chooser = new SendableChooser();
	chooser.addDefault("Do Nothing", new DoNothing());
	chooser.addObject("Moat", new BumpyCross(bigCross));
	chooser.addObject("Ramparts", new BumpyCross(bigCross));
	chooser.addObject("Rock Wall", new BumpyCross(bigCross));
	chooser.addObject("Rough Terrain", new BumpyCross(bigCross));
	chooser.addObject("Low Bar", new CrossLowBar(crossLowBar));
	chooser.addObject("Reach", new Reach(reach));
	chooser.addObject("Forward Rawto", new ForwardRawtonomous());
	chooser.addObject("Backward Rawto", new BackwardRawtonomous());
	SmartDashboard.putData("Auto mode", chooser);

	// Start Camera feed
	camera = new USBCamera();

	server = CameraServer.getInstance();
	server.setQuality(40);
	server.startAutomaticCapture(camera);

	// Start Compressor
	compressor = new Compressor();
	compressor.start();

	// Start NavX
	navx = new AHRS(SPI.Port.kMXP);
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    public void disabledInit() {
	SmartDashboard.putData("Auto mode", chooser);
    }

    public void disabledPeriodic() {
	Scheduler.getInstance().run();
	if (arm.getBottomLimit()) {
	    arm.zeroEncoder();
	}
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
	drive.zeroEncoders();
	arm.setSetpointRelative(0);
	autonomousCommand = (Command) chooser.getSelected();

	// Schedule the autonomous command
	if (autonomousCommand != null)
	    autonomousCommand.start();

	Updater.getInstance().initControllers();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
	Scheduler.getInstance().run();

	Updater.getInstance().updateAll();

	SmartDashboard.putNumber("Velocity", Robot.drive.leftSide.getSpeed());
	SmartDashboard.putNumber("Arm angle", Robot.arm.getRawEncoder());
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
	// teleop starts running. If you want the autonomous to
	// continue until interrupted by another command, remove
	// this line or comment it out.
	if (autonomousCommand != null)
	    autonomousCommand.cancel();

	Robot.drive.zeroEncoders();
	Robot.arm.setSetpointRelative(0);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
	Scheduler.getInstance().run();
	// SmartDashboard
	SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
	SmartDashboard.putNumber("Battery Voltage", DriverStation.getInstance().getBatteryVoltage());

	SmartDashboard.putNumber("Arm angle", Robot.arm.getRawEncoder());

	SmartDashboard.putNumber("Left encoder", Robot.drive.getLeftDistance());
	SmartDashboard.putNumber("Right encoder", Robot.drive.getRightDistance());

	SmartDashboard.putBoolean("Bottom Limit", Robot.arm.getBottomLimit());
	SmartDashboard.putBoolean("Upper Limit", Robot.arm.getUpperLimit());

	SmartDashboard.putNumber("Velocity", Robot.drive.leftSide.getSpeed());

	if (DriverStation.getInstance().getMatchTime() <= 1) {
	    new ShiftDown();
	}
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
	LiveWindow.run();
    }

    public void testInit() {
	arm.setSetpointRelative(0);
	LiveWindow.addActuator("Arm", "Arm", arm.getPIDController());
    }
}
