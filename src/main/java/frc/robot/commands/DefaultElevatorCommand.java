package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Robot;
import frc.robot.subsystems.ElevatorSubsystem;

public class DefaultElevatorCommand extends Command {

  private Robot robot;
  

  

  public DefaultElevatorCommand(Robot robot) {
    this.robot = robot;
    requires(robot.elevatorSubsystem);
  }

  
  protected void initialize() {
    //robot.elevatorSubsystem.setMode(ElevatorSubsystem.shipMode);
    //robot.elevatorSubsystem.setLevel(1);
  }

  /*flags to stop polling for dpad hits and making modes/levels scroll really fast
  order is top, left, right, down on dpad. 
  */
  // boolean vatorUpButtonFlag = false;
  // boolean vatorModeSwitchPrevButtonFlag = false;
  // boolean vatorModeSwitchNextButtonFlag = false;
  // boolean vatorHomeButtonFlag = false;

  protected void execute() {

    // //zero encoder but this is messed up
    // robot.elevatorSubsystem.checkBotLimitResetEncoder();

    
    // //begin up
    // if (Controls.vatorUpLevel() && !vatorUpButtonFlag){

    //   vatorUpButtonFlag = true;

    //   //store current level
    //   //tempCurrentLevel = robot.elevatorSubsystem.currentLevel;

    //   //for not ship (ball, hatch)
    //   if (robot.elevatorSubsystem.currentMode == ElevatorSubsystem.ballMode){
        
    //     //3 levels, 3 is max level, protect against overflow
    //     if (robot.elevatorSubsystem.currentLevel < 3){
    //       robot.elevatorSubsystem.setLevel(robot.elevatorSubsystem.currentLevel + 1);
          
    //       //set upper limits
    //       if (robot.elevatorSubsystem.currentLevel == 1){
    //         ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.r_ballLevels[1];
    //       } else if (robot.elevatorSubsystem.currentLevel == 2){
    //         ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.r_ballLevels[2];
    //       } else {
    //         ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.r_ballLevels[3];
    //       }

    //     } else { //if overflow, set to 1
    //       robot.elevatorSubsystem.setLevel(1);

    //       if (robot.elevatorSubsystem.currentLevel == 1){
    //         ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.r_ballLevels[1];
    //       }

    //     }
    //     //end not ship
    //   }/* for ship */ else if (robot.elevatorSubsystem.currentMode == ElevatorSubsystem.shipMode){
        
    //     //2 is max level now
    //     if (robot.elevatorSubsystem.currentLevel < 2){
    //       robot.elevatorSubsystem.setLevel(robot.elevatorSubsystem.currentLevel + 1);

    //       if (robot.elevatorSubsystem.currentLevel == 1){
    //         ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.c_shipLevels[1];
    //       } else if (robot.elevatorSubsystem.currentLevel == 2){
    //         ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.c_shipLevels[2];
    //       }

    //      } else {
    //       robot.elevatorSubsystem.setLevel(1);
    //       ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.c_shipLevels[1];
    //     }

    //   }/*end ship*/ else if (robot.elevatorSubsystem.currentMode == ElevatorSubsystem.hatchMode){

    //     if (robot.elevatorSubsystem.currentLevel < 2){
    //       robot.elevatorSubsystem.setLevel(robot.elevatorSubsystem.currentLevel + 1);

    //       if (robot.elevatorSubsystem.currentLevel == 1){
    //         ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.r_hatchLevels[1];
    //       } else if (robot.elevatorSubsystem.currentLevel == 2){
    //         ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.r_hatchLevels[2];
    //       }

    //     } else {
    //       robot.elevatorSubsystem.setLevel(1);
    //       ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.r_hatchLevels[1];
    //     }

    //   }
    //   //end hatch
      
    //   //new ElevatorAutoCommand(robot, tempCurrentLevel).start();
    // } /*end up*/ /*begin down*///else if (Controls.vatorGoHome()){
    //   //new ElevatorAutoCommand(robot, 0).start();
    // // }
    // //end down
    
    // //begin mode switch
    // else if(Controls.vatorModeSwitchNext() && !vatorModeSwitchNextButtonFlag){
      
    //   vatorModeSwitchNextButtonFlag = true;


    //   if (robot.elevatorSubsystem.currentMode < 2){
    //     robot.elevatorSubsystem.setMode(robot.elevatorSubsystem.currentMode + 1);
        
    //     if (robot.elevatorSubsystem.currentMode == 1){
    //       ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.r_ballLevels[robot.elevatorSubsystem.currentLevel];
    //     } else if (robot.elevatorSubsystem.currentMode == 2){
    //       ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.r_hatchLevels[robot.elevatorSubsystem.currentLevel];
    //     }

    //   } else {
    //     robot.elevatorSubsystem.setMode(0);

    //     ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.c_shipLevels[robot.elevatorSubsystem.currentLevel];
    //   }

    // } else if (Controls.vatorModeSwitchPrev() && !vatorModeSwitchPrevButtonFlag){

    //   vatorModeSwitchPrevButtonFlag = true;

    //   if (robot.elevatorSubsystem.currentMode > 0){
    //     robot.elevatorSubsystem.setMode(robot.elevatorSubsystem.currentMode - 1);

    //     if (robot.elevatorSubsystem.currentMode == 1){
    //       ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.r_ballLevels[robot.elevatorSubsystem.currentLevel];
    //     } else if (robot.elevatorSubsystem.currentMode == 0){
    //       ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.c_shipLevels[robot.elevatorSubsystem.currentLevel];
    //     }

    //   } else {
    //     robot.elevatorSubsystem.setMode(2);
    //     ElevatorSubsystem.maxUpperLimit = ElevatorSubsystem.r_hatchLevels[robot.elevatorSubsystem.currentLevel];
    //   }

    // } else if (Controls.vatorGoHome() && !vatorHomeButtonFlag){

    //   vatorHomeButtonFlag = true;

    //   robot.elevatorSubsystem.setLevel(ElevatorSubsystem.homeLevel);
    // }
    // //end mode switch 
    

    // //once button is released, set flag to false
    // if (!Controls.vatorGoHome()){
    //   vatorHomeButtonFlag = false;
    // }

    // if (!Controls.vatorUpLevel()){
    //   vatorUpButtonFlag = false;
    // }

    // if (!Controls.vatorModeSwitchNext()){
    //   vatorModeSwitchNextButtonFlag = false;
    // }

    // if (!Controls.vatorModeSwitchPrev()){
    //   vatorModeSwitchPrevButtonFlag = false;
    // }

    /**************************************************************************************************************** */

    // //if limit is true and vator is going down (thumbstick down) then stop
    // if (robot.elevatorSubsystem.getLimitCurrentValue() && Controls.elevatorManual() < 0.0){
    //   robot.elevatorSubsystem.setSpeed(0.0);
      
    //   //if thumbstick is moving and encoder val is below upper limit, drive
    // } else if (Controls.elevatorManual() != 0 && robot.elevatorSubsystem.getEncoderCurrentValue() > ElevatorSubsystem.maxUpperLimit){
    //   robot.elevatorSubsystem.setSpeed(Controls.elevatorManual());
    // } else {
    //   //else no
    //   robot.elevatorSubsystem.setSpeed(0.0);
    // }

    robot.elevatorSubsystem.setSpeed(Controls.elevatorManual());


  }

  
  protected boolean isFinished() {
    return false;
  }

  
  protected void end() {
    
  }

  
  @Override
  protected void interrupted() {
  }
}
