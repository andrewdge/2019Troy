package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Robot;

public class ElevatorAutoCommand extends Command {

  private Robot robot;
  private int targetLevel;

  public ElevatorAutoCommand(Robot robot, int targetLevel) {
    this.robot = robot;
    requires(robot.elevatorSubsystem);
    this.targetLevel = targetLevel;
    
  }

  
  protected void initialize() {
  }

  
  protected void execute() {
    

  }

  
  protected boolean isFinished() {
    return Controls.elevatorManual() != 0;
  }

  
  protected void end() {
    robot.elevatorSubsystem.currentLevel = targetLevel;
  }

  
  protected void interrupted() {
  }
}
