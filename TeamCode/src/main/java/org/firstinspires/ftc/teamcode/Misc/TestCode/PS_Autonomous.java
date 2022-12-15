package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous
public class PS_Autonomous extends LinearOpMode {
    private DcMotorEx frontLeft;
    private DcMotorEx backLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backRight;
    private DcMotorEx g;
    private CRServo thing;

    // 5204 motor ticks per rotation: 384.5 ticks
    // Mecanum wheel diameter: 9.6 cm
    // final double ticksPerCm = 384.5 / (9.6 * Math.PI)/*Wheel Circumference*/;
    // ~61.6 cm in one tile, ~1533.72 ticks per tile
    // final double tileLengthCm = 61.6 /*cm*/;
    // final int tileLengthTicks = (int) Math.round(tileLengthCm * ticksPerCm);
    // final int tiles = tileLengthTicks;
    final boolean left = true;
    final boolean right = false;
    final boolean forward = true;
    final boolean back = false;
    
    final int speed = 700;
    
    @Override public void runOpMode() {
        //// INIT
        frontLeft = hardwareMap.get(DcMotorEx.class, "motor1");
        backLeft = hardwareMap.get(DcMotorEx.class, "motor2");
        frontRight = hardwareMap.get(DcMotorEx.class, "motor3");
        backRight = hardwareMap.get(DcMotorEx.class, "motor4");
        g = hardwareMap.get(DcMotorEx.class, "g");
        thing = hardwareMap.get(CRServo.class, "thing");
        
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        
        resetEncoders();
        //// START
        waitForStart();
        move(1400, forward, speed);
        sleep(2000);
        move(1400, back, speed);
        sleep(2000);
        strafe(1500, left, speed);
        sleep(2000);
        
        // Route Plan:
            // Load cone in robot
            // Score in ground junction
                // Strafe left 0.5 tiles
                // strafe(700, right, speed);
                // sleep(1000);
                // Score in ground junction
                // scoreGround(speed);
                // sleep(2000);
            // Drive to parallel lines
                // Strafe right 0.5 tiles
                // strafe(900, right, speed);
                // sleep(1000);
                    // Should be at corner terminal
                // Drive forwards 1.5 tiles
                // move(2100, forward, speed);
                // sleep(3000);
                    // Should be at parallel lines
            // Score on closest small junction
                // Pick up cone
                // Drive backwards 0.5 tiles
                //move(69, back, speed);
                // Tank turn right 45 degrees
                //turn(45/*degrees*/, right, speed);
                // Score cone on small junction
            // Return to parallel lines
                // Tank turn left 45 degrees
                // Drive forwards 0.5 tiles
                    // Should be at parallel lines
            // Score on closest medium junction
                // Pick up cone
                // Strafe right 1 tile
                // Tank turn right 135 degrees
                // Score cone on medium junction
            // Return to parallel lines
                // Tank turn right 135 degrees
                // Drive forwards 0.5 tiles
            // Score on second closest medium junction
                // Drive backwards 0.5 tiles
                // Strafe left 1 tile
                // Tank turn left 135 degrees
                // Score cone on medium junction
            // Stop and park
        // move(1 * ticks, forward, 400);
        // turn(1 * tiles, right, 400);
        // strafe(1 * ticks, left, 400);
        
        // Stop motors from running to position
         frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
          backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Telemetry
        while (opModeIsActive()) {
            telemetry.addData("Arm position", g.getCurrentPosition());
            telemetry.update();
        }
    }
    public void resetEncoders() {
        // Resets Encoders
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void move(int ticks, boolean forwardOrBackward, int velocity) {
        resetEncoders();
        if (forwardOrBackward == true) {
        // Drive forwards if `forwardOrBackward` is true
            // Set target position
            frontLeft.setTargetPosition(-ticks);
            frontRight.setTargetPosition(-ticks);
            backLeft.setTargetPosition(-ticks);
            backRight.setTargetPosition(-ticks);
    
            // Set mode
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
            // Set velocity
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(velocity);
        } else if (forwardOrBackward == false) {
        // Drive backwards if `forwardOrBackward` is false
            // Set target position
            frontLeft.setTargetPosition(ticks);
            frontRight.setTargetPosition(ticks);
            backLeft.setTargetPosition(ticks);
            backRight.setTargetPosition(ticks);
    
            // Set mode
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
            // Set velocity
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(velocity);
        }

    }
    public void turn(int ticks, boolean leftOrRight, int velocity) {
        resetEncoders();
        if (leftOrRight == false) {
        // Tank turn left if `leftOrRight` is false
            // Set target position
            frontLeft.setTargetPosition(-ticks);
            frontRight.setTargetPosition(ticks);
            backLeft.setTargetPosition(-ticks);
            backRight.setTargetPosition(ticks);
    
            // Set mode
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
            // Set velocity
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(velocity);
        } else if (leftOrRight == true) {
        // Tank turn right if `leftOrRight` is true
            // Set target position
            frontLeft.setTargetPosition(ticks);
            frontRight.setTargetPosition(-ticks);
            backLeft.setTargetPosition(ticks);
            backRight.setTargetPosition(-ticks);
    
            // Set mode
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
            // Set velocity
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(velocity);
        }
    }
    public void strafe(int ticks, boolean leftOrRight, int velocity) {
        resetEncoders();
        if (leftOrRight == true) {
        // Strafe left if `leftOrRight` is true
            // Set target position
            frontLeft.setTargetPosition(ticks);
            frontRight.setTargetPosition(-ticks);
            backLeft.setTargetPosition(-ticks);
            backRight.setTargetPosition(ticks);
    
            // Set mode
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
            // Set velocity
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(velocity);
        } else if (leftOrRight == false) {
        // Strafe right if `leftOrRight` is false
            // Set target position
            frontLeft.setTargetPosition(-ticks);
            frontRight.setTargetPosition(ticks);
            backLeft.setTargetPosition(ticks);
            backRight.setTargetPosition(-ticks);
    
            // Set mode
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
            // Set velocity
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(velocity);
        }
    }
    public void scoreGround(int velocity) {
        resetEncoders();
        move(400, forward, velocity);
        sleep(1000);
        move(400, back, velocity);
    }
    public void scoreSmall(int velocity){
        g.setTargetPosition(-500);
        g.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        g.setVelocity(velocity);
    }
    public void scoreMedium(int velocity){
        g.setTargetPosition(-1000);
        g.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        g.setVelocity(velocity);
    }
}
