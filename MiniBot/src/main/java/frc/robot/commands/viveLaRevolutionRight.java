// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class viveLaRevolutionRight extends CommandBase {
  private static final class Config{
    private static final double rotInHell = 0.5; //motor speed
    private static final double tolerance = 50; // in ticks
    private static final double robotWidth = 360; // measure later in inches
    private static final double circumference = robotWidth * Math.PI;
    private static final double ticksPerRevolution = 2048;
  }

  double m_goalDegree;
  double m_leftGoal;
  double m_initalLeftPosition;
  double m_currentLeftPosition;
  Drivetrain m_driveTrain;
  double m_error;

  public viveLaRevolutionRight(double goalDegree, Drivetrain drivetrain) {
    m_goalDegree = Math.toRadians(goalDegree);
    m_leftGoal = (Config.ticksPerRevolution * ((0.5 * Config.robotWidth) * m_goalDegree)) / Config.circumference;
    m_driveTrain = drivetrain;
    addRequirements(m_driveTrain);
  }

  @Override
  public void initialize() {
    m_initalLeftPosition = m_driveTrain.getLeftPosition();
  }

  @Override
  public void execute() {
    m_currentLeftPosition = m_driveTrain.getLeftPosition() - m_initalLeftPosition;
    m_error = m_leftGoal - m_currentLeftPosition;
    m_driveTrain.setLeftSpeed(Config.rotInHell);
    m_driveTrain.setRightSpeed(-Config.rotInHell); /* be sure this negative sign is working */
    
  }

  @Override
  public void end(boolean interrupted) {
    //m_driveTrain.setLeftSpeed(0);
    //m_driveTrain.setRightSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return (m_error >= -Config.tolerance) && (m_error <= Config.tolerance);
  }
}
