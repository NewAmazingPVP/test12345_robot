// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final CommandXboxController m_driverController =
      new CommandXboxController(1);
  private final XboxController m_XboxControler = new XboxController(0);
  
  private final DriveSub m_shootballsub = new DriveSub();
  //private final Drive m_ShootBallCmd = new Drive(m_shootballsub, m_driverController);

  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final moveArmUp m_moveArmUpCmd = new moveArmUp(m_intake);
  private final moveArmDown m_moveArmDownCmd = new moveArmDown(m_intake);
  private final microArmCMD m_MicroArmCMD = new microArmCMD(m_intake, m_XboxControler);
  public final Command m_autoMoveArm = new AutonomousTest(m_intake, m_shootballsub);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // m_shootballsub.setDefaultCommand(m_ShootBallCmd);
    // Configure the trigger bindings
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
    m_driverController.a().onTrue(m_moveArmUpCmd);
    m_driverController.b().onTrue(m_moveArmDownCmd);
    m_intake.setDefaultCommand(m_MicroArmCMD);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
