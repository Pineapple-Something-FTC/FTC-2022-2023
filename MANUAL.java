package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "MANUAL")
public class MANUAL extends LinearOpMode {
  private DcMotorEx arm1;
  private DcMotorEx arm2;
  private Servo servo1;
  private Servo servo2;
  private DcMotor driveL;
  private DcMotor driveR;
  double posX;
  double posY;

  @Override
  
  public void runOpMode() {
    arm1  = hardwareMap.get(DcMotorEx.class, "arm0");
    arm2 = hardwareMap.get(DcMotorEx.class, "arm1");
    servo1 = hardwareMap.get(Servo.class, "servo0");
    servo2 = hardwareMap.get(Servo.class, "servo1");
    servo2.setDirection(Servo.Direction.REVERSE);
    driveL = hardwareMap.get(DcMotor.class, "driveL");
    driveR = hardwareMap.get(DcMotor.class, "driveR");
    // Put initialization blocks here.

    waitForStart();
    // Put run blocks here.
    while (opModeIsActive()) {
      telemetry.addData("Position: ", servo2.getPosition());
      telemetry.update();
      
      posY = (gamepad1.right_stick_y / 10) + posY;
      posX = (gamepad1.right_stick_x / 10) + posX;
      servo1.setPosition(gamepad1.right_trigger / 2);
      servo2.setPosition(gamepad1.right_trigger / 2);
      arm1.setPower(gamepad1.left_stick_y/4);
      arm2.setPower((gamepad1.right_stick_y)/4);
      driveL.setPower(gamepad2.left_stick_y/2);
      driveR.setPower(-gamepad2.right_stick_y/2);
      
      sleep(200);
    }
  }
  // public void move(int distanceDrive) {
  //       // Sets Target
  //       driveL.setTargetPosition(-Math.round((float)(distanceDrive * driveTicksInches)));
  //       driveR.setTargetPosition(Math.round((float)(distanceDrive * driveTicksInches)));

  //       // Sets Mode
  //       driveL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  //       driveR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  //           boolean m;
  //       double currentDistance = (distanceDrive - driveL.getCurrentPosition()/driveTicksInches);
  //       double kP = 69 - 42.0+4.20-1-1;
  //       double kD = 6900;
  //       double pastDistance = currentDistance;
        
  //       while(currentDistance>=0) {
  //       // Sets Speed
        
  //       double p = currentDistance*kP;
  //       double d = kD * (currentDistance - pastDistance);
        
  //       driveL.setVelocity(p + d);
  //       driveR.setVelocity(p + d);
  //       pastDistance = currentDistance;
  //       sleep(50);
  //       }
  //       driveL.setVelocity(0);
  //       driveR.setVelocity(0);
        
        
        
  //   }
}
