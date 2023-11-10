// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class viveLaRevolution extends CommandBase {
  private static final class Config{
    private static final double rotInHell = 0.5;
    private static final double tolerance = 0.4;
  }

  double m_goalDegree;

  double m_leftGoal;
  double m_rightGoal;
  
  double m_leftPosition;

  Drivetrain m_driveTrain;

  public viveLaRevolution(double goalDegree, Drivetrain drivetrain) {
    m_goalDegree = goalDegree;
    m_driveTrain = drivetrain;
  }

  @Override
  public void initialize() {
    m_rightGoal = AutoZagZig.toTicks((m_goalDegree/360) * (AutoZagZig.Config.kRobotWidth * Math.PI));
  }

  @Override
  public void execute() {
    m_leftPosition = m_driveTrain.getLeftPosition();
    m_driveTrain.setLeftSpeed(Config.rotInHell);
    m_driveTrain.setRightSpeed(-Config.rotInHell); /* be sure this negative sign is working */
    
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return (m_leftGoal - m_leftPosition) <= Config.tolerance;
  }
}
