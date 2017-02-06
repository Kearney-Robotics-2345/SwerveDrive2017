package org.usfirst.frc.team2345.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2345.robot.*;
//import edu.wpi.first.wpilibj.command.PIDSubsystem;
/**
 *
 */
public class Shooter extends Command {
	Joystick stick = OI.stick;
	Joystick schtick = OI.schtick;
	//Servo twister = new Servo(1);
	VictorSP flywheel = RobotMap.flywheel;
    public Shooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	boolean settwister = OI.stick.getRawButton(1);
    	
    	if (settwister == true) {
    		//This will be used to make the servo spin ---tmd 2017
    		//We need to set the angle correctly so it stops the balls.
    		twister.setAngle(70);
    	} else {
    		//Sets servo to 0 if button is not pressed ---tmd 2017
    		twister.setAngle(0);
    	}*/
    	double throttleshooter = (OI.schtick.getZ() * - 0.5);
    	boolean shoot = OI.stick.getRawButton(1);
    	double flywheelspeed = 1.0;
    	//if loop for the shooting part of the shooter! ---tmd 2017
    	if (shoot == true) {
    		flywheel.set(flywheelspeed * throttleshooter);
    	} else {
    		flywheel.set(0);
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
