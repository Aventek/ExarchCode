package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.commands.Launch;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Launcher extends Subsystem {

	// instantiate all motor controllers and constants used
	Talon angler;
	Talon leftLaunch;
	Talon rightLaunch;
	public Servo falconPunch;
	public AnalogPotentiometer anglePot;
	double servoState;
	public double potAngle;

	public Launcher() {

		// Servo to push ball into launcher
		falconPunch = new Servo(RobotMap.launchServoPWM);
		falconPunch.set(0.8);

		// Motors used for launching mechanism
		leftLaunch = new Talon(RobotMap.leftLaunchPWM);
		rightLaunch = new Talon(RobotMap.rightLaunchPWM);

		// Motors used in angling launching mechanism
		angler = new Talon(RobotMap.launchAnglerPWM);

		// Potentiometer used to measure angle of shooter
		anglePot = new AnalogPotentiometer(3, 1080, -120);

		// invert motors for symmetry
		angler.setInverted(true);

	}

	public void initDefaultCommand() {
		setDefaultCommand(new Launch());
	}

	// DO: give the angling motor a speed to rotate(for angling mechanism)
	public void angleLauncher(double speed) {

		// will rotate to a given angle
		angler.set(speed);

	}

	// DO: set launch motors to spin and then set servo to firing position
	public void launch(double speed) {

		// setting launch motors
		leftLaunch.set(speed);
		rightLaunch.set(speed);

	}

	// DO: uses servo to push ball into shooting mechanism
	public void falconPunch() {

		// make servo toggleable
		if (falconPunch.get() == 1) {
			servoState = 0.8;
		} else if (falconPunch.get() == 0.8) {
			servoState = 1;
		}
		// set servo to toggled state
		falconPunch.set(servoState);

	}

	public void selfLaunch() {

		launch(1);
		Timer.delay(2);
		falconPunch();
		Timer.delay(1);
		launch(0);

	}

}