package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Robot;

public class DefaultDriveCommand extends Command{
  //reference to main robot object(robot.java)
  private Robot robot;

  //constructor
  public DefaultDriveCommand(Robot robot){
    this.robot = robot;
    requires(robot.driveSubsystem);
  }

  protected void execute(){
    
    //we only do tankdrive, left and right joystick axis values are passed in as arguments to set the speed
    robot.driveSubsystem.tankDrive(Controls.driveLeftThrottle(), Controls.driveRightThrottle());
    
  }

  protected boolean isFinished(){
    return false;
  }
}