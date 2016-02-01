package org.usfirst.frc.team3019.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
//constants used in robot
	 public static final boolean runArms = false;
	 public static float kP = 0.03f;
	 
	 //pwm ports
     public static final int leftDrivePWM = 0;
     public static final int rightDrivePWM = 1;
     
     public static final int leftLaunchPWM = 2;
     public static final int rightLaunchPWM = 3;
     
     public static final int launchAnglerPWM = 4;
     
     public static final int launchServoPWM = 5;
    
}
