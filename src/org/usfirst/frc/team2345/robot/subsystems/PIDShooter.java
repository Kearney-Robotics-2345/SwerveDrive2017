package org.usfirst.frc.team2345.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2345.robot.RobotMap;
import edu.wpi.first.wpilibj.livewindow.*;

/**
 *
 */
public class PIDShooter extends PIDSubsystem {
	//VictorSP flywheel = RobotMap.flywheel;
	/*AnalogInput shooterPID = RobotMap.shooterPID;*/
	
    // Initialize your subsystem here
    public PIDShooter() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("PIDShooter", 1.0, 0.0, 0.0);
    	setAbsoluteTolerance(0.2);
    	getPIDController().setContinuous(true);
    	LiveWindow.addActuator("PIDShooter", "PIDSubsystem Controller", getPIDController());
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0.001;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	//flywheel.pidWrite(output);
    }
}
