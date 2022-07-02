package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
@TeleOp(name="ManYouOll")

public class ManYouOll extends LinearOpMode {
      private Servo servo1;
      private Servo servo2;
            // todo: write your code here
    @Override
    public void runOpMode() {
        servo1 = hardwareMap.get(Servo.class, "servo0");
        servo2 = hardwareMap.get(Servo.class, "servo1");
        servo1.setDirection(Servo.Direction.REVERSE);
        waitForStart();
       while (opModeIsActive()) {
       //// posY = (gamepad1.right_stick_y / 10) + posY;
       // posX = (gamepad1.right_stick_x / 10) + posX;
        telemetry.addData("grab", gamepad1.right_trigger * 100);
        telemetry.update();
        servo1.setPosition(gamepad1.right_trigger / 3);
      //  servo2.setPosition(gamepad1.right_trigger / 2);
      ////  arm1.setPower(gamepad1.left_stick_y);
       // arm2.setPower(-(gamepad1.right_stick_y));
        sleep(200);
      }
        
        servo1.setPosition(0);
        servo2.setPosition(0);
    }

}