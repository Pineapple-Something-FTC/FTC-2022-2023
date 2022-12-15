package org.firstinspires.ftc.teamcode.TestCode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Mecanome_Weles_Khar extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "motor1");
        backLeft = hardwareMap.get(DcMotor.class, "motor2");
        frontRight = hardwareMap.get(DcMotor.class, "motor3");
        backRight = hardwareMap.get(DcMotor.class, "motor4");
        
        // frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        while (opModeIsActive()) {
          
            // telemetry.addData("grab", gamepad1.right_trigger * 100);
            // telemetry.update();
            
            double frontLeftPower = (0.8)*(gamepad1.left_stick_y + gamepad1.right_stick_y) - (0.6)*(gamepad1.left_stick_x) - gamepad1.right_stick_x;
            double frontRightPower = (0.8)*(gamepad1.left_stick_y + gamepad1.right_stick_y) + (0.6)*(gamepad1.left_stick_x) - gamepad1.right_stick_x;
            double backLeftPower = (0.8)*(gamepad1.left_stick_y + gamepad1.right_stick_y) - (0.6)*(gamepad1.left_stick_x) + gamepad1.right_stick_x;
            double backRightPower = (0.8)*(gamepad1.left_stick_y + gamepad1.right_stick_y) + (0.6)*(gamepad1.left_stick_x) + gamepad1.right_stick_x;
            /*
            if((frontRight.getPower()>0.8 && frontLeft.getPower()>0.8) || (frontRight.getPower()<-0.8 && frontLeft.getPower()<-0.8)) {
                break;
            }
            */
            // if(gamepad1.right_stick_y>0.75 || gamepad1.right_stick_y<-0.75 || gamepad1.left_stick_x>0.75 || gamepad1.left_stick_x<-0.75 || gamepad1.right_stick_x>0.75 || gamepad1.right_stick_x<-0.75) {
            //     break;
            // }
            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);
            
            sleep(50);
        }
        telemetry.addData("","SLOW DOWN! GET OFF MY LAWN!");
        telemetry.update();
        sleep(15000);
    }
}
