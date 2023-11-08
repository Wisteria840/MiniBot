// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.reflect.Array;

import org.opencv.core.Mat;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

public class ZagZig extends CommandBase {
  private static final class Config{
    private static final double kRobotWidth = 30; /* width in inches */
    private static final double kWheelDiameter = 12; /* in inches */
    private static final int kTicksPerRevolution = 2048;

    private static final double kP = 0.5;
    private static final double kI = 0;
    private static final double kD = 0;

    private static final double speedMultiply = 0.5;

  }

  /** Creates a new ZagZig. */
  static private Drivetrain m_driveTrain;
  private double 
  private PIDController m_pidController = new PIDController(Config.kP, Config.kI, Config.kD);
  public ZagZig(Drivetrain drivetrain, double turnDegrees, double turnRadius) {
    m_driveTrain = drivetrain;
    m_turnDegrees = turnDegrees;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public static double [] getTurnGoals(double turnDegrees, double turnRadius, boolean left){
    double insideGoal = (turnRadius) * (Math.toRadians(turnDegrees));
    double outsideGoal = (turnRadius + Config.kRobotWidth) * (Math.toRadians(turnDegrees));

    
  }


  


  public static double toTicks(double distance){ /* input distance in inches output encoder ticks*/
    return (distance/(Config.kWheelDiameter*Math.PI)) * Config.kTicksPerRevolution;
  }
}

