// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class LeftSpin extends CommandBase {
  /** Creates a new LeftSpin. */
  private static final class Config{
    private static final double rotInHell = 0.5; //motor speed
    private static final double tolerance = 2; // in ticks
    private static final double robotWidth = 30; // measure later in inches
    private static final double circumference = robotWidth * Math.PI;
    private static final double ticksPerRevolution = 2048;
  }

  double m_goalDegree;
  double m_rightGoal;
  double m_initialRightPosition;
  double m_currentRightPosition;
  Drivetrain m_driveTrain;
  double m_error;
  public LeftSpin(double goalDegree, Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_goalDegree = Math.toRadians(goalDegree);
    m_rightGoal = (Config.ticksPerRevolution * ((0.5 * Config.robotWidth) * m_goalDegree)) / Config.circumference;
    m_driveTrain = drivetrain;
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_initialRightPosition = m_driveTrain.getRightPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_currentRightPosition = m_driveTrain.getRightPosition() - m_initialRightPosition;
    m_error = m_rightGoal - m_currentRightPosition;
    m_driveTrain.setLeftSpeed(-Config.rotInHell);
    m_driveTrain.setRightSpeed(Config.rotInHell); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setLeftSpeed(0);
    m_driveTrain.setRightSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_error <= Config.tolerance;
  }
}
