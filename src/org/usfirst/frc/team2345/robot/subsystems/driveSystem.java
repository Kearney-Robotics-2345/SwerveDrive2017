package org.usfirst.frc.team2345.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2345.robot.commands.swerveDrive;
/**
 *
 */
public class driveSystem extends Subsystem {
	public void initDefaultCommand(){
		
		
		setDefaultCommand(new swerveDrive());
	}
}