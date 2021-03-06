package org.usfirst.frc.team2345.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team2345.robot.RobotMap;
/**
 *
 */
public class Autonomous extends Command {

    public Autonomous() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    ////TEST CODE
    	RobotMap.frontLeftTurn.set(0);
    	RobotMap.frontRightTurn.set(0);
    	RobotMap.backLeftTurn.set(0);
    	RobotMap.backRightTurn.set(0);
    	
    	//Spin motors
    	RobotMap.frontLeftTurn.set(85);
    	RobotMap.frontRightTurn.set(85);
    	RobotMap.backLeftTurn.set(85);
    	RobotMap.backRightTurn.set(85);
    	Timer.delay(2.75);
    	
    	//Stop Spin
    	RobotMap.frontLeftTurn.set(0);
    	RobotMap.frontRightTurn.set(0);
    	RobotMap.backLeftTurn.set(0);
    	RobotMap.backRightTurn.set(0);
    	Timer.delay(2.75);
    	
    	//Move Back
    	RobotMap.frontLeftDrive.set(0.2);
    	RobotMap.frontRightDrive.set(0.2);
    	RobotMap.backLeftDrive.set(0.2);
    	RobotMap.backRightDrive.set(0.2);    	
    	Timer.delay(2.75); 
    	
    	//Stop
    	
    	RobotMap.frontLeftDrive.set(0);
    	RobotMap.frontRightDrive.set(0);
    	RobotMap.backLeftDrive.set(0);
    	RobotMap.backRightDrive.set(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
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
