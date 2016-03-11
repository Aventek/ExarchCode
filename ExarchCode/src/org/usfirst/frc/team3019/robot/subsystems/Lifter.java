package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.commands.Lift;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lifter extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	VictorSP launchMotor1;
	VictorSP launchMotor2;
	public DigitalInput armLimitSwitch;

	public boolean canGoUp = true;
	public boolean canGoDown = true;
	

	public Lifter() {

		// create instances of motors -note: there is no distinction between the
		// motors
		launchMotor1 = new VictorSP(RobotMap.liftAnglerPWM);
		launchMotor2 = new VictorSP(RobotMap.liftAngler2PWM);
		
		//lift motor limit switch
		armLimitSwitch = new DigitalInput(1);

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
