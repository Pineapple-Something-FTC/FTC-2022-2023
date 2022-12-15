package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.sussy.*;

@Autonomous
public class RedBlue extends PineappleSomething {

    @Override public void runOpMode() {
        //// INIT
        frontLeft = hardwareMap.get(DcMotorEx.class, "motor1");
        backLeft = hardwareMap.get(DcMotorEx.class, "motor2");
        frontRight = hardwareMap.get(DcMotorEx.class, "motor3");
        backRight = hardwareMap.get(DcMotorEx.class, "motor4");
        g = hardwareMap.get(DcMotorEx.class, "g");
        thing = hardwareMap.get(CRServo.class, "thing");
        
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        
        resetEncoders();
        //// START
        waitForStart();
        Move.strafe(1500, left, speed);
        sleep(2000);

        // Stop motors from running to position
         frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
          backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Telemetry
        while (opModeIsActive()) {
            telemetry.addData("Arm position", g.getCurrentPosition());
            telemetry.update();
        }
    }
}
