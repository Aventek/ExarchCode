package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Compress extends Command {
	
	String whatDo = "off";
	Boolean isDone = false;

    public Compress() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.pneumatics);
    }

    public Compress(String string) {
    	whatDo = string;
		// TODO Auto-generated constructor stub
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    	if(whatDo == "forward"){
    		Robot.pneumatics.soliForward();
    		SmartDashboard.putString("PneumaticsStatus", "FWD");
    	}else if(whatDo == "reverse"){
    		Robot.pneumatics.soliReverse();
    		SmartDashboard.putString("PneumaticsStatus", "REV");
    	}else if(whatDo == "off"){
    		Robot.pneumatics.soliOff();
    		SmartDashboard.putString("PneumaticsStatus", "OFF");
    	}
    	isDone = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
