// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class ClaqClaq extends CommandBase {
  private static final class Config{
    private static final double speedMultiply = 0.5;
    private static final double tolerance = 0.5;
  }

  private double m_goal;
  private Drivetrain m_driveTrain;
  
  private double m_position;

  public ClaqClaq(double goal, Drivetrain drivetrain) { /* goal in feet*/
    m_driveTrain = drivetrain;
    m_goal = AutoZagZig.toTicks(goal) + drivetrain.getLeftPosition();
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    m_position = m_driveTrain.getLeftPosition();
    m_driveTrain.setLeftSpeed(Config.speedMultiply);
    m_driveTrain.setRightSpeed(Config.speedMultiply);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return (m_goal - m_position) <= Config.tolerance; /* might have to flip the signs */
  }
}
