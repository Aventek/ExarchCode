package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.commands.Launch;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Launcher extends Subsystem {

	//instantiate all motor controllers and constants used
	Talon angler;
    Talon leftLaunch;
    Talon rightLaunch;
    Servo falconPunch;
    double servoState;

    public Launcher() {
    	
		//Servo to push ball into launcher
    	falconPunch = new Servo(RobotMap.launchServoPWM);
    	
    	//Motors used for launching mechanism
    	leftLaunch = new Talon(RobotMap.leftLaunchPWM);
    	rightLaunch = new Talon(RobotMap.rightLaunchPWM);
    	
    	//Motors used in angling launching mechanism
    	angler = new Talon(RobotMap.launchAnglerPWM);
    	
    	//invert motors for symmetry
    	rightLaunch.setInverted(true);
    	angler.setInverted(true);
    	
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new Launch());
    }
    
//DO: give the angling motor a speed to rotate(for angling mechanism)
    public void angleLauncher(double angle){

    	//will rotate to a given angle
    	angler.set(angle);
    
    }
    
//DO: set launch motors to spin and then set servo to firing position
    public void launch(double speed){

    	//setting launch motors
    	leftLaunch.set(speed);
    	rightLaunch.set(speed);
    
    }
   
//DO: uses servo to push ball into shooting mechanism
    public void falconPunch(){
    
    	//make servo toggleable
    	if(falconPunch.get()==1){
    		servoState = 0;
    	}else if(falconPunch.get()==0){
    		servoState = 1;
    	}
    	//set servo to toggled state
    	falconPunch.set(servoState);
    
    }

}