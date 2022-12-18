package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Auto.PineappleSomething;

@TeleOp
public class PineappleOp extends PineappleSomething {
    public static DcMotorEx frontLeft;
    public static DcMotorEx backLeft;
    public static DcMotorEx frontRight;
    public static DcMotorEx backRight;
    public static DcMotorEx g;
    public static CRServo thing;
    //  public static AnalogInput deeznuts;

    public void intakeControl(CRServo thing, double thingPower) {
        // Set intake power
        thing.setPower(thingPower);

        // Control intake with buttons
        // A: Intake
        // X: Outtake
        // B: Stop intake/outtake
        if (gamepad1.b == true)
            thingPower = 0;
        else if (gamepad1.a == true)
            thingPower = 1;
        else if (gamepad1.x == true)
            thingPower = -1;
    }
    @Override public void runOpMode() {
        double frontLeftPower, frontRightPower, backLeftPower,
                backRightPower, gPower;
        double thingPower = 0;
        int intakeState = 0;
        final double driveSpeedFactor = 0.5;
        final double armPowerFactor = 0.5;

        //deeznuts = hardwareMap.get(AnalogInput.class, "deez2");
        frontLeft = hardwareMap.get(DcMotorEx.class, "motor1");
        backLeft = hardwareMap.get(DcMotorEx.class, "motor2");
        frontRight = hardwareMap.get(DcMotorEx.class, "motor3");
        backRight = hardwareMap.get(DcMotorEx.class, "motor4");
        g = hardwareMap.get(DcMotorEx.class, "g");
        thing = hardwareMap.get(CRServo.class, "thing");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

//        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();

        while (opModeIsActive()) {
            // Drive
            // double frontRightPower = gamepad1.right_stick_y - gamepad1.right_stick_x;
            // double frontLeftPower  = gamepad1.left_stick_y - gamepad1.left_stick_x;
            // double backRightPower  = gamepad1.right_stick_y + gamepad1.right_stick_x;
            // double backLeftPower   = gamepad1.left_stick_y + gamepad1.left_stick_x;

            frontLeftPower = driveSpeedFactor * ((gamepad1.left_stick_y + gamepad1.right_stick_y) - (1.15*gamepad1.left_stick_x) - gamepad1.right_stick_x);
            frontRightPower = driveSpeedFactor * ((gamepad1.left_stick_y + gamepad1.right_stick_y) + (1.15*gamepad1.left_stick_x) + gamepad1.right_stick_x);
            backLeftPower = driveSpeedFactor * ((gamepad1.left_stick_y + gamepad1.right_stick_y) - (1.15*gamepad1.left_stick_x) + gamepad1.right_stick_x);
            backRightPower = driveSpeedFactor * ((gamepad1.left_stick_y + gamepad1.right_stick_y) + (1.15*gamepad1.left_stick_x) - gamepad1.right_stick_x);
            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);

            // Arm motor power
            gPower = armPowerFactor * (gamepad1.right_trigger-gamepad1.left_trigger);

            if (gamepad1.right_trigger > 0 || gamepad1.left_trigger > 0) {
                if(g.getCurrentPosition() < 200 && gamepad1.left_trigger > 0) {
                    if(g.getCurrentPosition() < 200) {
                        g.setPower(-0.00012*gPower);
                    }
                    else {
                        g.setPower(-0.00008*gPower);
                    }
                    // g.setPower(-0.0002*g.getCurrentPosition()*gPower); (make continuous later) hi DJ DIAPER
                } else g.setPower(gPower);
            }
            else if (gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0 && g.getCurrentPosition() > 1000) {
                g.setPower(-0.155);
            } else if (gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0 && g.getCurrentPosition() < 1000)
                g.setPower(0.155);


//            if (gamepad1.dpad_left || gamepad1.dpad_right) {
//                double lowGoal = 1.24;
//                double deadzone = 0.002;
//                if (deeznuts.getVoltage() >= lowGoal) {
//                    g.setPower(-0.3);
//                } else if (deeznuts.getVoltage() < lowGoal) {
//                    g.setPower(0.5);
//                } else {
//                    g.setPower(-0.1);
//                }
//            }

//                double pLimit = 1.455;
//                if(deeznuts.getVoltage() > pLimit) {
//                    if(deeznuts.getVoltage() > 1.63) {
//                        //while (deeznuts.getVoltage() > pLimit) {
//                            g.setPower(-0.5);
//                       // }
//                    }
//                    else if(deeznuts.getVoltage() <=1.62) {
//                        //while (deeznuts.getVoltage() > pLimit) {
//                            g.setPower(-0.1);
//                        //}
//                    }
//                }
//                else if(deeznuts.getVoltage() < pLimit) {
//                    while (deeznuts.getVoltage() < pLimit) {
//                        g.setPower(0.5);
//                    }
//                }
//            }

            // if (gamepad1.dpad_down || gamepad1.dpad_up) {
            //     double pLimit = 1.25;
            //     if(deeznuts.getVoltage() > pLimit) {
            //         if(deeznuts.getVoltage() > 1.62) {
            //             while (deeznuts.getVoltage() > pLimit) {
            //                 g.setPower(-0.5);
            //             }
            //         }
            //         if(deeznuts.getVoltage() <=1.62) {
            //             while (deeznuts.getVoltage() > pLimit) {
            //                 g.setPower(0.01);
            //             }
            //         }
            //     }
            //     if(deeznuts.getVoltage() < pLimit) {
            //         while (deeznuts.getVoltage() < pLimit) {
            //             g.setPower(0.5);
            //         }
            //     }
            // }

//            if (gamepad1.dpad_up) {
//                g.setTargetPosition(750);
//                g.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//                g.setVelocity(1400);
//                sleep(100);
//            }
            switch (intakeState) {
                case 0:
                    thingPower = 0;
                    break;
                case 1:
                    thingPower = 1;
                    break;
                case 2:
                    thingPower = -1;
                    break;
            }
            if (gamepad1.b == true)
                intakeState = 0;
            else if (gamepad1.a == true)
                intakeState = 1;
            else if (gamepad1.x == true)
                intakeState = 2;

            // Control intake with X,A,B
            intakeControl(thing, thingPower);

            // Turn off encoder limit when trigger pressed
            if (gamepad1.right_trigger > 0 || gamepad1.left_trigger > 0)
                g.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            // Telemetry
            telemetry.addData("Arm position", g.getCurrentPosition());
            telemetry.addData("Arm Power", g.getPower());
            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
            telemetry.addData("Right Stick Y", gamepad1.right_stick_y);
            telemetry.update();
        }
    }
}