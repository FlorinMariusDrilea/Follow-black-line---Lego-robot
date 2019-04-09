import sheffield.*;
import ShefRobot.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Assignment3 {
	enum Command {STOP, LEFT, RIGHT, FORWARD, REVERSE, KICK };
	public void keyPressed(KeyEvent e) {
		switch ( e.getKeyCode()) {
			case java.awt.event.KeyEvent.VK_UP:
				command = Command.FORWARD;
				break;
			case java.awt.event.KeyEvent.VK_DOWN:
				command = Command.REVERSE;
				break;
			case java.awt.event.KeyEvent.VK_LEFT:
				command = Command.LEFT;
				break;
			case java.awt.event.KeyEvent.VK_RIGHT:
				command = Command.RIGHT;
				break;
			case java.awt.event.KeyEvent.VK_SPACE:
				command = Command.KICK;
				break;
			default:
				command = Command.STOP;
				break;
		}
	}	

	static Command command = Command.STOP;
	
	public static void main(String[] args) {
        //Create a robot object to use and connect to it
		Robot myRobot = new Robot();

        //The robot is made of components which are themselves objects.
        //Create references to them as useful shortcuts
        Motor leftMotor = myRobot.getLargeMotor(Motor.Port.B);
        Motor rightMotor = myRobot.getLargeMotor(Motor.Port.C);
        Speaker speaker = myRobot.getSpeaker();
        ColorSensor light = myRobot.getColorSensor(Sensor.Port.S2);  
        Motor foot = myRobot.getMediumMotor(Motor.Port.A);
		
		//variables 
		boolean checkedLeft = false;
		Enum x = light.getColor();
		
        System.out.println(x);
        while(x!=ColorSensor.Color.BLUE)
		{
			if(x==ColorSensor.Color.BLACK)
			{
				leftMotor.setSpeed(150);
				rightMotor.setSpeed(150);
				leftMotor.forward();
				rightMotor.forward();
				System.out.print(x);
			}
		  
			else if(checkedLeft){
				rightMotor.setSpeed(150);
				rightMotor.forward();
				leftMotor.stop();
				System.out.print(x);
				checkedLeft = true;
			}
			else {
				leftMotor.setSpeed(150);
				leftMotor.forward();
				leftMotor.forward();
				rightMotor.stop();
				System.out.print(x);
				checkedLeft = false;
			}
			x=light.getColor();
		}
        leftMotor.stop();
        rightMotor.stop();
		
		//control the robot with the keyboard
		while (true) {
			switch (command) {
				case STOP:
					System.out.println("Stop");
					leftMotor.stop();
                    rightMotor.stop();
					
					break;					
				case FORWARD:
					System.out.println("Forward");
					leftMotor.setSpeed(150);
					rightMotor.setSpeed(150);
					leftMotor.forward();
					rightMotor.forward();
					
					break;					
				case REVERSE:
					System.out.println("Reverse");
					leftMotor.setSpeed(150);
					rightMotor.setSpeed(150);
					leftMotor.backward();
					rightMotor.backward();
					
					break;					
				case LEFT:
					System.out.println("Left");
					leftMotor.setSpeed(150);
					leftMotor.forward();

					break;
				case RIGHT:
					System.out.println("Right");
					rightMotor.setSpeed(150);
					rightMotor.forward();

					break;
				case KICK:
					System.out.println("Kick");
					foot.setSpeed(100);
					foot.rotate(20);
 					break;
			}
		}
      
    }
}
