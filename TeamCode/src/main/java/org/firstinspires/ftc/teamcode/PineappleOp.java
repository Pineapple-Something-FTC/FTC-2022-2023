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
        final double driveSpeedFactor = 0.67;
        final double armPowerFactor = 0.75;
        final double holdPower = .1;
        boolean state = false;
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
            armMotorPower();

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
//
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

            if(!state && gamepad2.left_stick_button) {

                state = true;
            }
            if(!(gamepad2.left_stick_button && state)) {
                state = false;
            }
            if(state && gamepad2.left_stick_button) {
                telemetry.addData("aojeoiajof", "OHFOHEOFOFHEH F");
            }
            telemetry.addData("Arm position", g.getCurrentPosition());
            telemetry.addData("Target: ", g.getTargetPosition());
            telemetry.addData("Arm Power", g.getPower());
            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
            telemetry.addData("Right Stick Y", gamepad1.right_stick_y);
            telemetry.addData("Right Trigger", gamepad2.right_trigger);
            telemetry.addData("Left Trigger", gamepad2.left_trigger);
            telemetry.addData("THING", frontLeft.getPower());
            telemetry.addData("left stick button", gamepad2.left_stick_button);
            telemetry.update();
        }

    }

    public void armMotorPower() {

        int MAX_HEIGHT = -2542;

        int MIN_HEIGHT = -1;

        if(g.getCurrentPosition() <= MAX_HEIGHT) {
            g.setTargetPosition(MAX_HEIGHT);
            h.setTargetPosition(MAX_HEIGHT);
            j.setTargetPosition(MAX_HEIGHT);
            telemetry.addData("no", "jo");
        }

//       else if(g.getCurrentPosition() >= MIN_HEIGHT && !(gamepad2.left_stick_button)) {
//            g.setTargetPosition(MIN_HEIGHT);
//            h.setTargetPosition(MIN_HEIGHT);
//            j.setTargetPosition(MIN_HEIGHT);
//            telemetry.addData("bo", "what");
//        }

        else {
            if(gamepad2.right_trigger > 0) {
                g.setTargetPosition((g.getTargetPosition() - (int) (69+4+2+6+9+6+9+6 * (gamepad2.right_trigger))));
                h.setTargetPosition((h.getTargetPosition() - (int) (69+4+2+6+9+6+9+6 * (gamepad2.right_trigger))));
                j.setTargetPosition((j.getTargetPosition() - (int) (69+4+2+6+9+6+9+6 * (gamepad2.right_trigger))));

            }
            else if(gamepad2.left_trigger > 0) {
                g.setTargetPosition((g.getTargetPosition() + (int) (69+4+2+6+9+6+9+6 * (gamepad2.left_trigger))));
                h.setTargetPosition((h.getTargetPosition() + (int) (69+4+2+6+9+6+9+6 * (gamepad2.left_trigger))));
                j.setTargetPosition((j.getTargetPosition() + (int) (69+4+2+6+9+6+9+6 * (gamepad2.left_trigger))));

            }

        }
//        if (Math.abs(g.getCurrentPosition() - g.getTargetPosition()) < 7) {
//            g.setPower(0.1);
//            h.setPower(0.1);
//            j.setPower(0.1);
//        }
//        else {
//            g.setPower(0.75);
//            h.setPower(0.75);
//            j.setPower(0.75);
//        }
        int average = (g.getCurrentPosition() + h.getCurrentPosition() + j.getCurrentPosition())/3;
        g.setPower(-0.0069*(average-g.getTargetPosition()) / 1);
        h.setPower(-0.0069*(average-g.getTargetPosition()) / 1);
        j.setPower(-0.0069*(average-g.getTargetPosition()) / 1);


    }


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
        g.setTargetPosition(-1669-4-42-6-9);
        h.setTargetPosition(-1669-4-42-6-9);
        j.setTargetPosition(-1669-4-42-6-9);
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
    public void resetLiftEncoders() {
        g.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        h.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        j.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        g.setTargetPosition(0);
        h.setTargetPosition(0);
        j.setTargetPosition(0);
    }


}



//package org.firstinspires.ftc.teamcode;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//import org.firstinspires.ftc.teamcode.sussy.PineappleSomething;
//
//@TeleOp
//public class PineappleOp extends PineappleSomething {
//
//    @Override public void runOpMode() {
//        double thingPower = 0;
//        int intakeState = 0;
//        final double driveSpeedFactor = 0.57;
//        final double armPowerFactor = 0.69;
//        final double holdPower = .1;
//        final int ground = 401;
//        final int low = 1042;
//        final int medium = 1669+4;
//        final int high = 2369;
//        final int intakeHeight = 169;
//        final int getCone = 1;
//        mapHardwareAndReverseMotors();
//// 182 - pick up, 401 - ground cone, 1269 - low, 1947 - medium,2609 - high
//        //// START
//
//        waitForStart();
//        resetEncoders();
//
//        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        score(0);
//
//        g.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        h.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        j.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        setMotorPower(armPowerFactor);
//
//        while (opModeIsActive()) {
//            if(gamepad1.right_stick_button || gamepad2.right_stick_button) {
//                resetLiftEncoders();
//            }
//            // Control intake with X,A,B
//            intakeControl(thing);
//            // Set power of motors
//            frontLeft.setPower(
//                    driveSpeedFactor * ((gamepad1.left_stick_y + gamepad1.right_stick_y) - (1.15*gamepad1.left_stick_x) - gamepad1.right_stick_x)
//            );
//            backLeft.setPower(driveSpeedFactor * (
//                    (gamepad1.left_stick_y + gamepad1.right_stick_y) - (1.15*gamepad1.left_stick_x) + gamepad1.right_stick_x)
//            );
//            frontRight.setPower(
//                    driveSpeedFactor * ((gamepad1.left_stick_y + gamepad1.right_stick_y) + (1.15*gamepad1.left_stick_x) + gamepad1.right_stick_x)
//            );
//            backRight.setPower(
//                    driveSpeedFactor * ((gamepad1.left_stick_y + gamepad1.right_stick_y) + (1.15*gamepad1.left_stick_x) - gamepad1.right_stick_x)
//            );
//
//            // Arm motor power
//            armMotorPower();
//
//            if(gamepad2.dpad_down ||gamepad1.dpad_down) {
//                score(ground);
//            }
//
//            else if(gamepad2.dpad_left || gamepad1.dpad_left) {
//                score(low);
//            }
//
//            else if(gamepad2.dpad_right || gamepad1.dpad_right) {
//                score(medium);
//            }
//
//            else if (gamepad2.dpad_up || gamepad1.dpad_up) {
//                score(high);
//            }
//
//            else if (gamepad2.left_bumper || gamepad1.left_bumper) {
//                score(intakeHeight);
//            }
//
//            else if (gamepad2.y || gamepad1.y)
//                score(getCone);
//
//            // Telemetry
//            telemetry.addData("Arm position", g.getCurrentPosition());
//            telemetry.addData("Target: ", g.getTargetPosition());
//            telemetry.addData("h: ", h.getPower());
//            telemetry.addData("j: ", j.getPower());
//            telemetry.addData("Arm Power", g.getPower());
//            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
//            telemetry.addData("Right Stick Y", gamepad1.right_stick_y);
//            telemetry.addData("Right Trigger", gamepad2.right_trigger);
//            telemetry.addData("Left Trigger", gamepad2.left_trigger);
//            telemetry.addData("THING", frontLeft.getPower());
//            telemetry.update();
//        }
//
//    }
//    public void armMotorPower() {
//        int error = Math.abs(g.getCurrentPosition() - g.getTargetPosition());
//        int MAX_HEIGHT = 2690;
//        int MIN_HEIGHT = 1;
//        if(g.getCurrentPosition() >= MAX_HEIGHT) {
//            score(MAX_HEIGHT);
//        }
//
//        if(g.getCurrentPosition() <= MIN_HEIGHT) {
//            score(MIN_HEIGHT);
//        }
//
//        else {
//            score(g.getTargetPosition() + (int) (69 * (gamepad2.right_trigger - gamepad2.left_trigger)));
//        }
////        if (error < 5) {
//////            if(error < 5) {
//////                setMotorPower(0);
//////            }
//////            else
//////                setMotorPower(error*0.1);
////            setMotorPower(0.1);
////        }
////        else {
////            setMotorPower(0.5);
////        }
//        setMotorPower(-0.5);
//
//    }
//
//    public void setMotorPower(double power) {
//        g.setPower(power);
//        h.setPower(power);
//        j.setPower(power);
//    }
//
//    public void score(int height) {
//        g.setTargetPosition(height);
//        h.setTargetPosition(height);
//        j.setTargetPosition(height);
//    }
//
//    public void resetLiftEncoders() {
//        g.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        h.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        j.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//    }
//
//    public void intakeControl(CRServo thing) {
//        // Control intake with buttons
//        // A: Intake
//        // X: Outtake
//        // B: Stop intake/outtake
//        if (gamepad2.b || gamepad1.b)
//            thing.setPower(0);
//        else if (gamepad2.a || gamepad1.a)
//            thing.setPower(1);
//        else if (gamepad2.x || gamepad1.x)
//            thing.setPower(-1);
//    }
//
//}