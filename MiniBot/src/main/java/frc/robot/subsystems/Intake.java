package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private static final class Config{
    private static final int kIntakeMotorID = 2; // change
    private static final double kIntakeSpeed = 0.4;
    
  }
  private CANSparkMax m_intakeMotor = new CANSparkMax(Config.kIntakeMotorID, MotorType.kBrushless);

  public Intake() {}

  @Override
  public void periodic() {
  }

  public void cube(){
    m_intakeMotor.set(Config.kIntakeSpeed); /* check if the cube or cone is in the positive direction. */
  }

  public InstantCommand cyube(){
    return new InstantCommand(this::cube, this);
  }
 

  public void cone(){
    m_intakeMotor.set(Config.kIntakeSpeed * -1); /* check if this is the right direction */
  }
  public InstantCommand coone(){
    return new InstantCommand(this::cone, this);
  }

  

  public void halt(){
    m_intakeMotor.set(0);
  }
  }

