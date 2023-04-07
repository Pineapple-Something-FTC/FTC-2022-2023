package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Utility.Arm;
import org.firstinspires.ftc.teamcode.Utility.PineappleSomething;

@Autonomous
public class XBlueRed extends PineappleSomething {
    final int ticksTile = 1450;

    @Override
    public void runOpMode() {
        //// INIT
        frontLeft = hardwareMap.get(DcMotorEx.class, "motor1");
        backLeft = hardwareMap.get(DcMotorEx.class, "motor2");
        frontRight = hardwareMap.get(DcMotorEx.class, "motor3");
        backRight = hardwareMap.get(DcMotorEx.class, "motor4");
        armMotor1 = hardwareMap.get(DcMotorEx.class, "g");
        intakeServo = hardwareMap.get(CRServo.class, "thing");
        potentiometer = hardwareMap.get(AnalogInput.class, "deez2");
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        //resetEncoders();
        //// START
        waitForStart();

//        move(1270, forward, speed);
//        sleep(2500);
//        turn(615, right, speed);
//        sleep(1500);
        Arm.executorFunc();
        sleep(1500);
        Arm.low();
//        Move.straight(500, forward, speed);
//        sleep(1000);
//        thing.setPower(-1);
//        sleep(500);
//        Move.straight(500,false,speed);
//        sleep(1000);

        // Stop motors from running to position
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Telemetry
        while (opModeIsActive()) {
            telemetry.addData("Arm position", armMotor1.getCurrentPosition());
            telemetry.update();
        }
    }
}


