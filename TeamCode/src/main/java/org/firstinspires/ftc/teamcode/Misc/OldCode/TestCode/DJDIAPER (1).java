// package org.firstinspires.ftc.teamcode.TestCode;

// import org.firstinspires.ftc.robotcore.external.Telemetry;
// import com.qualcomm.robotcore.hardware.Servo;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.hardware.DcMotor;

// @TeleOp

// public class DJDIAPER {
//     private Servo intake;
//     private DcMotor frontLeft;
//     private DcMotor backLeft;
//     private DcMotor frontRight;
//     private DcMotor backRight;
//     private DcMotor arm;
    
//     // todo: write your code here

//     public void runOpMode() {
//         frontLeft = hardwareMap.get(DcMotor.class, "motor1");
//         backLeft = hardwareMap.get(DcMotor.class, "motor2");
//         frontRight = hardwareMap.get(DcMotor.class, "motor3");
//         backRight = hardwareMap.get(DcMotor.class, "motor4");
//         intake = hardwareMap.get(Servo.class, "intake");
//         arm = hardwareMap.get(DcMotor.class, "arm");
        
//         frontRight.setDirection(DcMotor.Direction.REVERSE);
//         backRight.setDirection(DcMotor.Direction.REVERSE);
        
//         waitForStart();
//         while(opModeIsActive()) {
//             double frontLeftPower = (0.8)*(gamepad1.left_stick_y + gamepad1.right_stick_y) - (0.6)*(gamepad1.left_stick_x) - gamepad1.right_stick_x;
//             double frontRightPower = (0.8)*(gamepad1.left_stick_y + gamepad1.right_stick_y) + (0.6)*(gamepad1.left_stick_x) - gamepad1.right_stick_x;
//             double backLeftPower = (0.8)*(gamepad1.left_stick_y + gamepad1.right_stick_y) - (0.6)*(gamepad1.left_stick_x) + gamepad1.right_stick_x;
//             double backRightPower = (0.8)*(gamepad1.left_stick_y + gamepad1.right_stick_y) + (0.6)*(gamepad1.left_stick_x) + gamepad1.right_stick_x;
            
            
//         }
//     }
    
// }
