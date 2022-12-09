// package org.firstinspires.ftc.teamcode.TestCode;

// import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.hardware.DcMotorEx;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.robotcore.hardware.CRServo;

// @Autonomous
// public class Something extends LinearOpMode {
//     private DcMotorEx frontLeft;
//     private DcMotorEx backLeft;
//     private DcMotorEx frontRight;
//     private DcMotorEx backRight;
//     private DcMotor g;
//     private CRServo thing;
    
//     // 5204 motor ticks per rotation: 384.5 ticks
//     // Mecanum wheel diameter: 9.6 cm
//     double ticksPerCm = (9.6 * Math.PI)/*Wheel Circumference*/ / 384.5;
//     // ~61.6 cm in one tile, ~1533.72 ticks per tile
//     double tileLengthCm = 61.6 /*cm*/;
//     double tileLengthTicks = tileLengthCm * ticksPerCm;
    
//     @Override public void runOpMode() {
//         frontLeft = hardwareMap.get(DcMotorEx.class, "motor1");
//         backLeft = hardwareMap.get(DcMotorEx.class, "motor2");
//         frontRight = hardwareMap.get(DcMotorEx.class, "motor3");
//         backRight = hardwareMap.get(DcMotorEx.class, "motor4");

//         frontRight.setDirection(DcMotor.Direction.REVERSE);
//         backRight.setDirection(DcMotor.Direction.REVERSE);
        
//         resetEncoders();
        
//         waitForStart();
        
//         move(1 * tileLengthTicks, 300);
        
//         while (opModeIsActive()) {
//             telemetry.addData("sussy balls: ",gamepad1.right_trigger);
//             telemetry.update();
//             sleep(30);
//         }
//     }
//     public void resetEncoders() {
//         // Resets Encoders 8j szdg
//         frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//         backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//         frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//         backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//     }
//     public void move(int position, int velocity) {
//         // Set target position
//         frontLeft.setTargetPosition(position);
//         frontRight.setTargetPosition(position);
//         backLeft.setTargetPosition(position);
//         backRight.setTargetPosition(position);

//         // Set mode
//         frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//         frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//         backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//         backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
//         // Set velocity
//         frontLeft.setVelocity(velocity);
//         frontRight.setVelocity(velocity);
//         backLeft.setVelocity(velocity);
//         backRight.setVelocity(velocity);
//     }
// }
