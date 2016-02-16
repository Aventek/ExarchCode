package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.SolenoidState;

import edu.wpi.first.wpilibj.command.Command;

public class Solenoids extends Command {

	// constants needed
	Boolean solenoidState = true;
	Boolean isDone = false;

	public Solenoids() {
		requires(Robot.pneumatics);
	}

	protected void initialize() {

		// if depending on solenoid State, change solenoid input
		if (solenoidState) {

			Robot.pneumatics.soliForward();
			Robot.solenoidState = SolenoidState.FORWARD;

		} else if (!solenoidState) {

			Robot.pneumatics.soliReverse();
			Robot.solenoidState = SolenoidState.REVERSE;

		} else {

			Robot.pneumatics.soliOff();
			Robot.solenoidState = SolenoidState.OFF;

		}

		// toggle solenoid state
		solenoidState = !solenoidState;

		// after telling solenoid what to do, exit command
		isDone = true;

	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return isDone;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
