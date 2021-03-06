package org.usfirst.frc.team2345.robot.commands;

import org.usfirst.frc.team2345.robot.OI;
import org.usfirst.frc.team2345.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climber extends Command {
	Joystick stick = OI.stick;
	Joystick schtick = OI.schtick;
	VictorSP climber = RobotMap.climber;

    public Climber() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean climb = OI.stick.getRawButton(3);
    	boolean climbdown = OI.stick.getRawButton(2);
    	//Climber if loop that will change the speed to wheel over 3ish seconds ---tmd 2017
    	if (climb == true) {
    		int n = 0;
    		int speed = 0;
    		while (n < 10) { //for loop increments the speed of the wheel
    			speed = (int) (speed + 0.1);
    			climber.set(speed);
    			Timer.delay(0.2);
    			n++;
    		}
    		climber.set(1.0);
    	} else if (climbdown == true) {
    		climber.set(0.5);
    	} else {
    		climber.set(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
