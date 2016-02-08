package org.usfirst.frc.team3019.robot;

import org.usfirst.frc.team3019.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	/*xbox controller*/	
	public Joystick xbox = new Joystick(0);
	
	/*A - used for AutoAim*/	
	public Button xb1 = new JoystickButton(xbox, 1);
	
	/*B - used to toggle solenoid*/	
	public Button xb2 = new JoystickButton(xbox, 2);
	
	/*X - used to turn off solenoid*/	
	public Button xb3 = new JoystickButton(xbox, 3);
	
	/*Y - used to control servo*/	
	public Button xb4 = new JoystickButton(xbox, 4);

	/*lBump - used to manual run launch motors fwd*/	
	public Button xb5 = new JoystickButton(xbox, 5);
	
	/*rBump - used to manual run launch motors intake*/	
	public Button xb6 = new JoystickButton(xbox, 6);
	
	/*back - used to manual do PIDTurn*/	
	public Button xb7 = new JoystickButton(xbox, 7);
	
	/*start - used to manual do PIDAngle*/	
	public Button xb8 = new JoystickButton(xbox, 8);
	
	/*lStickClick - NOT USED*/
	public Button xb9 = new JoystickButton(xbox, 9);
	
	/*rStickClick - NOT USED*/
	public Button xb10 = new JoystickButton(xbox, 10);
	
	public OI(){
		
		if(RobotMap.useAutoAlign){
		
			//Pressing A does the autoAiming sequence
			xb1.whenPressed(new AutoAim());
		
		}
		
		if(RobotMap.usePID){
		
			//PID commands
			xb7.whenPressed(new PIDTurn());
			xb8.whenPressed(new PIDAngle());
		
		}
		
		if(RobotMap.useCompressor){
		
			//Compressor commands
			xb2.whenPressed(new Compress());
		
		}
		
		if(RobotMap.usePuncher){
		
			//launch servo command
			xb4.whenPressed(new FalconPunch());
		
		}
		
	}
}

