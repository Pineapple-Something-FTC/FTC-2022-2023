package org.firstinspires.ftc.teamcode.TestCode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MotorSpeen extends LinearOpMode {
    
    // todo: write your code here
    private DcMotor g;
    public void runOpMode() {
        g = hardwareMap.get(DcMotor.class, "g");
        waitForStart();
        while(opModeIsActive()) {
            g.setPower(1);
        }
    }
}
