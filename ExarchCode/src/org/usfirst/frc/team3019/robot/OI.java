package org.usfirst.frc.team3019.robot;

import org.usfirst.frc.team3019.robot.commands.Compress;
import org.usfirst.frc.team3019.robot.commands.PIDTurn;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	/*xbox*/	public Joystick xbox = new Joystick(0);
	
	/*A*/	public Button xb1 = new JoystickButton(xbox, 1);
	
	/*B*/	public Button xb2 = new JoystickButton(xbox, 2);
	
	/*X*/	public Button xb3 = new JoystickButton(xbox, 3);
	
	/*Y*/	public Button xb4 = new JoystickButton(xbox, 4);

	/*lBump*/	public Button xb5 = new JoystickButton(xbox, 5);
	
	/*rBump*/	public Button xb6 = new JoystickButton(xbox, 6);
	
	
	
	public OI(){
		
		xb1.whenPressed(new PIDTurn());
		xb3.whenPressed(new Compress("forward"));
		xb4.whenPressed(new Compress("reverse"));
		xb5.whenPressed(new Compress("off"));
		
	}
}

