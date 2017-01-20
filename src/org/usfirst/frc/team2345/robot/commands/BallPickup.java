package org.usfirst.frc.team2345.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team2345.robot.*;

/**
 *
 */
public class BallPickup extends Command {
	Joystick schtick = OI.schtick;
	VictorSP pickup = RobotMap.pickup;
    public BallPickup() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean ballPickUp = OI.schtick.getRawButton(3);
    	double pickupspeed = (0.5);
    	if (ballPickUp == true) {
    		pickup.set(pickupspeed);
    	} else {
    		pickup.set(0);
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
