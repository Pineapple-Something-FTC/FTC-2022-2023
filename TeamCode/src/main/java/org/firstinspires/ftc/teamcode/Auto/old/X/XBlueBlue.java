package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Utility.Drive;
import org.firstinspires.ftc.teamcode.Utility.PineappleSomething;

@Autonomous
public class XBlueBlue extends PineappleSomething {

    final int speed = 700;

    @Override
    public void runOpMode() {
        //// INIT
        frontLeft = hardwareMap.get(DcMotorEx.class, "motor1");
        backLeft = hardwareMap.get(DcMotorEx.class, "motor2");
        frontRight = hardwareMap.get(DcMotorEx.class, "motor3");
        backRight = hardwareMap.get(DcMotorEx.class, "motor4");
        armMotor1 = hardwareMap.get(DcMotorEx.class, "g");
        intakeServo = hardwareMap.get(CRServo.class, "thing");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        Drive drive = new Drive();
        drive.straight(1400, forward, speed, 2000);
        drive.straight(1400, back, speed, 2000);
        drive.strafe(1500, left, speed, 2000);
        //scoreLow();
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

