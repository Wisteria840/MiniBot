package frc.robot.commands;


import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class ArcadeDrive extends CommandBase {
  private static final class Config{
    public static final int kLeftYAxis = 1; // change these
    public static final int kRightXAxis = 4; //change these
    public static final int kTurboAxis = 2; // change these


    public static final double kSpeedMultiplier = 0.2;
    public static final double kTurnMultiplier = 0.2;
    public static final double kTurboMultiplier = 1;
    public static final boolean kSetTurbo = false; /* true makes the turbo digital, otherwise analog */


  }
  private Joystick m_joystick;
  private Drivetrain m_drivetrain;


  public ArcadeDrive(Joystick joystick, Drivetrain drivetrain) {
    m_joystick = joystick;
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);

    
  }

  @Override
  public void initialize() {
    m_drivetrain.setIdleMode(NeutralMode.Brake);
  }

  @Override
  public void execute() {
    double speed = m_joystick.getRawAxis(Config.kLeftYAxis) * Config.kSpeedMultiplier * -1;
      
    
    double turn  = m_joystick.getRawAxis(Config.kRightXAxis) * Config.kTurnMultiplier;
    double turbo = (1 + (m_joystick.getRawAxis(Config.kTurboAxis) * Config.kTurboMultiplier));
    if (Config.kSetTurbo == true && m_joystick.getRawAxis(Config.kTurboAxis) > 0){ // go away this is intentional
      turbo = 1 + Config.kTurboMultiplier;
    }
    

    double left = (speed + turn) * turbo;
    double right = (speed - turn) * turbo;

    
    m_drivetrain.setLeftSpeed(left);
    m_drivetrain.setRightSpeed(right);
    SmartDashboard.putNumber("Left Position", m_drivetrain.getLeftPosition());
    SmartDashboard.putNumber("Right number", m_drivetrain.getRightPosition());
  }


  @Override
  public void end(boolean interrupted) {

    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
