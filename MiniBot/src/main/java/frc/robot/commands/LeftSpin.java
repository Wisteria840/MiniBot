

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LeftSpin extends CommandBase {
  private static final class Config{
    private static final double rotSped = 0.1; //motor speed
    private static final double tolerance = 2; // in ticks
  }

  double m_goalDegree;
  double m_rightGoal;
  Drivetrain m_driveTrain;

  public LeftSpin(double goalDegree, Drivetrain drivetrain) {
    
    m_rightGoal = (Drivetrain.toTicks(Math.toRadians(goalDegree)) * (Drivetrain.Config.kRobotWidth/2));
    m_driveTrain = drivetrain;
    addRequirements(m_driveTrain);
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("start position", m_driveTrain.getRightPosition());
    m_rightGoal = m_rightGoal + m_driveTrain.getRightPosition();
    
    
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("right goal", m_rightGoal);

    m_driveTrain.setLeftSpeed(-Config.rotSped);
    m_driveTrain.setRightSpeed(Config.rotSped); /* be sure this negative sign is working */
    
  }

  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setLeftSpeed(0);
    m_driveTrain.setRightSpeed(0);
    SmartDashboard.putNumber("end position", m_driveTrain.getRightPosition());
  }

  @Override
  public boolean isFinished() {
    return m_rightGoal - m_driveTrain.getRightPosition() <= Config.tolerance;
  }
  }
