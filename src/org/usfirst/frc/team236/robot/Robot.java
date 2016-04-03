package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.ShiftDown;
import org.usfirst.frc.team236.robot.commands.autonomous.BackwardRawtonomous;
import org.usfirst.frc.team236.robot.commands.autonomous.DoNothing;
import org.usfirst.frc.team236.robot.commands.autonomous.ForwardRawtonomous;
import org.usfirst.frc.team236.robot.commands.autonomous.LowBarRawtonomous;
import org.usfirst.frc.team236.robot.commands.profiled.BumpyCross;
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

public class Robot extends IterativeRobot {
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
    public Profile crossLowBar;
    public Profile reach;
    public Profile toShoot;
    public Profile toLowGoal;
    public Profile bigCross;

    public void robotInit() {
	drive = new Drive();
	intake = new Intake();
	arm = new Arm();
	shooter = new Shooter();

	oi = new OI();

	crossLowBar = new Profile(AutoMap.crossLowBar);
	reach = new Profile(AutoMap.reach);
	toShoot = new Profile(AutoMap.toShoot);
	toLowGoal = new Profile(AutoMap.toLowGoal);
	bigCross = new Profile(AutoMap.bigCross);

	chooser = new SendableChooser();
	chooser.addDefault("Do Nothing", new DoNothing());
	chooser.addObject("Moat", new BumpyCross(bigCross));
	chooser.addObject("Ramparts", new BumpyCross(bigCross));
	chooser.addObject("Rock Wall", new BumpyCross(bigCross));
	chooser.addObject("Rough Terrain", new BumpyCross(bigCross));
	chooser.addObject("Low Bar", new LowBarRawtonomous());
	chooser.addObject("Reach", new Reach(reach));
	chooser.addObject("Forward Rawto", new ForwardRawtonomous());
	chooser.addObject("Backward Rawto", new BackwardRawtonomous());
	SmartDashboard.putData("Auto mode", chooser);

	camera = new USBCamera();

	server = CameraServer.getInstance();
	server.setQuality(40);
	server.startAutomaticCapture(camera);

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
    }

    public void autonomousInit() {
	drive.zeroEncoders();
	arm.setSetpointRelative(0.0D);
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
	SmartDashboard.putNumber("Arm angle", arm.getRawEncoder());
    }

    public void teleopInit() {
	if (autonomousCommand != null) {
	    autonomousCommand.cancel();
	}
	drive.zeroEncoders();
	arm.setSetpointRelative(0.0D);
    }

    public void teleopPeriodic() {
	Scheduler.getInstance().run();

	SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
	SmartDashboard.putNumber("Battery Voltage", DriverStation.getInstance().getBatteryVoltage());

	SmartDashboard.putNumber("Arm angle", arm.getRawEncoder());

	SmartDashboard.putNumber("Left encoder", drive.getLeftDistance());
	SmartDashboard.putNumber("Right encoder", drive.getRightDistance());

	SmartDashboard.putBoolean("Bottom Limit", arm.getBottomLimit());
	SmartDashboard.putBoolean("Upper Limit", arm.getUpperLimit());

	SmartDashboard.putNumber("Velocity", drive.leftSide.getSpeed());

	SmartDashboard.putBoolean("Ball", intake.getLimit());
	if (DriverStation.getInstance().getMatchTime() <= 1.0D) {
	    new ShiftDown();
	}
    }

    public void testPeriodic() {
    }

    public void testInit() {
	arm.setSetpointRelative(0.0D);
	LiveWindow.addActuator("Arm", "Arm", arm.getPIDController());
    }
}
