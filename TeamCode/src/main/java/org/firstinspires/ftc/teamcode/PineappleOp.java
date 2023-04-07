package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Utility.Arm;
import org.firstinspires.ftc.teamcode.Utility.Drive;
import org.firstinspires.ftc.teamcode.Utility.PineappleSomething;

@TeleOp
public class PineappleOp extends PineappleSomething {
    Drive drive = new Drive();
    Arm arm = new Arm();
    final double DRIVE_SPEED_FACTOR = 0.69;
    final double ARM_POWER_FACTOR = 0.75;

    @Override public void runOpMode() {

        mapHardware();

        //// START
        waitForStart();
        resetEncoders();
        drive.setModeRunEncoder();
        arm.setTargetPosition(0);
        arm.runUsingEncoder();
        arm.setPower(ARM_POWER_FACTOR);
        while (opModeIsActive()) {
            if(gamepad1.right_stick_button || gamepad2.right_stick_button) {
                arm.resetEncoders();
            }
            else {
                arm.runUsingEncoder();
            }
            // Control intake with X,A,B
            intakeControl(thing);

            setDriveMotorPower();

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
            else if(gamepad2.right_bumper || gamepad1.right_bumper) {
                wallHeight();
            }
            else if (gamepad1.y || gamepad2.y) {
                if(g.getTargetPosition()<g.getCurrentPosition()-20){
                    thing.setPower(0.4);
                }
                else thing.setPower(0.5);
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
            telemetry.addData("THING", thing.getPower());
            telemetry.addData("left stick button", gamepad2.left_stick_button);
            telemetry.addData("front right", frontRight.getCurrentPosition());
            telemetry.addData("front left", frontLeft.getCurrentPosition());
            telemetry.addData("back right", backRight.getCurrentPosition());
            telemetry.addData("back left", backLeft.getCurrentPosition());
        }
    }

    private void setDriveMotorPower() {
        // Set power of drive motors
        frontLeft.setPower(
                DRIVE_SPEED_FACTOR * (
                        (gamepad1.left_stick_y + gamepad1.right_stick_y) -
                                (1.15 * gamepad1.left_stick_x) - gamepad1.right_stick_x
                )
        );
        backLeft.setPower(
                DRIVE_SPEED_FACTOR * (
                    (gamepad1.left_stick_y + gamepad1.right_stick_y) -
                            (1.15 * gamepad1.left_stick_x) + gamepad1.right_stick_x
                )
        );
        frontRight.setPower(
                DRIVE_SPEED_FACTOR * (
                        (gamepad1.left_stick_y + gamepad1.right_stick_y) +
                                (1.15 * gamepad1.left_stick_x) + gamepad1.right_stick_x
                )
        );
        backRight.setPower(
                DRIVE_SPEED_FACTOR * (
                        (gamepad1.left_stick_y + gamepad1.right_stick_y) +
                                (1.15 * gamepad1.left_stick_x) - gamepad1.right_stick_x
                )
        );
    }

    //Sets the lift power based on the trigger value
    public void liftMotorPower() {
        // int lastPosition = g.getCurrentPosition();
        int MAX_HEIGHT = -2369;
        // int MIN_HEIGHT = -2;
        // Sets a max height, so the lift does not pull the string too much
        if(g.getCurrentPosition() <= MAX_HEIGHT && gamepad2.right_trigger > 0) {
            arm.setTargetPosition(MAX_HEIGHT);
            telemetry.addData("no", "jo");
        }
        //Updates the lift's target position using the triggers
        else if (!gamepad2.right_bumper){
            if(gamepad2.right_trigger > 0) {
                arm.setTargetPosition((g.getTargetPosition() - (int) (69 * (gamepad2.right_trigger))));
            }
            else if(gamepad2.left_trigger > 0) {
                arm.setTargetPosition((g.getTargetPosition() + (int) (69 * (gamepad2.left_trigger))));
            }
        }
        //Sets the lift motors' power proportionally to its current-target position
        int average = (g.getCurrentPosition() + h.getCurrentPosition() + j.getCurrentPosition())/3;
        arm.setPower(-0.0052 * (average - g.getTargetPosition()));
    }
    //Controls the state of the intake using a, b, x
    public void intakeControl(CRServo thing) {
        if (gamepad2.b || gamepad1.b)
            thing.setPower(0);
        else if (gamepad2.a || gamepad1.a) {
            if(g.getTargetPosition() < g.getCurrentPosition()-20){
                thing.setPower(0.4);
            }
            else thing.setPower(0.5);
        }
        else if (g.getTargetPosition() == -111) {
            thing.setPower(0.5);
        }
        else if (gamepad2.x || gamepad1.x)
            thing.setPower(-1);
    }
    //Sets lift motors' target position to junction height
    public void scoreGround() {
        arm.setTargetPosition(-401);
    }
    public void scoreLow() {
        arm.setTargetPosition(-969);
    }
    public void scoreMedium() {
        arm.setTargetPosition(-1739);
    }
    public void scoreHigh() {
        arm.setTargetPosition(-2315);
    }
    public void intakeHeight() {
        arm.setTargetPosition(-300);
    }
    public void scoreCone() {
        arm.setTargetPosition(-6);
    }
    public void wallHeight() {
        arm.setTargetPosition(-111);
    }
}