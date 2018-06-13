package com.andoverrobotics.core.examples;

import com.andoverrobotics.core.drivetrain.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Mecanum TeleOp Example", group = "ARC")
public class MecanumTeleOpExample extends OpMode {

  private static final int ticksPerInch = 20, ticksPer360 = 200;

  private DcMotor motorFL, motorFR, motorBL, motorBR;
  private MecanumDrive mecanumDrive;

  @Override
  public void init() {
    motorFL = hardwareMap.dcMotor.get("motorFL");
    motorFR = hardwareMap.dcMotor.get("motorFR");
    motorBL = hardwareMap.dcMotor.get("motorBL");
    motorBR = hardwareMap.dcMotor.get("motorBR");

    mecanumDrive = MecanumDrive.fromOctagonalMotors(
        motorFL, motorFR, motorBL, motorBR, this, ticksPerInch, ticksPer360);
  }

  @Override
  public void loop() {

    if (Math.abs(gamepad1.right_stick_x) > 0.1) {
      mecanumDrive.setRotationPower(gamepad1.right_stick_x);
    } else {
      mecanumDrive.setStrafe(gamepad1.left_stick_x, -gamepad1.left_stick_y);
    }

    telemetry.addData("Left stick X", gamepad1.left_stick_x);
    telemetry.addData("Left stick Y", -gamepad1.left_stick_y);
    telemetry.update();
  }
}
