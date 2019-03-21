package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Robot;

public class DefaultBallCommand extends Command {

  private Robot robot;

  public DefaultBallCommand(Robot robot) {
    this.robot = robot;
    requires(robot.ballSubsystem);
  }

  protected void initialize() {
  }

  protected void execute() {
    if (Controls.beaterBarPush() && !Controls.beaterBarSuck()){
      robot.ballSubsystem.setIntakeConstantForwardSpeed();
    } else if (Controls.beaterBarSuck() && !Controls.beaterBarPush()){
      robot.ballSubsystem.setIntakeConstantReverseSpeed();
    } else {
      robot.ballSubsystem.disableMotor();
    }
    
    if (Controls.shootBall()){
      robot.ballSubsystem.deployEnable();
    } else {
      robot.ballSubsystem.deployDisable();
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
