// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ZagZig extends CommandBase {
  private static final class Config{
    
    private static final double speedMultiply = 0.5;
    private static final double tolerance = 0.1;

  }

  static private Drivetrain m_driveTrain;
  static private double m_turnDegrees;
  static private double m_turnRadius;
  static private boolean m_left;

  private double leftPosition;

  private double leftGoal;
  private double leftSpeed;
  private double rightSpeed;

  private double kRobotWidth = AutoZagZig.Config.kRobotWidth;


  public ZagZig(Drivetrain drivetrain, double turnDegrees, double turnRadius, boolean left) {
    m_driveTrain = drivetrain;
    m_turnDegrees = turnDegrees;
    m_turnRadius = turnRadius;
    m_left = left;


  }

  @Override
  public void initialize() {
    double OutsideInsideRatio = (m_turnRadius + kRobotWidth)/m_turnRadius;
    leftSpeed = Config.speedMultiply;
    rightSpeed = leftSpeed * OutsideInsideRatio;
    leftGoal = AutoZagZig.toTicks((m_turnRadius) * (Math.toRadians(m_turnDegrees))); /* inside */
    if (m_left == true){

    } else {
      leftGoal = leftGoal * OutsideInsideRatio;

      leftSpeed = leftSpeed * OutsideInsideRatio;
      rightSpeed = rightSpeed / OutsideInsideRatio;
    }
    leftGoal = leftGoal + m_driveTrain.getLeftPosition();
  }

  @Override
  public void execute() {
    leftPosition = m_driveTrain.getLeftPosition();
    m_driveTrain.setLeftSpeed(leftSpeed);
    m_driveTrain.setRightSpeed(rightSpeed);
    }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    if ((leftGoal - leftPosition) <= Config.tolerance){
      return true;
    } else {
      return false;
    }
  }


  


}

