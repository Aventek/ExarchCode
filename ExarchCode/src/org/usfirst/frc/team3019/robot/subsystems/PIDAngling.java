package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PIDAngling extends PIDSubsystem {

	public static double P;
	public double angle;
	public double distance;

	public PIDAngling(double P, double I, double D, double setPoint) {

		super(P, I, D);

		// setInputRange(-25, 25);
		setAbsoluteTolerance(1);
		setOutputRange(-1, 1);

	}

	public PIDAngling(double P, double I, double D) {
		// TODO Auto-generated constructor stub
		this(P, I, D, 0);
	}

	public void initDefaultCommand() {
	}

	// DO: sets the correct input to be used for PID Angling
	protected double returnPIDInput() {

		// use the potentiometer's read angle to match the
		return Robot.launcher.anglePot.get();

	}

	// DO: sets correct output to be generated by PID
	protected void usePIDOutput(double output) {

		// Output of pid to rotate bot towards center/forward
		Robot.launcher.angleLauncher(output);

	}
}
