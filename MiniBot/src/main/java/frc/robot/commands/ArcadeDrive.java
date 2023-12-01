package frc.robot.commands;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class ArcadeDrive extends CommandBase {
  private static final class Config{
    public static final int kLeftYAxis = 1; // change these
    public static final int kRightXAxis = 4; //change these
    public static final int kTurboAxis = 6; // change these

    public static final double kSpeedMultiplier = 0.5;
    public static final double kTurnMultiplier = 0.5;
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
  }

  @Override
  public void execute() {
    double speed = m_joystick.getRawAxis(Config.kRightXAxis) * Config.kSpeedMultiplier;
    double turn  = m_joystick.getRawAxis(Config.kLeftYAxis) * Config.kTurnMultiplier;
    double turbo = (1 + (m_joystick.getRawAxis(Config.kTurboAxis) * Config.kTurboMultiplier));
    if (Config.kSetTurbo == true){ // go away this is intentional
      turbo = Config.kTurboMultiplier;
    }


    double left = (speed + turn) * turbo;
    double right = (speed - turn) * turbo;


    m_drivetrain.setLeftSpeed(left);
    m_drivetrain.setRightSpeed(right);
    
  }


  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setLeftSpeed(0);
    m_drivetrain.setRightSpeed(0);
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
