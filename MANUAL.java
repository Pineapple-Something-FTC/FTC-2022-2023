package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "sure (Blocks to Java)")
public class sure extends LinearOpMode {


  private DcMotorEx arm1;
  private DcMotorEx arm2;


  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
      arm1  = hardwareMap.get(DcMotorEx.class, "arm0");
  arm2 = hardwareMap.get(DcMotorEx.class, "arm1");

    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        arm1.setPower(gamepad1.left_stick_x);
        arm2.setPower(-(gamepad1.right_stick_x));
      }
    }
  }
}