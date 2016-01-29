package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Launch extends Command {

    public Launch() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//activate fly wheels
    	if(Robot.oi.xb1.get()){
    		Robot.launcher.launch(1);
    	}
    	else{
    		Robot.launcher.launch(0);
    	}
    	//activate angler
    	if(Robot.oi.xb2.get()){
    		Robot.launcher.angleLauncher(.7);
    	}
    	else if(Robot.oi.xb3.get()){
    		Robot.launcher.angleLauncher(-.7);
    	}
    	else{
    		Robot.launcher.angleLauncher(0);
    		//stop angling
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
