package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.sussy.PineappleSomething;

@TeleOp
public class PineappleOp extends PineappleSomething {

    @Override public void runOpMode() {
        final double driveSpeedFactor = 0.69;
        final double armPowerFactor = 0.75;

        mapHardwareAndReverseMotors();

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

        g.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        h.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        j.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        g.setPower(armPowerFactor);
        h.setPower(armPowerFactor);
        j.setPower(armPowerFactor);
        while (opModeIsActive()) {
            if(gamepad1.right_stick_button || gamepad2.right_stick_button) {
                resetLiftEncoders();
            }
            else {
                g.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
                h.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
                j.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

            }


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
            // Raises arm to target junction height by pressing dpad button
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

            else if (gamepad2.left_bumper || gamepad1.left_bumper) {
                intakeHeight();
            }

            else if (gamepad1.y || gamepad2.y) {
                scoreCone();
            }

            // Manual control of the lift


            liftMotorPower();
            telemetry.addData("Arm position", g.getCurrentPosition());
            telemetry.addData("Target: ", g.getTargetPosition());
            telemetry.addData("Arm Power", g.getPower());
            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
            telemetry.addData("Right Stick Y", gamepad1.right_stick_y);
            telemetry.addData("Right Trigger", gamepad2.right_trigger);
            telemetry.addData("Left Trigger", gamepad2.left_trigger);
            telemetry.addData("THING", frontLeft.getPower());
            telemetry.addData("left stick button", gamepad2.left_stick_button);
            telemetry.addData("left stick button", frontRight.getCurrentPosition());
            telemetry.addData("left stick button", frontLeft.getCurrentPosition());
            telemetry.addData("left stick button", backRight.getCurrentPosition());
            telemetry.addData("left stick button", backLeft.getCurrentPosition());
            telemetry.update();
        }

    }

    //Sets the lift power based on the trigger value
    public void liftMotorPower() {
        int lastPosition = g.getCurrentPosition();
        int MAX_HEIGHT = -2542;

       // int MIN_HEIGHT = -1;


        //Sets a max height, so the lift does not pull the string too much
//        if(g.getCurrentPosition()-lastPosition<42 && g.getCurrentPosition()<g.getTargetPosition()) {
//            resetLiftEncoders();
//
//        }
        if(g.getCurrentPosition() <= MAX_HEIGHT && !(gamepad2.left_stick_button)) {
            g.setTargetPosition(MAX_HEIGHT);
            h.setTargetPosition(MAX_HEIGHT);
            j.setTargetPosition(MAX_HEIGHT);
            telemetry.addData("no", "jo");
        }

        //Sets a min height, so the intake does not slam into the ground
//        else if(g.getCurrentPosition() >= MIN_HEIGHT && !(gamepad2.right_stick_button)) {
//            g.setTargetPosition(MIN_HEIGHT);
//            h.setTargetPosition(MIN_HEIGHT);
//            j.setTargetPosition(MIN_HEIGHT);
//            telemetry.addData("bo", "what");
//        }

        //Updates the lift's target position using the triggers
        else if (gamepad2.right_bumper == false){
            if(gamepad2.right_trigger > 0) {
                g.setTargetPosition((g.getTargetPosition() - (int) (69 * (gamepad2.right_trigger))));
                h.setTargetPosition((h.getTargetPosition() - (int) (69 * (gamepad2.right_trigger))));
                j.setTargetPosition((j.getTargetPosition() - (int) (69 * (gamepad2.right_trigger))));

            }
            else if(gamepad2.left_trigger > 0) {
                g.setTargetPosition((g.getTargetPosition() + (int) (69 * (gamepad2.left_trigger))));
                h.setTargetPosition((h.getTargetPosition() + (int) (69 * (gamepad2.left_trigger))));
                j.setTargetPosition((j.getTargetPosition() + (int) (69 * (gamepad2.left_trigger))));

            }


        }
        if(gamepad2.right_bumper == true){
            shutUpNathan.setPower(gamepad2.right_trigger*5);
            shutUpNathan.setPower(-5*(gamepad2.left_trigger));
        }

        //Sets the lift motors' power proportionally to its current-target position
        int average = (g.getCurrentPosition() + h.getCurrentPosition() + j.getCurrentPosition())/3;

        g.setPower(-0.0067*(average-g.getTargetPosition()) / 1);
        h.setPower(-0.0067*(average-g.getTargetPosition()) / 1);
        j.setPower(-0.0067*(average-g.getTargetPosition()) / 1);



    }

    //Controls the state of the intake using a, b, x
    public void intakeControl(CRServo thing) {
        if (gamepad2.b || gamepad1.b)
            thing.setPower(0);
        else if (gamepad2.a || gamepad1.a)
            thing.setPower(1);
        else if (gamepad2.x || gamepad1.x)
            thing.setPower(-1);
    }

    //Sets lift motors' target position to junction height
    public void scoreGround() {
        g.setTargetPosition(-401);
        h.setTargetPosition(-401);
        j.setTargetPosition(-401);
    }
    public void scoreLow() {
        g.setTargetPosition(-1042);
        h.setTargetPosition(-1042);
        j.setTargetPosition(-1042);
    }
    public void scoreMedium() {
        g.setTargetPosition(-1669-4-42-6-9-9);
        h.setTargetPosition(-1669-4-42-6-9-9);
        j.setTargetPosition(-1669-4-42-6-9-9);
    }
    public void scoreHigh() {
        g.setTargetPosition(-2620);
        h.setTargetPosition(-2620);
        j.setTargetPosition(-2620);
    }
    public void intakeHeight() {
        g.setTargetPosition(-300);
        h.setTargetPosition(-300);
        j.setTargetPosition(-300);
    }
    public void scoreCone() {
        g.setTargetPosition(-6);
        h.setTargetPosition(-6);
        j.setTargetPosition(-6);
    }

    //Resets only the lift encoders
    //Makes sure the macros, min, and max height restrictions work
    public void resetLiftEncoders() {
        g.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        h.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        j.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        g.setTargetPosition(0);
        h.setTargetPosition(0);
        j.setTargetPosition(0);
    }


}


