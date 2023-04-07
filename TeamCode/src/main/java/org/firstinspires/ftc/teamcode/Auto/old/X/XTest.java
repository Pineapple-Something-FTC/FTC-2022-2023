package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Utility.PineappleSomething;

@Autonomous
public class XTest extends PineappleSomething {

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
        frontLeft = hardwareMap.get(DcMotorEx.class, "motor1");
        potentiometer = hardwareMap.get(AnalogInput.class, "deez2");
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        //resetEncoders();
        //// START
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Potentiometer value:", potentiometer.getVoltage());
            telemetry.update();
        }
    }
}

