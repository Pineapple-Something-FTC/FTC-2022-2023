package org.firstinspires.ftc.teamcode.TestCode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp
public class INTAKE extends LinearOpMode{
    private DcMotor intake;
    @Override public void runOpMode() {
        intake = hardwareMap.get(DcMotor.class, "intakemotor");
        double intakePower;
        waitForStart();
        while (opModeIsActive()) {
            if(gamepad1.left_trigger > 0 && gamepad1.left_trigger < 1) {
                intake.setPower(gamepad1.left_trigger);
            } else if(gamepad1.right_trigger > 0 && gamepad1.right_trigger < 1) {
                intake.setPower(-gamepad1.right_trigger);
            } else if(gamepad1.right_trigger == 1) {
                intake.setPower(-1);
            } else if(gamepad1.left_trigger == 1) {
                intake.setPower(1);
            } else intake.setPower(0);
            
            telemetry.addData("Right Trigger: ", 1-gamepad1.right_trigger);
            telemetry.addData("Left Trigger: ", 1-gamepad1.left_trigger);
            telemetry.addData("Motor Power: ", intake.getPower());
            telemetry.update();
            
        }
    }     
}
