package frc.robot;

import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Coone;
import frc.robot.commands.Cyube;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {
  private static final class Config{ // all need to be changed
    private static final int kJoystickPort = 1;
    private static final int kConeButtonPort = 2;
    private static final int kCubeButtonPort = 3;
  }

  private Joystick m_joystick = new Joystick(Config.kJoystickPort);
  private JoystickButton m_coneButton = new JoystickButton(m_joystick, Config.kConeButtonPort);
  private JoystickButton m_cubeButton = new JoystickButton(m_joystick, Config.kCubeButtonPort);

  private Drivetrain m_drivetrain = new Drivetrain();
  private Intake m_intake = new Intake();

  private ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_joystick, m_drivetrain);
  private Coone m_coone = new Coone(m_intake);
  private Cyube m_cyube = new Cyube(m_intake);


  
  

  public RobotContainer() {
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    m_coneButton.onTrue(m_coone);
    m_cubeButton.onTrue(m_cyube);
    m_turboButton.whileTrue(m_arcadeDrive);

  }

  public Command getAutonomousCommand() {
    return null;
  }

  public Command getTeleopCommand(){
    m_drivetrain.setDefaultCommand(m_arcadeDrive);
    return null;
  }

}
