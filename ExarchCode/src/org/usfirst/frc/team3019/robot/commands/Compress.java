package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Compress extends Command {
	
	//constants needed
	String whatDo = "off";
	Boolean isDone = false;

    public Compress() {
         requires(Robot.pneumatics);
    }

    public Compress(String string) {
    	
    	//if recieveing a string for Compress state, set it to whatDo    
    	whatDo = string;
		
	}

    protected void initialize() {
    	
    	//based on what input for what to do, run solenoid and output to SmartDash    	
    	if(whatDo == "forward"){
    		
    		// if told forward, solenoid forward
    		Robot.pneumatics.soliForward();
    		SmartDashboard.putString("PneumaticsStatus", "FWD");
    	
    	}else if(whatDo == "reverse"){
    		
    		// if told reverse, solenoid reverse
    		Robot.pneumatics.soliReverse();
    		SmartDashboard.putString("PneumaticsStatus", "REV");
    	
    	}else if(whatDo == "off"){
    		
    		// if told off, solenoid off    	
    		Robot.pneumatics.soliOff();
    		SmartDashboard.putString("PneumaticsStatus", "OFF");
    	
    	}
    	//after telling solenoid what to do, exit command
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
