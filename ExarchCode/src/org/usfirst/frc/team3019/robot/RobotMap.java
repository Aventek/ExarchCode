package org.usfirst.frc.team3019.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
	 public static final boolean runArms = false;
	 public static float kP = 0.03f;
	 
	 
     public static final int leftMotorPWM = 0;
     public static int rightMotorPWM = 1;
     
     public static int liftMotorsPWM = 3;
     public static int armMotorsPWM = 4;
     
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}