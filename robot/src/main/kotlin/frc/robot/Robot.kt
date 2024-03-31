package frc.robot

import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.xrp.XRPMotor
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler
import frc.subsystems.XRPDrivetrain

class Robot : TimedRobot() {
    private val joystick0 = Joystick(0) //drive joystick

    private val driveTrain = XRPDrivetrain(
        leftMotor = XRPMotor(0),
        rightMotor = XRPMotor(1),
        leftEncoder = Encoder(4, 5),
        rightEncoder = Encoder(6, 7),
    )

    private val autonomousChooser = SendableChooser<Command>()

    override fun robotInit() {
        driveTrain.initialize()

        with(autonomousChooser) {
            setDefaultOption("Nothing", null)
            SmartDashboard.putData("Autonomous Mode", this)
        }
    }

    override fun robotPeriodic() {
        driveTrain.arcadeDrive(joystick0.y, joystick0.x)
    }

    override fun autonomousInit() {
        val autonomous = autonomousChooser.selected
    }

    override fun autonomousPeriodic() {
    }

    override fun autonomousExit() {
    }

    override fun teleopInit() {
    }

    override fun teleopPeriodic() {
    }

    override fun teleopExit() {
        CommandScheduler.getInstance().cancelAll()
    }

    override fun testInit() {
    }
}
