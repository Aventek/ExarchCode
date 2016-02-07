package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDAngle extends Command {

    public PIDAngle() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//retrieve distance from target
    	Robot.PIDAngle.distance = SmartDashboard.getNumber("distance",0);
    	
    	//calculation for 10 m/s shoot speed
    	Robot.PIDAngle.angle = 0.2075*(Math.pow(Robot.PIDAngle.distance, 2) - 0.1817*(Robot.PIDAngle.distance) + 44.395);
    	
    	Robot.PIDAngle.setSetpoint(Robot.PIDAngle.angle);
    	Robot.PIDAngle.enable();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	double error = Robot.PIDAngle.angle - Robot.launcher.anglePot.get();
        return Math.abs(error) < 0.3f;
    
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
