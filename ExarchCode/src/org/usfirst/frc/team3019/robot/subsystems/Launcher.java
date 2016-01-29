package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.commands.Launch;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Launcher extends Subsystem {
    Talon angler;
    Talon leftLaunch;
    Talon rightLaunch;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	

    public Launcher() {
		// TODO Auto-generated constructor stub
    	leftLaunch = new Talon(2);
    	leftLaunch.setInverted(true);
    	rightLaunch = new Talon(3);
    	angler = new Talon(4);
    	
    }
    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        setDefaultCommand(new Launch());
    }
    public void angleLauncher(double angle){
    	//will rotate to a given angle
    	angler.set(angle);
    }
    public void launch(double speed){
    	leftLaunch.set(speed);
    	rightLaunch.set(speed);
    }
}