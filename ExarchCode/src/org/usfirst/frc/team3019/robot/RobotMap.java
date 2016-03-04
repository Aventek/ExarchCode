package org.usfirst.frc.team3019.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// constants used in robot
	public static final boolean runArms = false;
	public static float kP = 0.03f;
	public static double driveNerf = 1;
	public static double angleOff;

	// systems in use, change to pos or neg here
	public static final boolean usePneumatics = true;
	public static final boolean usePID = true;
	public static final boolean usePuncher = true;
	public static final boolean useAutoAlign = false;

// pwm ports
	//DRIVE MOTORS
	public static final int leftRearDrivePWM = 0;
	public static final int leftFrontDrivePWM = 1;
	public static final int rightRearDrivePWM = 2; 
	public static final int rightFrontDrivePWM = 3; 
	//LAUNCHER MOTORS
	public static final int leftLaunchPWM = 4; 
	public static final int rightLaunchPWM = 5;
	//ARM LIFT MOTORS
	public static final int liftAnglerPWM = 6;  
	public static final int liftAngler2PWM =7;
	//SHOOTER ANGLING AND SERVO
	public static final int launchAnglerPWM = 8;
	public static final int launchServoPWM = 9;

}
