// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;


public class viveLaRevolution extends CommandBase {

  /** Creates a new viveLaRevolution. */
  double m_goalDegree;
  double m_robotWidth;

  double m_leftGoal;
  double m_rightGoal;
  public viveLaRevolution(double goalDegree, double robotWidth) {
    m_goalDegree = goalDegree;
    m_robotWidth = robotWidth;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_rightGoal = (m_goalDegree/360) * (m_robotWidth * Math.PI);
    m_leftGoal = -m_rightGoal;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
