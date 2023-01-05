package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.sussy.PineappleSomething;

@TeleOp
public class PineappleOp extends PineappleSomething {

    public void intakeControl(CRServo thing) {
        // Control intake with buttons
        // A: Intake
        // X: Outtake
        // B: Stop intake/outtake
        if (gamepad2.b || gamepad1.b)
            thing.setPower(0);
        else if (gamepad2.a || gamepad1.a)
            thing.setPower(1);
        else if (gamepad2.x || gamepad1.x)
            thing.setPower(-1);
    }
    @Override public void runOpMode() {
        double thingPower = 0;
        int intakeState = 0;
        final double driveSpeedFactor = 0.57;
        final double armPowerFactor = 0.69;
        final double holdPower = .1;

        mapHardwareAndReverseMotors();
// 182 - pick up, 401 - ground cone, 1269 - low, 1947 - medium,2609 - high
        //// START

        waitForStart();
        resetEncoders();

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        g.setTargetPosition(0);
        h.setTargetPosition(0);
        j.setTargetPosition(0);

        g.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        h.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        j.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        g.setPower(armPowerFactor);
        h.setPower(armPowerFactor);
        j.setPower(armPowerFactor);
        while (opModeIsActive()) {
            // Control intake with X,A,B
            intakeControl(thing);
            // Set power of motors
            frontLeft.setPower(
                    driveSpeedFactor * ((gamepad1.left_stick_y + gamepad1.right_stick_y) - (1.15*gamepad1.left_stick_x) - gamepad1.right_stick_x)
            );
            backLeft.setPower(driveSpeedFactor * (
                    (gamepad1.left_stick_y + gamepad1.right_stick_y) - (1.15*gamepad1.left_stick_x) + gamepad1.right_stick_x)
            );
            frontRight.setPower(
                    driveSpeedFactor * ((gamepad1.left_stick_y + gamepad1.right_stick_y) + (1.15*gamepad1.left_stick_x) + gamepad1.right_stick_x)
            );
            backRight.setPower(
                    driveSpeedFactor * ((gamepad1.left_stick_y + gamepad1.right_stick_y) + (1.15*gamepad1.left_stick_x) - gamepad1.right_stick_x)
            );

            // Arm motor power
            armMotorPower();

            // Turn off encoder limit when trigger pressed
//            if (gamepad1.right_trigger > 0 || gamepad1.left_trigger > 0) {
//                g.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                h.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                j.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            }

            if(gamepad2.dpad_down ||gamepad1.dpad_down) {
                scoreGround();
            }

            else if(gamepad2.dpad_left || gamepad1.dpad_left) {
                scoreLow();
            }

            else if(gamepad2.dpad_right || gamepad1.dpad_right) {
                scoreMedium();
            }

            else if (gamepad2.dpad_up || gamepad1.dpad_up) {
                scoreHigh();
            }

            else if (gamepad2.y || gamepad1.left_bumper) {
                intakeHeight();
            }



            // Telemetry
            telemetry.addData("Arm position", g.getCurrentPosition());
            telemetry.addData("Target: ", g.getTargetPosition());
            telemetry.addData("Arm Power", g.getPower());
            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
            telemetry.addData("Right Stick Y", gamepad1.right_stick_y);
            telemetry.addData("Right Trigger", gamepad2.right_trigger);
            telemetry.addData("Left Trigger", gamepad2.left_trigger);
            telemetry.addData("THING", frontLeft.getPower());
            telemetry.update();
        }

    }
    public void armMotorPower() {

        int MAX_HEIGHT = 2690;
        int MIN_HEIGHT = 69;
        if(g.getCurrentPosition() >= MAX_HEIGHT) {
            g.setTargetPosition(MAX_HEIGHT);
            h.setTargetPosition(MAX_HEIGHT);
            j.setTargetPosition(MAX_HEIGHT);
        }

        if(g.getCurrentPosition() <= MIN_HEIGHT) {
            g.setTargetPosition(MIN_HEIGHT);
            h.setTargetPosition(MIN_HEIGHT);
            j.setTargetPosition(MIN_HEIGHT);
        }

        else {
            g.setTargetPosition(g.getTargetPosition() + (int) (69 * (gamepad2.right_trigger - gamepad2.left_trigger)));
            h.setTargetPosition(h.getTargetPosition() + (int) (69 * (gamepad2.right_trigger - gamepad2.left_trigger)));
            j.setTargetPosition(j.getTargetPosition() + (int) (69 * (gamepad2.right_trigger - gamepad2.left_trigger)));
        }
        if (Math.abs(g.getCurrentPosition() - g.getTargetPosition()) < 7) {
            g.setPower(0.1);
            h.setPower(0.1);
            j.setPower(0.1);
        }
        else {
            g.setPower(0.5);
            h.setPower(0.5);
            j.setPower(0.5);
        }


    }


    public void scoreGround() {
        g.setTargetPosition(401);
        h.setTargetPosition(401);
        j.setTargetPosition(401);
    }
    public void scoreLow() {
        g.setTargetPosition(1042);
        h.setTargetPosition(1042);
        j.setTargetPosition(1042);
    }
    public void scoreMedium() {
        g.setTargetPosition(1669+4);
        h.setTargetPosition(1669+4);
        j.setTargetPosition(1669+4);
    }
    public void scoreHigh() {
        g.setTargetPosition(2620);
        h.setTargetPosition(2620);
        j.setTargetPosition(2620);

    }
    public void intakeHeight() {
        g.setTargetPosition(169);
        h.setTargetPosition(169);
        j.setTargetPosition(169);
    }



}
