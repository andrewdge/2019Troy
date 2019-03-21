package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Robot;

public class DefaultHatchCommand extends Command {

  private Robot robot;

  public DefaultHatchCommand(Robot robot) {
    this.robot = robot;
    requires(robot.hatchSubsystem);
  }

  
  protected void initialize() {
  }


  boolean candyCaneFlag = false;
  
  protected void execute() {
    
    if (Controls.hatchGrab()){
      robot.hatchSubsystem.armEnable();
    } else {
      robot.hatchSubsystem.armDisable();
    }

    // robot.hatchSubsystem.servoAset(Controls.servoAtest());
    // robot.hatchSubsystem.servoBset(Controls.servoBtest());
    
    if (Controls.hatchDeploy()){
      robot.hatchSubsystem.deployEnable();
    } else {
      robot.hatchSubsystem.deployDisable();
    }

    // if (Controls.hatchPulse()){
    //   robot.hatchSubsystem.pulseDeploy();
    // } else {
    //   robot.hatchSubsystem.deployDisable();
    // }

    if (Controls.hatchCandyCaneTrigger() && !candyCaneFlag){
      robot.hatchSubsystem.toggleCandyCane();
      candyCaneFlag = true;
    } else if (!Controls.hatchCandyCaneTrigger()){
      candyCaneFlag = false;
    }
    
    
    
  }

  
  protected boolean isFinished() {
    return false;
  }

  
  protected void end() {
  }

  
  protected void interrupted() {
  }
}
