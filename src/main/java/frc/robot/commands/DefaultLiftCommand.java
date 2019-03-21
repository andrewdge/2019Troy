/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Robot;

public class DefaultLiftCommand extends Command {

  private Robot robot;

  public DefaultLiftCommand(Robot robot) {
    this.robot = robot;
    requires(robot.climbSubsystem);
  }

  protected void initialize() {
  }

  protected void execute() {
    if (Controls.liftEnable()){
      robot.climbSubsystem.deployLift();
    } else {
      robot.climbSubsystem.retractLift();
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
