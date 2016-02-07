package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.CompressorState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Compress extends Command {
	
	//constants needed
	String solenoidState = "off";
	String solenoidInstruction;
	Boolean isDone = false;

    public Compress() {
         requires(Robot.pneumatics);
    }

    public Compress(String string) {
		solenoidInstruction = string;
	}

	protected void initialize() {
    	
    	if(solenoidInstruction == "toggle"){
    		
    		if(solenoidState == "forward"){
    			
    			Robot.compressorState = CompressorState.REVERSE;
    			
    		}else if(solenoidState == "reverse"){
    			
    			Robot.compressorState = CompressorState.FORWARD;
    			
    		}
    	}else if(solenoidInstruction == "off"){
    		
    		Robot.compressorState = CompressorState.OFF;
    		
    	}
    	
    	switch (Robot.compressorState) {
			case FORWARD:
				
				// if told forward, solenoid forward
	    		Robot.pneumatics.soliForward();
	    		SmartDashboard.putString("PneumaticsStatus", "FWD");
	    		
				break;
			
			case REVERSE:
				
				// if told reverse, solenoid reverse
	    		Robot.pneumatics.soliReverse();
	    		SmartDashboard.putString("PneumaticsStatus", "REV");
	    		
				break;
				
			case OFF:
				
				// if told off, solenoid off    	
	    		Robot.pneumatics.soliOff();
	    		SmartDashboard.putString("PneumaticsStatus", "OFF");
	    		
				break;
				
		default:
			break;
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
