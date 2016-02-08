package org.usfirst.frc.team3019.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoAim extends CommandGroup {

	public AutoAim() {

		// turn to target
		addSequential(new PIDTurn());
		// angle shooter to target
		addSequential(new PIDAngle());
		// launch
		addSequential(new Launch(true));

	}
}
