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
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
//    public Joystick stick = new Joystick(0);
	public Joystick stick = new Joystick(0);
	public Joystick xbox = new Joystick(1);
	public Button button1 = new JoystickButton(stick, 1);
	public Button button2 = new JoystickButton(stick, 2);
	public Button button3 = new JoystickButton(stick, 3);
	public Button button4 = new JoystickButton(stick, 4);
	public Button button5 = new JoystickButton(stick, 5);
	
	
	
	public OI(){
		button1.whenPressed(new PIDTurn());
		button3.whenPressed(new Compress("forward"));
		button4.whenPressed(new Compress("reverse"));
		button5.whenPressed(new Compress("off"));
	}
////    
//    public Button button = new JoystickButton(xbox, 1);
    
//    public OI(){
//    	button1.whileHeld(new Drive());
//    }
    
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
//     button.whenPressed(new RunMotors());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
     
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

