package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Launch extends Command {

	Boolean autoshoot;

	public Launch() {
		requires(Robot.launcher);
	}

	public Launch(Boolean autoShoot) {
		this.autoshoot = autoShoot;
	}

	protected void initialize() {
	}

	protected void execute() {

		// spinning launcher motors
		if (Robot.oi.xb5.get()) {

			// when button A is held down run motors for launching
			Robot.launcher.launch(1);

		} else if (Robot.oi.xb6.get()) {

			// when right trigger is held down run motors for intake
			Robot.launcher.launch(0.4);

		} else if (autoshoot) {

			Robot.launcher.selfLaunch();

		} else {

			// when neither are held down dont run motors
			Robot.launcher.launch(0);

		}

		// activate angler
		if (Robot.oi.xb2.get()) {

			// when B is held down angle launcher down
			Robot.launcher.angleLauncher(-.3);

		} else if (Robot.oi.xb3.get()) {

			// when X is held down, angle launcher up
			Robot.launcher.angleLauncher(.6);

		} else {
			// stop angling
			Robot.launcher.angleLauncher(0);

		}

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
