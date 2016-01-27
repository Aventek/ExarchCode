package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDDrive extends PIDSubsystem {

	NetworkTable table;
	public static double P;
    // Initialize your subsystem here
    public PIDDrive(double P, double I, double D) {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super(P,I,D);
    	
    	table = NetworkTable.getTable("TowerTracker");
    	setSetpoint(0);
    	setInputRange(-25, 25);
    	setAbsoluteTolerance(1);
//    	setPercentTolerance(5);
    	setOutputRange(-.7, .7);
//    	LiveWindow.addActuator("PIDDrive", "teststuff", getPIDController());
//    	LiveWindow.addSensor(subsystem, name, component);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return SmartDashboard.getNumber("azimuth",0);
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Robot.driveTrain.arcadeDrive(0, -output);
    }
}
