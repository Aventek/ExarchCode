package org.usfirst.frc.team3019.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootCommandGroup extends CommandGroup {

	public ShootCommandGroup() {
		// TODO Auto-generated constructor stub
		addSequential(new PIDAngle());
		addSequential(new Launch(true),3);
		addSequential(new Jerk());
		addSequential(new PIDAngle(0));
	}
}
