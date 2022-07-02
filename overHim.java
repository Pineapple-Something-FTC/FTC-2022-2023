package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous

public class overHim extends LinearOpMode {
    
    private DcMotorEx driveL;
    private DcMotorEx driveR;

    //double driveTicksDeg = 336/(3.5*Math.PI);
    double driveTicksInches = 25/1; // 25 ticks per inch
    public void runOpMode() {
        //// INIT ////
        driveL = hardwareMap.get(DcMotorEx.class, "driveL");
        driveR = hardwareMap.get(DcMotorEx.class, "driveR");
        
	rightmotor.setDirection(DcMotor.Direction.REVERSE);

        resetEncoders();
        
        waitForStart();
        //// START ////
        move(24);

        while (opModeIsActive()) {
            // Prints ticks of motor on hub
            telemetry.addData("driveL", driveL.getCurrentPosition());
            telemetry.addData("driveR", driveR.getCurrentPosition());
            telemetry.update();
        }
    }
    public void move(int distanceToDrive) {
        // Sets Target
        driveL.setTargetPosition(Math.round((float)(distanceToDrive * driveTicksInches)));
        driveR.setTargetPosition(Math.round((float)(distanceToDrive * driveTicksInches)));

        // Sets Mode
        driveL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        double currentDistance = (distanceToDrive - driveL.getCurrentPosition() * driveTicksInches);
        double kP = 29.2;
        double kD = 6900;
        double pastDistance = currentDistance;
        
        while(currentDistance > 0) {        
            double p = kP * currentDistance;
            double d = kD * (currentDistance - pastDistance);

            driveL.setVelocity(p + d);
            driveR.setVelocity(p + d);
            pastDistance = currentDistance;
            currentDistance -= driveL.getCurrentPosition() * driveTicksInches;

            sleep(50);
        }
        driveL.setVelocity(0);
        driveR.setVelocity(0); 
    }
     public void resetEncoders() {
        // Resets Encoders
        driveL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
