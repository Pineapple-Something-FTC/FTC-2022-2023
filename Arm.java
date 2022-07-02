package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="NewerFowlder", group="Linear Opmode")

public class Arm extends LinearOpMode {

    // Variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotorEx arm1;
    private DcMotorEx arm2;
    double currentPosition1Degrees = 0;
    double currentPosition2Degrees = 0;
    float arm1TicksDeg = 15.56f;
    float arm2TicksDeg = 28*80/360f;
    int grid0MM = 720;
    int grid1MM = 720;
    int arm1IntDeg = Math.round(arm1TicksDeg);
    int arm2IntDeg = Math.round(arm2TicksDeg);
    int gridSizeXMM = 1440;
    int gridSizeYMM = 960;
    int arm1LengthMM = 380;
    int arm2LengthMM = 380;
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        arm2  = hardwareMap.get(DcMotorEx.class, "arm0");
        arm1 = hardwareMap.get(DcMotorEx.class, "arm1");

        resetEncoders();
        // Code
        waitForStart();
      
        // Moves to zero position
        //moveToPoint(300,300,500,500);
        moveToPoint(0,1,500,500);
        sleep(2000);
        moveToPoint(200,200,500,500);
        sleep(2000);
        moveToPoint(1,1,500,500);
        // sleep(1000);

        while (opModeIsActive()) {
            // Updates Telemetry
            telemetry.addData("arm1", arm1.getCurrentPosition() / arm1TicksDeg);
            telemetry.addData("arm2", arm2.getCurrentPosition() / arm2TicksDeg);
            telemetry.update();
        }
    }
    public void move(int distanceArm1, int distanceArm2, int velocityArm1, int velocityArm2){
        //distanceArm1 *= -1;
        // Sets Target
        arm1.setTargetPosition(distanceArm1 * arm1IntDeg);
        arm2.setTargetPosition(distanceArm2 * arm2IntDeg);

        // Sets Mode
        arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Sets Speed
        arm1.setVelocity(velocityArm1);
        arm2.setVelocity(velocityArm2);

        // Adding Amount to currentPositionDegrees
        //currentPositionDegrees += distanceArm1 + distanceArm2;
       // currentPositionDegrees += distanceArm2;
    
        while (opModeIsActive() && arm1.isBusy() && arm2.isBusy()) {

            // Prints on Hub Position of Motor
            telemetry.addData("arm1", arm1.getCurrentPosition());
            telemetry.addData("arm2", arm2.getCurrentPosition());
            telemetry.update();
        }
    }

    public static double[] tR(double s1, double s2, double s3){
        // Gets Array
        double[] theta = {0, 0, 0};

        // Equation 1 & Turning Radians to Degrees
        theta[0] = Math.acos( ((s1 * s1) - (s2 * s2) - (s3 * s3)) / (-2 * s2 * s3) );
        theta[0] = theta[0] * 180 / Math.PI;

        // // Equation 2 & Turning Radians to Degrees
        theta[1] = Math.acos( ((s2 * s2) - (s1 * s1) - (s3 * s3)) / (-2 * s1 * s3) );
        theta[1] = theta[1] * 180 / Math.PI;

        // // Equation 3 & Turning Radians to Degrees
        theta[2] = Math.acos( (s3 * s3 - s2 * s2 - s1 * s1) / (-2 * s2 * s1) );
        theta[2] = theta[2] * 180 / Math.PI;
        // Returning the Theta
        return theta;
    }
    // Get X & Y
    public void moveToPoint(int x, int y, int velocity1, int velocity2) {
        double c1 = distanceFormula(x,y);
        double c2 = distanceFormula(x,y);
        double[] targetAngles1 = tR(x,y,c1);
        double[] targetAngles2 = tR(x,y,c2);

        currentPosition1Degrees = arm1.getCurrentPosition()/arm1TicksDeg;
        currentPosition2Degrees = 0;//arm2.getCurrentPosition()/arm1TicksDeg;
        
        if (currentPosition2Degrees >= targetAngles2[0]){
            move((int)(currentPosition1Degrees - targetAngles1[0]), (int)(currentPosition2Degrees - targetAngles2[1]),velocity1,velocity2);
        } else if (currentPosition2Degrees < targetAngles2[0]){
            move((int)(currentPosition1Degrees - targetAngles1[0]), -(int)(currentPosition2Degrees - targetAngles2[1]),velocity1,velocity2);
        }
        
        // if (x == 0 && y == 0) {
        //     move((int)(0 - currentPosition1Degrees),(int)(145 - currentPosition2Degrees),velocity1,velocity2);
        // }
        
    }
    // Uses X & Y for distance formula
    public static double distanceFormula(double a, double b) {
        return Math.sqrt((a*a) + (b*b));
    }
    // Get Circumference of a cCircle
    public static double piD(int radius) {
        return 2*3.141592653789*radius;
    }

    public void resetEncoders() {
        // Resets Encoders
        arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
