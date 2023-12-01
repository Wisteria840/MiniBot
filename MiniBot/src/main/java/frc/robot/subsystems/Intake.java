package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private static final class Config{
    private static final int kIntakeMotorID = 5; // change
    private static final double kIntakeSpeed = 0.1;
    private static final VictorSPXControlMode kControlMode = VictorSPXControlMode.PercentOutput;
    
  }
  private VictorSPX m_intakeMotor = new VictorSPX(Config.kIntakeMotorID);

  public Intake() {}

  @Override
  public void periodic() {
  }

  public InstantCommand cyube(){
    return new InstantCommand(this::cube, this);
  }
  public void cube(){
    m_intakeMotor.set(Config.kControlMode, Config.kIntakeSpeed); /* check if the cube or cone is in the positive direction. */
  }

  public InstantCommand coone(){
    return new InstantCommand(this::cone, this);
  }

  public void cone(){
    m_intakeMotor.set(Config.kControlMode, Config.kIntakeMotorID * -1); /* check if this is the right direction */
  }

  public void halt(){
    m_intakeMotor.set(Config.kControlMode, 0);
  }
  }

