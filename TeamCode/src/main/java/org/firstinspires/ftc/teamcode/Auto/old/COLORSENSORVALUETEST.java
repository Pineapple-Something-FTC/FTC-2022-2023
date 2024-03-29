package org.firstinspires.ftc.teamcode.Auto;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * This is an example LinearOpMode that shows how to use a color sensor in a generic
 * way, regardless of which particular make or model of color sensor is used. The Op Mode
 * assumes that the color sensor is configured with a name of "sensor_color".
 * <p>
 * There will be some variation in the values measured depending on the specific sensor you are using.
 * <p>
 * You can increase the gain (a multiplier to make the sensor report higher values) by holding down
 * the A button on the gamepad, and decrease the gain by holding down the B button on the gamepad.
 * <p>
 * If the color sensor has a light which is controllable from software, you can use the X button on
 * the gamepad to toggle the light on and off. The REV sensors don't support this, but instead have
 * a physical switch on them to turn the light on and off, beginning with REV Color Sensor V2.
 * <p>
 * If the color sensor also supports short-range distance measurements (usually via an infrared
 * proximity sensor), the reported distance will be written to telemetry. As of September 2020,
 * the only color sensors that support this are the ones from REV Robotics. These infrared proximity
 * sensor measurements are only useful at very small distances, and are sensitive to ambient light
 * and surface reflectivity. You should use a different sensor if you need precise distance measurements.
 * <p>
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this Op Mode to the Driver Station OpMode list
 */
@Autonomous
public class COLORSENSORVALUETEST extends LinearOpMode {

    /**
     * The colorSensor field will contain a reference to our color sensor hardware object
     */
    NormalizedColorSensor colorL;
    NormalizedColorSensor colorR;
    CRServo thing;
    PineappleTag bobot = new PineappleTag();
    ElapsedTime timer = new ElapsedTime();
    ElapsedTime timerD = new ElapsedTime();
    /**
     * The relativeLayout field is used to aid in providing interesting visual feedback
     * in this sample application; you probably *don't* need this when you use a color sensor on your
     * robot. Note that you won't see anything change on the Driver Station, only on the Robot Controller.
     */
    View relativeLayout;

    /**
     * The runOpMode() method is the root of this Op Mode, as it is in all LinearOpModes.
     * Our implementation here, though is a bit unusual: we've decided to put all the actual work
     * in the runSample() method rather than directly in runOpMode() itself. The reason we do that is
     * that in this sample we're changing the background color of the robot controller screen as the
     * Op Mode runs, and we want to be able to *guarantee* that we restore it to something reasonable
     * and palatable when the Op Mode ends. The simplest way to do that is to use a try...finally
     * block around the main, core logic, and an easy way to make that all clear was to separate
     * the former from the latter in separate methods.
     */
    @Override
    public void runOpMode() {
        bobot.init(hardwareMap);
        // Get a reference to the RelativeLayout so we can later change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        try {
            runSample(); // actually execute the sample
        } finally {
            // On the way out, *guarantee* that the background is reasonable. It doesn't actually start off
            // as pure white, but it's too much work to dig out what actually was used, and this is good
            // enough to at least make the screen reasonable again.
            // Set the panel back to the default color
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.WHITE);
                }
            });
        }
    }

    protected void runSample() {
        // You can give the sensor a gain value, will be multiplied by the sensor's raw value before the
        // normalized color values are calculated. Color sensors (especially the REV Color Sensor V3)
        // can give very low values (depending on the lighting conditions), which only use a small part
        // of the 0-1 range that is available for the red, green, and blue values. In brighter conditions,
        // you should use a smaller gain than in dark conditions. If your gain is too high, all of the
        // colors will report at or near 1, and you won't be able to determine what color you are
        // actually looking at. For this reason, it's better to err on the side of a lower gain
        // (but always greater than  or equal to 1).
        float gain = 20;
        float kPL = 3000;
        float kPR = 3000;
        double kD = 110;
        double lastErrorL = 0;
        double lastErrorR = 0;
        double encoderCounts = 0;
        // Once per loop, we will update this hsvValues array. The first element (0) will contain the
        // hue, the second element (1) will contain the saturation, and the third element (2) will
        // contain the value. See http://web.archive.org/web/20190311170843/https://infohost.nmt.edu/tcc/help/pubs/colortheory/web/hsv.html
        // for an explanation of HSV color.
        final float[] hsvValues = new float[3];

        // xButtonPreviouslyPressed and xButtonCurrentlyPressed keep track of the previous and current
        // state of the X button on the gamepad
        boolean xButtonPreviouslyPressed = false;
        boolean xButtonCurrentlyPressed = false;

        // Get a reference to our sensor object. It's recommended to use NormalizedColorSensor over
        // ColorSensor, because NormalizedColorSensor consistently gives values between 0 and 1, while
        // the values you get from ColorSensor are dependent on the specific sensor you're using.
        colorL = hardwareMap.get(NormalizedColorSensor.class, "deez");
        colorR = hardwareMap.get(NormalizedColorSensor.class, "nuts");
        thing = hardwareMap.get(CRServo.class, "thing");
        // If possible, turn the light on in the beginning (it might already be on anyway,
        // we just make sure it is if we can).
        if (colorL instanceof SwitchableLight) {
            ((SwitchableLight) colorL).enableLight(true);
        }

        // Wait for the start button to be pressed.
        waitForStart();

        // Loop until we are asked to stop
        while (opModeIsActive()) {
            // Explain basic gain information via telemetry
            telemetry.addLine("Hold the A button on gamepad 1 to increase gain, or B to decrease it.\n");
            telemetry.addLine("Higher gain values mean that the sensor will report larger numbers for Red, Green, and Blue, and Value\n");

            // Update the gain value if either of the A or B gamepad buttons is being held
            if (gamepad1.a) {
                // Only increase the gain by a small amount, since this loop will occur multiple times per second.
                gain += 0.005;
            } else if (gamepad1.b && gain > 1) { // A gain of less than 1 will make the values smaller, which is not helpful.
                gain -= 0.005;
            }


            // Show the gain value via telemetry
            telemetry.addData("Gain", gain);

            // Tell the sensor our desired gain value (normally you would do this during initialization,
            // not during the loop)
            colorL.setGain(gain);
            colorR.setGain(gain);
            // Check the status of the X button on the gamepad
            xButtonCurrentlyPressed = gamepad1.x;

            // If the button state is different than what it was, then act
            if (xButtonCurrentlyPressed != xButtonPreviouslyPressed) {
                // If the button is (now) down, then toggle the light
                if (xButtonCurrentlyPressed) {
                    if (colorL instanceof SwitchableLight) {
                        SwitchableLight light = (SwitchableLight) colorL;
                        light.enableLight(!light.isLightOn());
                    }
                }
            }
            xButtonPreviouslyPressed = xButtonCurrentlyPressed;

            // Get the normalized colors from the sensor

            NormalizedRGBA colorsL = colorL.getNormalizedColors();
            NormalizedRGBA colorsR = colorR.getNormalizedColors();

            /* Use telemetry to display feedback on the driver station. We show the red, green, and blue
             * normalized values from the sensor (in the range of 0 to 1), as well as the equivalent
             * HSV (hue, saturation and value) values. See http://web.archive.org/web/20190311170843/https://infohost.nmt.edu/tcc/help/pubs/colortheory/web/hsv.html
             * for an explanation of HSV color. */

            // Update the hsvValues array by passing it to Color.colorToHSV()
            Color.colorToHSV(colorsL.toColor(), hsvValues);
            bobot.setModeNoEncoder();

            // run code (timer off), center robot on the line and read the values
            // corresponding to the color of the line, should
            // cycle between right and left sensors
            // change the values of redRight, redLeft, blueRight, and blueLeft IFFFFF needed
            while (opModeIsActive()) {
                colorsL = colorL.getNormalizedColors();
                colorsR = colorR.getNormalizedColors();

                telemetry.addLine()
                        .addData("Red RIGHT:", "%.3f", colorsR.red)
                        .addData("Green", "%.3f", colorsR.green)
                        .addData("Blue", "%.3f", colorsR.blue);
                telemetry.addLine()
                        .addData("Hue", "%.3f", hsvValues[0])
                        .addData("Saturation", "%.3f", hsvValues[1])
                        .addData("Value", "%.3f", hsvValues[2]);
                telemetry.addLine()
                        .addData("Red LEFT:", "%.3f", colorsL.red)
                        .addData("Green", "%.3f", colorsL.green)
                        .addData("Blue", "%.3f", colorsL.blue);
                telemetry.addLine()
                        .addData("Hue", "%.3f", hsvValues[0])
                        .addData("Saturation", "%.3f", hsvValues[1])
                        .addData("Value", "%.3f", hsvValues[2]);
                telemetry.update();

            }

            telemetry.addData("Alpha", "%.3f", colorsR.alpha);

            /* If this color sensor also has a distance sensor, display the measured distance.
             * Note that the reported distance is only useful at very close range, and is impacted by
             * ambient light and surface reflectivity. */
            if (colorL instanceof DistanceSensor) {
                telemetry.addData("Distance (cm)", "%.3f", ((DistanceSensor) colorL).getDistance(DistanceUnit.CM));
            }

            telemetry.update();

            // Change the Robot Controller's background color to match the color detected by the color sensor.
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(hsvValues));
                }
            });
        }
    }

    public void move(int ticks, boolean forwardOrBackward, int velocity, int sleep) {
        bobot.move(ticks, forwardOrBackward, velocity);
        sleep(sleep);
    }

    public void turn(int degrees, boolean leftOrRight, int velocity, int sleep) {
        bobot.turn(degrees, leftOrRight, velocity);
        sleep(sleep);
    }

    public void strafe(int ticks, boolean leftOrRight, int velocity, int sleep) {
        bobot.strafe(ticks, leftOrRight, velocity);
        sleep(sleep);
    }

    public void armThing(int ticks, int speed, int sleep) {
        bobot.armThing(ticks, speed);
        sleep(sleep);
    }

    public void intakeThing(int state, int sleep) {
        bobot.intakeThing(state);
        sleep(sleep);
    }

    public void diagonal(int ticks, boolean leftOrRight, int velocity, int sleep) {
        bobot.diagonal(ticks, leftOrRight, velocity);
        sleep(sleep);
    }
    // pls dont touch this
//        encoderCounts = 0.25*(bobot.frontLeft.getCurrentPosition() + bobot.frontRight.getCurrentPosition() + bobot.backLeft.getCurrentPosition() + bobot.backRight.getCurrentPosition());
//        colorsL = colorL.getNormalizedColors();
//        colorsR = colorR.getNormalizedColors();
//        double errorR = 0.155 - colorsR.blue;
//        double errorL = 0.252 - colorsL.blue;
//
//        double derivativeR = (errorR - lastErrorR) / timerD.seconds();
//        double derivativeL = (errorL - lastErrorL) / timerD.seconds();
//
//        lastErrorL = errorL;
//        lastErrorR = errorR;
//        timerD.reset();
//         bobot.frontLeft.setVelocity(-(420-((errorR)*kPR + derivativeR*kD)+((errorL)*kPL + derivativeL*kD)));
//         bobot.frontRight.setVelocity(-(420+((errorR)*kPR + derivativeR*kD)-((errorL)*kPL + derivativeL*kD)));

}
