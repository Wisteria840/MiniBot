// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class viveLaRevolutionRight extends CommandBase {
  private static final class Config{
    private static final double rotInHell = 0.1; //motor speed
    private static final double tolerance = 2; // in ticks
    private static final double robotWidth = 30; // measure later in inches
    private static final double circumference = robotWidth * Math.PI;
    private static final double ticksPerRevolution = 2048;
  }

  double m_goalDegree;
  double m_leftGoal;
  double m_initalLeftPosition;
  double m_currentLeftPosition;
  Drivetrain m_driveTrain;
  double m_direction;

  public viveLaRevolutionRight(double goalDegree, Drivetrain drivetrain) {
    m_leftGoal = Math.abs(AutoZagZig.toTicks(Math.toRadians(goalDegree) * (AutoZagZig.Config.kRobotWidth/2)));
    m_driveTrain = drivetrain;
    addRequirements(m_driveTrain);
    m_direction = 1;
    if (goalDegree < 0){
      m_direction = -1;
    }
  }

  @Override
  public void initialize() {
    m_leftGoal = m_leftGoal + m_driveTrain.getLeftPosition();
    
    
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("Left Goal", m_leftGoal);

    m_driveTrain.setLeftSpeed(Config.rotInHell * m_direction);
    m_driveTrain.setRightSpeed(Config.rotInHell * m_direction * -1); /* be sure this negative sign is working */
    
  }

  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setLeftSpeed(0);
    m_driveTrain.setRightSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return m_leftGoal - m_driveTrain.getLeftPosition() <= Config.tolerance;
  }
}
