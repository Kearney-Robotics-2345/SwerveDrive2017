//FRC Team-2345
//Kearney Robotics -- Animal Control
//Kearney Robotics - Animal Control
//Steampunk 2017

package org.usfirst.frc.team2345.robot.commands;

import org.usfirst.frc.team2345.robot.OI;
//import org.usfirst.frc.team2345.robot.Robot;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;
import com.ctre.CANTalon;

import org.usfirst.frc.team2345.robot.RobotMap;
//Commented out code is left for posterities' sake
public class AbsoluteSwerve extends Command {
	double uRT;		//Upper Right Turn
    double uLT;		//Upper Left Turn
    double dRT;		//Down Right Turn
    double dLT;		//Down Left Turn
    //leftover code. Delete?
    //double uRTDir;	
    //double uLTDir;
    double dRTDir;
    double dLTDir;
    // multiplier ratio of encoder ticks to degrees
    double mult = 360;
    boolean commandStatus = false;

    //enabling all the encoders
    CANTalon frontLeftDrive = RobotMap.frontLeftDrive;
    CANTalon frontRightDrive = RobotMap.frontRightDrive;
    CANTalon backLeftDrive = RobotMap.backLeftDrive;
    CANTalon backRightDrive = RobotMap.backRightDrive;

    VictorSP frontLeftTurn = RobotMap.frontLeftTurn;
    VictorSP frontRightTurn = RobotMap.frontRightTurn;
    VictorSP backLeftTurn = RobotMap.backLeftTurn;
    VictorSP backRightTurn = RobotMap.backRightTurn;

    //all joystick code
    Joystick stick = OI.stick;		//Enables joystick 1
    Joystick schtick = OI.schtick;	//Enables joystick 2
    //calls encoders from Robot Map
    //Encoder frontRightEnc = RobotMap.frontRightEnc;
    //Encoder frontLeftEnc = RobotMap.frontLeftEnc;
    //Encoder backRightEnc = RobotMap.backRightEnc;
    //Encoder backLeftEnc = RobotMap.backLeftEnc;
    
    //Absolute encoders
    //AnalogInput absfrontRightEnc = RobotMap.absfrontRightEnc;
    //AnalogInput absfrontLeftEnc = RobotMap.absfrontLeftEnc;
    AnalogInput absbackRightEnc = RobotMap.absbackRightEnc;
    AnalogInput absbackLeftEnc = RobotMap.absbackLeftEnc;
 
    //Math to determine hypotenuse of frame
    static double l = 14.375;//19; //(wheelbase, inches)
    
    static double w = 25.75;//32; //(trackwidth, inches)
    double r = Math.sqrt(l * l + w * w);

    public AbsoluteSwerve() {
    	//requires(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	/*
    	 * double absfrontRightEncoder = (absfrontRightEnc.get());
    	 * double absfrontLeftEncoder = (absfrontLeftEnc.get());
    	 * double absbackRightEnc = (absbackRightEnc.get());
    	 * double absbackLeftEnc = (absbackLeftEnc.get());
    	 * 
    	 * 
    	 */
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if (auto = true) {
        
    	// used to change encoder ticks to degrees
    	//double frontRightEncoder = (frontRightEnc.get() / mult);
    	//double frontLeftEncoder = (frontLeftEnc.get() / mult);
    	//double backRightEncoder = (backRightEnc.get() / mult);
    	//double backLeftEncoder = (backLeftEnc.get() / mult);
    	
    	//Absolute Encoder code (Actually Analog)
    	
    	//double absfrontRightEncoder = ((absfrontRightEnc.getVoltage() / 5.0) * mult);
    	//double absfrontLeftEncoder = ((absfrontLeftEnc.getVoltage() / 5.0) * mult);
    	double absbackRightEncoder = ((absbackRightEnc.getVoltage() / 5.0) * mult);
    	double absbackLeftEncoder = ((absbackRightEnc.getVoltage() / 5.0) * mult);
 
    	//fixes the problem of negative ticks on the encoder converting to inverted degrees(-1* translates to 359* opposed to -1* before)
    	//double uRE = (absfrontRightEncoder < 0) ? 360 - Math.abs(absfrontRightEncoder % 360) : absfrontRightEncoder % 360;
    	//double uLE = (absfrontLeftEncoder < 0) ? 360 - Math.abs(absfrontLeftEncoder % 360) : absfrontLeftEncoder % 360; //frontLeftEncoder%= 360;
    	double dLE = (absbackLeftEncoder < 0) ? 360 - Math.abs(absbackLeftEncoder % 360) : absbackLeftEncoder % 360; //backLeftEncoder %= 360;
    	double dRE = (absbackRightEncoder < 0) ? 360 - Math.abs(absbackRightEncoder % 360) : absbackRightEncoder % 360; //backRightEncoder %= 360;
    	
    	
    	
    	double str = stick.getX() * Math.abs(stick.getX()) * -1; // stick.getThrottle();(forward/reverse command, -1 to +1)
        double fwd = stick.getY() * Math.abs(stick.getY()); // stick.getThrottle();(strafe right command, -1 to +1)
        double rcw = schtick.getX() * Math.abs(schtick.getX()); // schtick.getThrottle();(rotate clockwise command, -1 to +1)


        //for any questions on code in lines 106-126, refer to Ether on cheif delphi, because thats where we got this trig sorcery
        //math for finding the degrees of all the wheels and their vectors
        double A = str-rcw*(l/r);
        double B = str+rcw*(l/r);
        double C = fwd-rcw*(w/r);
        double D = fwd+rcw*(w/r);

        double ws1 = Math.sqrt(B*B + C*C);
        double ws2 = Math.sqrt(B*B + D*D);
        double ws3 = Math.sqrt(A*A + D*D);
        double ws4 = Math.sqrt(A*A + C*C);
        double max = Math.max(ws1, Math.max(ws2, Math.max(ws3, ws4)));

        //sets all the angles and powers from the above to the wheels
        // for the time being, this is fine, but in the future, I would suggest putting the relative positions of the wheels here instead of putting in generic numbers, because it is slightly unclear.
        double wheelSpeedOne = (max>1) ? ws1/max : ws1;
        double wheelSpeedTwo = (max>1) ? ws2/max : ws2;
        double wheelSpeedThree = (max>1) ? ws3/max : ws3;
        double wheelSpeedFour = (max>1) ? ws4/max : ws4;
        double wheelAngleOne = ( C == 0 && B == 0) ? 0 : Math.toDegrees(Math.atan2(B,C));
        double wheelAngleTwo = ( D == 0 && B == 0) ? 0 : Math.toDegrees(Math.atan2(B,D));
        double wheelAngleThree = ( D == 0 && A == 0) ? 0 : Math.toDegrees(Math.atan2(A,D));
        double wheelAngleFour = ( C == 0 && A == 0) ? 0 : Math.toDegrees(Math.atan2(A,C));
        
        
        //this takes the difference of the wanted angle and current angle to see the shorter
        //distance, then it modifies motor value based on angle
        double wA1 = (wheelAngleOne < 0) ? 360 - Math.abs(wheelAngleOne) : wheelAngleOne;
        double uRTdr;
        
        /*if(Math.abs(uRE-wA1) < 1) {
        	uRTdr = 0;
        }else if (Math.abs(uRE - wA1) <= 180){			// if absolute of (current angle - wanted angle) is less than or equal to 180
        	uRTdr = ((uRE -wA1) < 0) ? -1 : 1;		//Direction = (if (current angle - wanted angle) is less than 0) true: - False: +
        }else if(Math.abs(uRE - wA1) > 180){		
        	uRTdr = ((uRE -wA1) < 0) ? 1 : -1;
        //
        }else if(Math.abs(uRE - wA1) == 180) {
        	uRTdr = 1;
        //
        }else{
        	uRTdr = 0;
        }*/
        //these section are just repeats of the above section for the other wheels
        double wA2 = (wheelAngleTwo < 0) ? 360 - Math.abs(wheelAngleTwo) : wheelAngleTwo;
        double uLTdr;
        
        /*if (Math.abs(uLE - wA2) <= 180){			
        	uLTdr = ((uLE -wA2) < 0) ? -1 : 1;
        }else if(Math.abs(uLE - wA2) > 180){
        	uLTdr = ((uLE -wA2) < 0) ? 1 : -1;
        //
        }else if(Math.abs(uLE - wA2) == 180) {
        	uLTdr = 1;
        //  
        }else{
        	uLTdr = 0;
        }*/
        
        double wA3 = (wheelAngleThree < 0) ? 360 - Math.abs(wheelAngleThree) : wheelAngleThree;
        double dLTdr;
        
        if (Math.abs(dLE - wA3) <= 180){
        	dLTdr = ((dLE -wA3) < 0) ? -1 : 1;
        }else if(Math.abs(dLE - wA3) > 180){
        	dLTdr = ((dLE - wA3) < 0) ? 1 : -1;
        //
        }else if(Math.abs(dLE - wA3) == 180) {
        	dLTdr = 1;
        //
        }else{
        	dLTdr = 0;
        }
        
        double wA4 = (wheelAngleFour < 0) ? 360 - Math.abs(wheelAngleFour) : wheelAngleFour;
        double dRTdr;
        
        if (Math.abs(dRE - wA4) <= 180){
        	dRTdr = ((dRE -wA4) < 0) ? -1 : 1;
        }else if(Math.abs(dRE - wA4) > 180){
        	dRTdr = ((dRE -wA4) < 0) ? 1 : -1;
        //
        }else if(Math.abs(dRE - wA4) == 180) {
        	dRTdr = 1;
        //
        }else{
        	dRTdr = 0;
        }

        double throttle = (OI.stick.getZ() * -0.5) + 0.9;
        
        // I added a 0.7 to slow down the robot, as it was discovered that at full power, the robot would tip over.
        frontLeftDrive.set(wheelSpeedTwo * 0.7 * throttle); 
        frontRightDrive.set(wheelSpeedOne * 0.7 * throttle);
        backLeftDrive.set(wheelSpeedThree * 0.7 * throttle);
        backRightDrive.set(wheelSpeedFour * 0.7 * throttle);
        
        // This displays inputs and outputs on the Dashboard for troubleshooting purposes
        //SmartDashboard.putNumber("uL", (double) absfrontLeftEnc.getVoltage());
        //SmartDashboard.putNumber("uR", (double) absfrontRightEnc.getVoltage());
        SmartDashboard.putNumber("dL", (double) absbackLeftEnc.getVoltage());
        SmartDashboard.putNumber("dR", (double) absbackRightEnc.getVoltage());
        SmartDashboard.putNumber("wS1", (double) wheelSpeedOne);
        SmartDashboard.putNumber("wS2", (double) wheelSpeedTwo);
        SmartDashboard.putNumber("wS3", (double) wheelSpeedThree);
        SmartDashboard.putNumber("wS4", (double) wheelSpeedFour);
        SmartDashboard.putNumber("wA1", (double) wheelAngleOne);
        SmartDashboard.putNumber("wA2", (double) wheelAngleTwo);
        SmartDashboard.putNumber("wA3", (double) wheelAngleThree);
        SmartDashboard.putNumber("wA4", (double) wheelAngleFour);
        
        //Test that Mod is working
        //SmartDashboard.putNumber("uLe", (double) absfrontLeftEncoder);
        //SmartDashboard.putNumber("uRe", (double) absfrontRightEncoder);
        SmartDashboard.putNumber("dLe", (double) absbackLeftEncoder);
        SmartDashboard.putNumber("dRe", (double) absbackRightEncoder);
        
        //exponential decrease of motor input based on distance from wanted degree
        //double uRT = (Math.abs(uRE - wA1) > 80) ? 0.7 : (Math.abs(uRE - wA1)) / 70;
        //double uLT = (Math.abs(uLE - wA2) > 80) ? 0.7 : (Math.abs(uLE - wA2)) / 70;
        double dLT = (Math.abs(dLE - wA3) > 80) ? 0.7 : (Math.abs(dLE - wA3)) / 70;
        double dRT = (Math.abs(dRE - wA4) > 80) ? 0.7 : (Math.abs(dRE - wA4)) / 70;

        //takes motor power and mods from lesser angle code
        //frontRightTurn.set(uRT * uRTdr);
        //frontLeftTurn.set(uLT * uLTdr);
        backLeftTurn.set(dLT * dLTdr);
        backRightTurn.set(dRT * dRTdr);
        
        
        //DEBUGGING (yay)
        //double uRTC = uRT * uRTdr;
        //double uLTC = uLT * uLTdr;
        double dLTC = dLT * dLTdr;
        double dRTC = dRT * dRTdr;
        
        //SmartDashboard.putNumber("uRTC", (double) uRTC);
        //SmartDashboard.putNumber("uLTC", (double) uLTC);
        SmartDashboard.putNumber("dLTC", (double) dLTC);
        SmartDashboard.putNumber("dRTC", (double) dRTC);

        //This line of code directly below caused the code to take values from being enabled and hold them
        //I commented it out so that it would not trigger protected void end ----tmd 2017
        //commandStatus = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return commandStatus;
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
