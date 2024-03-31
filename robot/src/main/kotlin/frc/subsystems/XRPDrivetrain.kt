// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.subsystems

import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj.xrp.XRPMotor

class XRPDrivetrain(
    private val leftMotor: XRPMotor,
    private val rightMotor: XRPMotor,
    private val leftEncoder: Encoder,
    private val rightEncoder: Encoder,
) {
    // Set up the differential drive controller
    private val diffDrive: DifferentialDrive = DifferentialDrive(leftMotor::set, rightMotor::set)

    fun initialize() {
        rightMotor.inverted = true
        resetEncoders()
    }

    fun arcadeDrive(xaxisSpeed: Double, zaxisRotate: Double) {
        diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate)
    }

    fun resetEncoders() {
        with(leftEncoder) {
            distancePerPulse = Math.PI * WHEEL_DIAMETER_INCH / COUNTS_PER_REVOLUTION
            reset()
        }
        with(rightEncoder) {
            distancePerPulse = Math.PI * WHEEL_DIAMETER_INCH / COUNTS_PER_REVOLUTION
            reset()
        }
    }

    val leftDistanceInch: Double
        get() = leftEncoder.distance

    val rightDistanceInch: Double
        get() = rightEncoder.distance

    companion object {
        private const val GEAR_RATIO = (30.0 / 14.0) * (28.0 / 16.0) * (36.0 / 9.0) * (26.0 / 8.0) // 48.75:1
        private const val COUNTS_PER_MOTOR_SHAFT_REV = 12.0
        private const val COUNTS_PER_REVOLUTION = COUNTS_PER_MOTOR_SHAFT_REV * GEAR_RATIO // 585.0
        private const val WHEEL_DIAMETER_INCH = 2.3622 // 60 mm
    }
}
