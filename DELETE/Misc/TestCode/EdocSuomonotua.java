package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous
public class EdocSuomonotua extends LinearOpMode{
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor g;
    private CRServo thing;
    @Override public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "motor1");
        backLeft = hardwareMap.get(DcMotor.class, "motor2");
        frontRight = hardwareMap.get(DcMotor.class, "motor3");
        backRight = hardwareMap.get(DcMotor.class, "motor4");
        g = hardwareMap.get(DcMotor.class, "g");
        thing = hardwareMap.get(CRServo.class, "thing");
        
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        while (opModeIsActive()) {
            // double frontRightPower = gamepad1.right_stick_y - gamepad1.right_stick_x;
            // double frontLeftPower = gamepad1.left_stick_y - gamepad1.left_stick_x;
            // double backRightPower = gamepad1.right_stick_y + gamepad1.right_stick_x;
            // double backLeftPower = gamepad1.left_stick_y + gamepad1.left_stick_x;
            
            double frontLeftPower = (1)*(gamepad1.left_stick_y + gamepad1.right_stick_y) - (gamepad1.left_stick_x) - gamepad1.right_stick_x;
            double frontRightPower = (1)*(gamepad1.left_stick_y + gamepad1.right_stick_y) + (gamepad1.left_stick_x) + gamepad1.right_stick_x;
            double backLeftPower = (1)*(gamepad1.left_stick_y + gamepad1.right_stick_y) - (gamepad1.left_stick_x) + gamepad1.right_stick_x;
            double backRightPower = (1)*(gamepad1.left_stick_y + gamepad1.right_stick_y) + (gamepad1.left_stick_x) - gamepad1.right_stick_x;
            double gPower = (1)*((gamepad1.right_trigger-gamepad1.left_trigger)/2);
            double thingPower = 0;
            if(gamepad1.right_bumper==true) thingPower = -1;
            else if(gamepad1.left_bumper==true) thingPower = 1;
            else thingPower = 0;
            

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);
            g.setPower(gPower);
            thing.setPower(thingPower);
        
             telemetry.addData("sussy balls: ",gamepad1.right_trigger);
             telemetry.update();
            sleep(50);
        }
    }
}
