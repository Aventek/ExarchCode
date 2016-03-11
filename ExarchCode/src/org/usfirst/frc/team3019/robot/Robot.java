
package org.usfirst.frc.team3019.robot;

import org.usfirst.frc.team3019.robot.commands.AutonomousCommandGroup;
import org.usfirst.frc.team3019.robot.commands.*;
import org.usfirst.frc.team3019.robot.subsystems.*;
import org.usfirst.frc.team3019.robot.utilities.*;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {

	// instantiate networktable for vision tracking
	public static NetworkTable table;

	// instantiate all subsystems
	public static DriveTrain driveTrain;
	public static Lifter lifter;
	public static PIDDriving PIDDriving;
	public static PIDAngling PIDAngling;
	public static MXPBreakout mxpBreakout;
	public static Pneumatics pneumatics;
	public static Launcher launcher;
	public static OI oi;

	// using enumerations to control and switch states, and setting default
	// states
	public static LauncherState launcherState;
	public static AnglerState anglerState;
	public static DriveState driveState;
	public static SolenoidState solenoidState;
	public static ServoState servoState;
	public static LiftState liftState;
	
	public static CameraServer camServer;
	
	// autonomous command (not in use)
	Command autonomousCommand;

	SendableChooser chooser1;

	public Robot() {
		instantiateSubs();
		
		chooser1 = new SendableChooser();
		chooser1.addDefault("Go To", new AutonomousCommandGroup(AutonomousMode.GOTO));
		chooser1.addObject("Low Bar", new AutonomousCommandGroup(AutonomousMode.LOW_BAR));
		chooser1.addObject("Moat", new AutonomousCommandGroup(AutonomousMode.MOAT));
		chooser1.addObject("Ramparts", new AutonomousCommandGroup(AutonomousMode.RAMPART));
		chooser1.addObject("Rock Wall", new AutonomousCommandGroup(AutonomousMode.ROCKWALL));
		chooser1.addObject("Rought Terrain", new AutonomousCommandGroup(AutonomousMode.ROUGH_TERRAAIN));
		chooser1.addObject("Spy", new AutonomousCommandGroup(AutonomousMode.SPY));

	}

	public void robotInit() {
		launcherState = LauncherState.STILL;
		anglerState = AnglerState.STILL;
		driveState = DriveState.STILL;
		solenoidState = SolenoidState.OFF;
		servoState = ServoState.RETRACTED;
		liftState = LiftState.TELEOP;
		
		// instantiate all necessary items
		instantiateDashButtons();
		instantiateNetworkTable();
		
		// create OI last so buttons can do commands
		oi = new OI();

		Robot.pneumatics.soliOff();
	}

	private void instantiateNetworkTable() {

		// creating networktable for towertracking and putting testString
		table = NetworkTable.getTable("TowerTracker");
		table.putString("TEST", "isRunning");

	}

	private void instantiateDashButtons() {
		
		SmartDashboard.putData("PIDTurn", new PIDTurn());
		SmartDashboard.putData("PIDAngle", new PIDAngle());
		SmartDashboard.putData("ToggleSolenoid", new SolenoidToggle());
		SmartDashboard.putData("ServoPunch", new Jerk());

	}

	private void instantiateSubs() {

		driveTrain = new DriveTrain();
		pneumatics = new Pneumatics();
		mxpBreakout = new MXPBreakout();
		launcher = new Launcher();
		lifter = new Lifter();
		PIDDriving = new PIDDriving(0.3, 0.05, 0.5, 0);
		PIDAngling = new PIDAngling(0.1, 0.0, 0.05, 0);

	}

	public void disabledInit() {
		Robot.PIDAngling.disable();
		Robot.PIDDriving.disable();
		SmartDashboard.putData("Autonomous Mode", chooser1);
	}

	public void disabledPeriodic() {

		//normalize potentiometer angle from 1080 to 360 degrees
		Robot.launcher.potAngle = Robot.launcher.getPot() - RobotMap.ShooterAngleOfset;

		SmartDashboard.putData("Autonomous Mode", chooser1);
		visionProcessing();
		dashUpdate();
		
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		autonomousCommand = (AutonomousCommandGroup)chooser1.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
			driveState = DriveState.AUTO;
			liftState = LiftState.AUTO;
	}

	public void autonomousPeriodic() {
		Robot.launcher.potAngle = Robot.launcher.getPot() - RobotMap.ShooterAngleOfset;
		Robot.launcher.targetAngle = table.getNumber("targetAngle", 0);

		dashUpdate();
		visionProcessing();
		testLimitSwitch();
		
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		
		launcherState = LauncherState.STILL;
		anglerState = AnglerState.STILL;
		driveState = DriveState.STILL;
		solenoidState = SolenoidState.OFF;
		servoState = ServoState.RETRACTED;
		liftState = LiftState.TELEOP;
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	public void teleopPeriodic() {

		//normalize potentiometer angle from 1080 to 360 degrees
		Robot.launcher.potAngle = Robot.launcher.getPot() - RobotMap.ShooterAngleOfset;
		Robot.launcher.targetAngle = table.getNumber("targetAngle", 0);
		
		
		
		//can reset the potentiometer
		visionProcessing();
		dashUpdate();
		testLimitSwitch();
		Scheduler.getInstance().run();
	}


	private void testLimitSwitch() {
		if(!Robot.lifter.armLimitSwitch.get()){
			Robot.lifter.canGoDown = true;
		}else{
			Robot.lifter.canGoDown = false;
		}
	}

	private void dashUpdate() {
		//target Angle
		SmartDashboard.putNumber("visTargetAngle", Robot.launcher.targetAngle);
		SmartDashboard.putNumber("ShooterAngleOfset", RobotMap.ShooterAngleOfset);
		SmartDashboard.putNumber("servoPosition", Robot.launcher.pusher.get());
		// putting azimuthal to SmartDash
		SmartDashboard.putNumber("azimuth", RobotMap.angleOff);

		// put current states in smartDash
		SmartDashboard.putString("driveState", "" + driveState);
		SmartDashboard.putString("anglerState", "" + anglerState);
		SmartDashboard.putString("launcherState", "" + launcherState);
		SmartDashboard.putString("solenoidState", "" + solenoidState);
		SmartDashboard.putString("servoState", "" + servoState);

		// put pot value in smartDash
		SmartDashboard.putNumber("potReading", Robot.launcher.potAngle);

	}

	private void visionProcessing() {

		SmartDashboard.putNumber("distance", table.getNumber("VISdistance", 0));

		// fixing angleOff to be relative to forwards
		if (table.getNumber("VISazimuth", 0) > 180) {
			RobotMap.angleOff = 360 - table.getNumber("VISazimuth", 0);
		} else {
			RobotMap.angleOff = -table.getNumber("VISazimuth", 0);
		}

	}

	public void testPeriodic() {
		LiveWindow.run();
		Scheduler.getInstance().run();
	}

}
