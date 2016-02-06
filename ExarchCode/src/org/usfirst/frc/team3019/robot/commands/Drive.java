package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.DriveState;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command{
	
	public double driveNerf = 1;
	
	//state of drivetrain so we can alternate PID and joyDrive
	
	public Drive() {
		requires(Robot.driveTrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		
		//Simple arcade drive from lStick axes 
		if(Robot.state == DriveState.JOYSTICK){
			Robot.driveTrain.arcadeDrive(-Robot.oi.xbox.getY() * driveNerf , Robot.oi.xbox.getX() * driveNerf);
		}
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
