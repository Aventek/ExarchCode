package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.commands.Launch;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Launcher extends Subsystem {

	// instantiate all motor controllers and constants used
	VictorSP angler;
	VictorSP leftLaunch;
	VictorSP rightLaunch;
	Talon intakeBar;
//	public Servo pusher;
	public AnalogPotentiometer anglePot;
	public double potAngle;
	public double targetAngle;

	public Launcher() {

		// Servo to push ball into launcher
//		pusher = new Servo(RobotMap.launchServoPWM);
//		pusher.set(Launch.retractedPosition);

		// Motors used for launching mechanism
		leftLaunch = new VictorSP(RobotMap.leftLaunchPWM);
		rightLaunch = new VictorSP(RobotMap.rightLaunchPWM);

		// Motors used on the bar for intake of the ball
		intakeBar = new Talon(RobotMap.intakeArmsPMW);
		
		// Motors used in angling launching mechanism
		angler = new VictorSP(RobotMap.launchAnglerPWM);

		// Potentiometer used to measure angle of shooter
		anglePot = new AnalogPotentiometer(3, 1080, 0);

		// invert motors for symmetry
		angler.setInverted(true);
		leftLaunch.setInverted(true);

	}

	public void initDefaultCommand() {
		setDefaultCommand(new Launch());
	}

	// DO: give the angling motor a speed to rotate(for angling mechanism)
	public void angleLauncher(double speed) {

		// will rotate to a given angle
		angler.set(speed);

	}
	
	public void intakeControl(double speed){
		
		leftLaunch.set(-.4);
		rightLaunch.set(-.4);
		intakeBar.set(speed);
	
	}

	// sets launch motors to spin and then set servo to firing position
	public void launch(double speed) {

		// setting launch motors
		leftLaunch.set(speed);
		rightLaunch.set(speed);

	}

	// used to set position of servo
//	public void servoControl(double i) {
//
//		pusher.set(i);
//
//	}
	public double getPot(){
		return anglePot.get();
	}

	public void resetPotentiometer() {
		// TODO Auto-generated method stub
		RobotMap.ShooterAngleOfset = Math.abs(getPot());
	}

}