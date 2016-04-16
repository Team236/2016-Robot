package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.ShootCycle;
import org.usfirst.frc.team236.robot.commands.autonomous.AutoShoot;
import org.usfirst.frc.team236.robot.commands.autonomous.BackwardRawtonomous;
import org.usfirst.frc.team236.robot.commands.autonomous.DoNothing;
import org.usfirst.frc.team236.robot.commands.autonomous.ForwardRawtonomous;
import org.usfirst.frc.team236.robot.commands.autonomous.LowBarRawtonomous;
import org.usfirst.frc.team236.robot.commands.profiled.CrossLowBar;
import org.usfirst.frc.team236.robot.commands.profiled.Reach;
import org.usfirst.frc.team236.robot.subsystems.Arm;
import org.usfirst.frc.team236.robot.subsystems.Drive;
import org.usfirst.frc.team236.robot.subsystems.Hanger;
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

public class Robot extends IterativeRobot {

    // Subsystems
    public static Drive drive;
    public static Intake intake;
    public static Arm arm;
    public static Shooter shooter;
    public static Hanger hanger;
    public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;
    CameraServer server;
    USBCamera camera;
    Compressor compressor;
    public static AHRS navx;

    // Profiles
    public Profile crossLowBar;
    public Profile reach;
    public Profile toShoot;
    public Profile toLowGoal;
    public Profile bigCross;

    public void robotInit() {

	// Create subsystems
	drive = new Drive();
	intake = new Intake();
	arm = new Arm();
	shooter = new Shooter();

	oi = new OI();

	// Create motion profiles
	crossLowBar = new Profile(AutoMap.crossLowBar);
	reach = new Profile(AutoMap.reach);
	toShoot = new Profile(AutoMap.toShoot);
	toLowGoal = new Profile(AutoMap.toLowGoal);

	// Pick an auto
	chooser = new SendableChooser();
	chooser.addDefault("Do Nothing", new DoNothing());
	chooser.addObject("Low Bar", new LowBarRawtonomous());
	chooser.addObject("Low Bar (profile)", new CrossLowBar(crossLowBar));
	chooser.addObject("Reach", new Reach(reach));
	chooser.addObject("Forward Rawto", new ForwardRawtonomous());
	chooser.addObject("Backward Rawto", new BackwardRawtonomous());
	chooser.addObject("Shoot", new AutoShoot());
	SmartDashboard.putData("Auto mode", chooser);

	// Start camera stream
	camera = new USBCamera();
	server = CameraServer.getInstance();
	server.setQuality(40);
	server.startAutomaticCapture(camera);

	// Start compressor
	compressor = new Compressor();
	compressor.start();

	navx = new AHRS(SPI.Port.kMXP);
    }

    public void disabledInit() {
	SmartDashboard.putData("Auto mode", chooser);
    }

    public void disabledPeriodic() {
	Scheduler.getInstance().run();
	if (arm.getBottomLimit()) {
	    arm.zeroEncoder();
	}

	SmartDashboard.putNumber("Left", drive.getLeftDistance());
	SmartDashboard.putNumber("Right", drive.getRightDistance());
    }

    public void autonomousInit() {
	drive.zeroEncoders();
	arm.setSetpointRelative(0);
	autonomousCommand = ((Command) chooser.getSelected());
	if (autonomousCommand != null) {
	    autonomousCommand.start();
	}
	Updater.getInstance().initControllers();
    }

    public void autonomousPeriodic() {
	Scheduler.getInstance().run();

	Updater.getInstance().updateAll();

	SmartDashboard.putNumber("Velocity", drive.leftSide.getSpeed());
	SmartDashboard.putNumber("Left", drive.getLeftDistance());
	SmartDashboard.putNumber("Right", drive.getRightDistance());
	SmartDashboard.putNumber("Arm angle", arm.getRawEncoder());
    }

    public void teleopInit() {
	if (autonomousCommand != null) {
	    autonomousCommand.cancel();
	}
	drive.zeroEncoders();
	arm.setSetpointRelative(0);
    }

    public void teleopPeriodic() {
	Scheduler.getInstance().run();

	SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());

	SmartDashboard.putNumber("Arm angle", arm.getRawEncoder());

	SmartDashboard.putNumber("Left", drive.getLeftDistance());
	SmartDashboard.putNumber("Right", drive.getRightDistance());

	SmartDashboard.putBoolean("Bottom Limit", arm.getBottomLimit());
	SmartDashboard.putBoolean("Upper Limit", arm.getUpperLimit());

	SmartDashboard.putNumber("Velocity", drive.leftSide.getSpeed());

	SmartDashboard.putBoolean("Ball", intake.getLimit());

	// Hackish way to shoot with both triggers
	if (oi.leftStick.getRawButton(1) && oi.rightStick.getRawButton(1)) {
	    new ShootCycle().start();
	}

	// Hackish way to ensure we end in low gear
	if (DriverStation.getInstance().getMatchTime() <= 1) {
	    new ShiftDown().start();
	}
    }

    public void testPeriodic() {
    }

    public void testInit() {
	arm.setSetpointRelative(0);
	LiveWindow.addActuator("Arm", "Arm", arm.getPIDController());
    }
}
