
package org.usfirst.frc.team3019.robot;

import org.usfirst.frc.team3019.robot.commands.Solenoids;
import org.usfirst.frc.team3019.robot.commands.FalconPunch;
import org.usfirst.frc.team3019.robot.commands.PIDAngle;
import org.usfirst.frc.team3019.robot.commands.PIDTurn;
import org.usfirst.frc.team3019.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3019.robot.subsystems.Launcher;
import org.usfirst.frc.team3019.robot.subsystems.Lifter;
import org.usfirst.frc.team3019.robot.subsystems.MXPBreakout;
import org.usfirst.frc.team3019.robot.subsystems.PIDAngling;
import org.usfirst.frc.team3019.robot.subsystems.PIDDriving;
import org.usfirst.frc.team3019.robot.subsystems.Pneumatics;
import org.usfirst.frc.team3019.robot.utilities.AnglerState;
import org.usfirst.frc.team3019.robot.utilities.DriveState;
import org.usfirst.frc.team3019.robot.utilities.LauncherState;
import org.usfirst.frc.team3019.robot.utilities.ServoState;
import org.usfirst.frc.team3019.robot.utilities.SolenoidState;

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
	NetworkTable table;

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
	public static LauncherState launcherState = LauncherState.STILL;
	public static AnglerState anglerState = AnglerState.STILL;
	public static DriveState driveState = DriveState.STILL;
	public static SolenoidState solenoidState = SolenoidState.OFF;
	public static ServoState servoState = ServoState.RETRACTED;
	
	// autonomous command (not in use)
	Command autonomousCommand;

	SendableChooser chooser1;

	public Robot() {
		instantiateSubs();

	}

	public void robotInit() {

		// instantiate all necessary items
		instantiateDashButtons();
		instantiateNetworkTable();
		
		// create OI last so buttons can do commands
		oi = new OI();

	}

	private void instantiateNetworkTable() {

		// creating networktable for towertracking and putting testString
		table = NetworkTable.getTable("TowerTracker");
		table.putString("TEST", "isRunning");

	}

	private void instantiateDashButtons() {
		
		SmartDashboard.putData("PIDTurn", new PIDTurn());
		SmartDashboard.putData("PIDAngle", new PIDAngle());
		SmartDashboard.putData("ToggleSolenoid", new Solenoids());
		SmartDashboard.putData("ServoPunch", new FalconPunch());

	}

	private void instantiateSubs() {

		driveTrain = new DriveTrain();
		pneumatics = new Pneumatics();
		mxpBreakout = new MXPBreakout();
		launcher = new Launcher();
		lifter = new Lifter();
		PIDDriving = new PIDDriving(0.8, 0.0, 0.0, 0);
		PIDAngling = new PIDAngling(0.8, 0.0, 0.0, 0);

	}

	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	public void teleopPeriodic() {

		visionProcessing();
		dashUpdate();

		Scheduler.getInstance().run();
	}

	private void dashUpdate() {

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
