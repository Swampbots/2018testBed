package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;

@Autonomous(name = "Gold Contour Pipeline Test", group = "Testing")
public class GoldContourPipelineTest extends LinearOpMode {

    GoldContourPipeline vision;

    RoverHardware hardware = new RoverHardware();


    // HSV Threshold input variables
    private final double THRESHOLD_STEP = 1.0;

    private final double HSV_MAX = 255.0;
    private final double HSV_MIN = 0.0;

    private double[] hsvHue = new double[]{0.0, 255.0};
    private double[] hsvSat = new double[]{0.0, 255.0};
    private double[] hsvVal = new double[]{0.0, 255.0};


    // Cooldown variables
    private final double COOLDOWN = 0.075; // 75 milliseconds

    // DPAD UP
    private double dpUpSnapshot = 0.0;
    private double dpUpRuntimeDif = 0.0;
    private boolean dpUpReady = false;

    // DPAD DOWN
    private double dpDownSnapshot = 0.0;
    private double dpDownRuntimeDif = 0.0;
    private boolean dpDownReady = false;

    // DPAD LEFT
    private double dpLeftSnapshot = 0.0;
    private double dpLeftRuntimeDif = 0.0;
    private boolean dpLeftReady = false;

    // DPAD RIGHT
    private double dpRightSnapshot = 0.0;
    private double dpRightRuntimeDif = 0.0;
    private boolean dpRightReady = false;

    // Y
    private double ySnapshot = 0.0;
    private double yRuntimeDif = 0.0;
    private boolean yReady = false;

    // A
    private double aSnapshot = 0.0;
    private double aRuntimeDif = 0.0;
    private boolean aReady = false;

    // X
    private double xSnapshot = 0.0;
    private double xRuntimeDif = 0.0;
    private boolean xReady = false;

    // B
    private double bSnapshot = 0.0;
    private double bRuntimeDif = 0.0;
    private boolean bReady = false;

    // LEFT BUTTON
    private double lbSnapshot = 0.0;
    private double lbRuntimeDif = 0.0;
    private boolean lbReady = false;

    // LEFT TRIGGER
    private double ltSnapshot = 0.0;
    private double ltRuntimeDif = 0.0;
    private boolean ltReady = false;

    // RIGHT BUTTON
    private double rbSnapshot = 0.0;
    private double rbRuntimeDif = 0.0;
    private boolean rbReady = false;

    // RIGHT TRIGGER
    private double rtSnapshot = 0.0;
    private double rtRuntimeDif = 0.0;
    private boolean rtReady = false;


    // Variable for thresholding LT and RT inputs, e.g. if(gamepad1.left_trigger > TRIGGER_THRESHOLD)
    public final double TRIGGER_THRESHOLD = 0.7;


    public void runOpMode() {

        // OCV INITIALIZATION
        vision = new GoldContourPipeline();
        telemetry.addLine("GoldPipeline instance created.");
        telemetry.update();
        sleep(800);

        vision.init(hardwareMap.appContext, CameraViewDisplay.getInstance(), 1);
        telemetry.addLine("Vision initialized.");
        telemetry.update();
        sleep(800);

        vision.enable();
        telemetry.addLine("Vision enabled.");
        telemetry.addLine("Press play to start.");
        telemetry.update();
        sleep(2500);



        // HARDWARE INITIALIZATION
        hardware.init(hardwareMap);



        waitForStart();

        while(opModeIsActive()) {

            //--------------------------------------------------------------------------------------
            // START COOLDOWN LOGIC
            //--------------------------------------------------------------------------------------

            /*
             When a button is pressed, set the button's cooldown variable to the current runtime.
             While the difference between the cooldown variable and the runtime is below some constant: don't allow thresholds to change
              */

            // DPAD UP
            dpUpRuntimeDif = (getRuntime() - dpUpSnapshot);
            dpUpReady = dpUpRuntimeDif > COOLDOWN;

            // DPAD DOWN
            dpDownRuntimeDif = (getRuntime() - dpDownSnapshot);
            dpDownReady = dpDownRuntimeDif > COOLDOWN;

            // DPAD LEFT
            dpLeftRuntimeDif = (getRuntime() - dpLeftSnapshot);
            dpLeftReady = dpLeftRuntimeDif > COOLDOWN;

            // DPAD RIGHT
            dpRightRuntimeDif = (getRuntime() - dpRightSnapshot);
            dpRightReady = dpRightRuntimeDif > COOLDOWN;



            // Y
            yRuntimeDif = (getRuntime() - ySnapshot);
            yReady = yRuntimeDif > COOLDOWN;

            // A
            aRuntimeDif = (getRuntime() - aSnapshot);
            aReady = aRuntimeDif > COOLDOWN;

            // X
            xRuntimeDif = (getRuntime() - xSnapshot);
            xReady = xRuntimeDif > COOLDOWN;

            // B
            bRuntimeDif = (getRuntime() - bSnapshot);
            bReady = bRuntimeDif > COOLDOWN;



            // LEFT BUTTON
            lbRuntimeDif = (getRuntime() - lbSnapshot);
            lbReady = lbRuntimeDif > COOLDOWN;

            // LEFT TRIGGER
            ltRuntimeDif = (getRuntime() - ltSnapshot);
            ltReady = ltRuntimeDif > COOLDOWN;

            // RIGHT BUTTON
            rbRuntimeDif = (getRuntime() - rbSnapshot);
            rbReady = rbRuntimeDif > COOLDOWN;

            // RIGHT TRIGGER
            rtRuntimeDif = (getRuntime() - rtSnapshot);
            rtReady = rtRuntimeDif > COOLDOWN;

            //--------------------------------------------------------------------------------------
            // END COOLDOWN LOGIC
            //--------------------------------------------------------------------------------------




            //--------------------------------------------------------------------------------------
            // START HSV THRESHOLD CONTROLS
            //--------------------------------------------------------------------------------------

            /*
                CONTROLS: (increase, decrease)

                Hue min: gp1.up,    gp1.down
                Hue max: gp1.y,     gp1.a

                Sat min: gp1.right, gp1.left
                Sat max: gp1.b,     gp1.x

                Val min: gp1.lb,    gp1.lt
                Val max: gp1.rb,    gp1.rt
             */

            // Modify threshold variables if the buttons are pressed and thresholds are within outer limits 0 & 255

            // HUE MINIMUM
            if(gamepad1.dpad_down && dpDownReady) {
                if (hsvHue[0] > HSV_MIN)   hsvHue[0] -= THRESHOLD_STEP;
                else                        hsvHue[0] = HSV_MIN;
                dpDownSnapshot = getRuntime();
            }

            if(gamepad1.dpad_up && dpUpReady) {
                if(hsvHue[0] < hsvHue[1])  hsvHue[0] += THRESHOLD_STEP;
                else                        hsvHue[0] = hsvHue[1];
                dpUpSnapshot = getRuntime();
            }


            // HUE MAXIMUM
            if(gamepad1.y && yReady) {
                if (hsvHue[1] < HSV_MAX)   hsvHue[1] += THRESHOLD_STEP;
                else                        hsvHue[1] = HSV_MAX;
                ySnapshot = getRuntime();
            }

            if(gamepad1.a && aReady) {
                if(hsvHue[1] > hsvHue[0])  hsvHue[1] -= THRESHOLD_STEP;
                else                        hsvHue[1] = hsvHue[0];
                aSnapshot = getRuntime();
            }




            // SAT MINIMUM
            if(gamepad1.dpad_left && dpLeftReady) {
                if (hsvSat[0] > HSV_MIN)   hsvSat[0] -= THRESHOLD_STEP;
                else                        hsvSat[0] = HSV_MIN;
                dpLeftSnapshot = getRuntime();
            }

            if(gamepad1.dpad_right && dpRightReady) {
                if(hsvSat[0] < hsvSat[1])  hsvSat[0] += THRESHOLD_STEP;
                else                        hsvSat[0] = hsvSat[1];
                dpRightSnapshot = getRuntime();
            }


            // SAT MAXIMUM
            if(gamepad1.b && bReady) {
                if (hsvSat[1] < HSV_MAX)   hsvSat[1] += THRESHOLD_STEP;
                else                        hsvSat[1] = HSV_MAX;
                bSnapshot = getRuntime();
            }

            if(gamepad1.x && xReady) {
                if(hsvSat[1] > hsvSat[0])  hsvSat[1] -= THRESHOLD_STEP;
                else                        hsvSat[1] = hsvSat[0];
                xSnapshot = getRuntime();
            }




            // VAL MINIMUM
            if(gamepad1.left_trigger > TRIGGER_THRESHOLD && ltReady) {
                if (hsvVal[0] > HSV_MIN)   hsvVal[0] -= THRESHOLD_STEP;
                else                        hsvVal[0] = HSV_MIN;
                ltSnapshot = getRuntime();
            }

            if(gamepad1.left_bumper && lbReady) {
                if(hsvVal[0] < hsvVal[1])  hsvVal[0] += THRESHOLD_STEP;
                else                        hsvVal[0] = hsvVal[1];
                lbSnapshot = getRuntime();
            }



            // VAL MAXIMUM
            if(gamepad1.right_trigger > TRIGGER_THRESHOLD && rtReady) {
                if (hsvVal[1] > hsvVal[0])  hsvVal[1] -= THRESHOLD_STEP;
                else                        hsvVal[1] = hsvVal[0];
                rtSnapshot = getRuntime();
            }

            if(gamepad1.right_bumper && rbReady) {
                if(hsvVal[1] < HSV_MAX)     hsvVal[1] += THRESHOLD_STEP;
                else                        hsvVal[1] = HSV_MAX;
                rbSnapshot = getRuntime();
            }

            //--------------------------------------------------------------------------------------
            // END HSV THRESHOLD CONTROLS
            //--------------------------------------------------------------------------------------




            // SET HSV THRESHOLDS
            vision.setHsvHue(hsvHue);
            vision.setHsvSat(hsvSat);
            vision.setHsvVal(hsvVal);


            // Show contours on left_stick_button press
            vision.setShowCountours(gamepad1.left_stick_button);





            // Image dimensions
            int camWidth = vision.getCameraView().getWidth();
            int camHeight = vision.getCameraView().getHeight();



            // TELEMETRY
            telemetry.addData("Hue min", hsvHue[0]);
            telemetry.addData("Hue max", hsvHue[1]);
            telemetry.addLine();
            telemetry.addData("Sat min", hsvSat[0]);
            telemetry.addData("Sat max", hsvSat[1]);
            telemetry.addLine();
            telemetry.addData("Val min", hsvVal[0]);
            telemetry.addData("Val max", hsvVal[1]);
            telemetry.addLine();
            telemetry.addData("Camera Height", camHeight);
            telemetry.addData("Camera Width", camWidth);
            telemetry.addLine();
            telemetry.addData("Height / 3", camHeight / 3);
            telemetry.addData("Height * 2 / 3", camHeight * 2 / 3);
            telemetry.addLine();
            telemetry.addData("Width / 3", camWidth / 3);
            telemetry.addData("Width * 2/ 3", camWidth * 2 / 3);
            telemetry.update();
        }

        vision.disable();
    }
}
