package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Jerk extends Command{

	boolean isDone = false;
	public Jerk() {
		// TODO Auto-generated constructor stub
		requires(Robot.driveTrain);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		isDone = false;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.driveTrain.ArcadeDrive(1, 0);
		Timer.delay(.075);
		Robot.driveTrain.ArcadeDrive(-1, 0);
		Timer.delay(.1);
		isDone = true;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isDone;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.driveTrain.ArcadeDrive(0, 0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
