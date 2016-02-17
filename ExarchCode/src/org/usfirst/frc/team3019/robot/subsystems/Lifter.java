package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.commands.Lift;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lifter extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Talon launchMotor1;
	Talon launchMotor2;

	public Lifter() {

		// create instances of motors -note: there is no distinction between the
		// motors
		launchMotor1 = new Talon(RobotMap.liftAnglerPWM);
		launchMotor2 = new Talon(RobotMap.liftAngler2PWM);

		// invert one motor so they both spin the same way
		launchMotor2.setInverted(true);
	}

	public void initDefaultCommand() {

		setDefaultCommand(new Lift());

	}

	// Set both motors to the same speed
	public void LiftControl(double liftValue) {

		launchMotor1.set(liftValue);
		launchMotor2.set(liftValue);

	}
}
