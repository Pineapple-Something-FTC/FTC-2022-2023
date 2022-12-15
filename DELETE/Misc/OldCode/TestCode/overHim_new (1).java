package org.firstinspires.ftc.teamcode.TestCode;

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

public class overHim_new extends LinearOpMode {
    
    private DcMotorEx aMotor;
    private DcMotorEx bMotor;
    private DcMotorEx cMotor;
    private DcMotorEx dMotor;

    //double driveTicksDeg = 336/(3.5*Math.PI);
    double driveTicksInches = 300/12; // 25 ticks per inch
     double driveTicksRotation = 1560;
    double driveTicksPerDegree = 35;
    public void runOpMode() {
        //// INIT ////
        aMotor = hardwareMap.get(DcMotorEx.class, "motor1");
        bMotor = hardwareMap.get(DcMotorEx.class, "motor2");
        cMotor = hardwareMap.get(DcMotorEx.class, "motor3");
        dMotor = hardwareMap.get(DcMotorEx.class, "motor4");

        
        bMotor.setDirection(DcMotor.Direction.REVERSE);
        cMotor.setDirection(DcMotor.Direction.REVERSE);

        resetEncoders();
        
        waitForStart();
        //// START ////
        move(84);
    
       
      

        // while (opModeIsActive()) {
        //     // Prints ticks of motor on hub
        //     telemetry.addData("driveL", currentDistance);
        //     telemetry.addData("driveR", currentDistance);
        //     telemetry.update();
        // }
    }
    public void move(int degrees) {
        // Sets Target
        aMotor.setTargetPosition(-Math.round((float)(degrees * driveTicksPerDegree)));
        bMotor.setTargetPosition(Math.round((float)(degrees * driveTicksPerDegree)));
        cMotor.setTargetPosition(-Math.round((float)(degrees * driveTicksPerDegree)));
        dMotor.setTargetPosition(Math.round((float)(degrees * driveTicksPerDegree)));

        // Sets Mode
        aMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         cMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        double currentDegrees = Math.abs(degrees - aMotor.getCurrentPosition()/driveTicksPerDegree);
    
        double kP = 6.9/2+0.69+0.42+0.69+0.42+100; //2
        double kD = 0.00; //1.5
        double pastDegrees = currentDegrees;
        
        while(currentDegrees>0.69) {
            // Sets Speed
            currentDegrees = Math.abs(degrees - aMotor.getCurrentPosition()/driveTicksPerDegree);
            double p = currentDegrees*kP;
            double d = Math.abs(kD * (currentDegrees - pastDegrees));
            
            aMotor.setVelocity(p+d);
            bMotor.setVelocity(p+d);
              cMotor.setVelocity(p+d);
            dMotor.setVelocity(p+d);
            pastDegrees = currentDegrees;
            //currentDegrees -= Math.abs(driveR.getCurrentPosition()/driveTicksPerDegree);
            sleep(50);
            telemetry.addData("Stuff: ", currentDegrees);
            telemetry.update();
            
        }
        telemetry.addData("Stuff: ", currentDegrees);
        telemetry.update();
        aMotor.setVelocity(0);
        bMotor.setVelocity(0);
        cMotor.setVelocity(0);
        dMotor.setVelocity(0);
        
        
        
    }
     public void resetEncoders() {
        // Resets Encoders 8j szdg
        bMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        aMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
