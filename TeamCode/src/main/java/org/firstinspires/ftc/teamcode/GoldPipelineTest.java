package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;
import org.opencv.core.MatOfKeyPoint;

@Autonomous(name = "Gold Pipeline Test", group = "Testing")
public class GoldPipelineTest extends LinearOpMode {

    private GoldPipeline vision;
    private MatOfKeyPoint blobs;

    @Override
    public void runOpMode() {

        telemetry.addLine("Creating pipeline instance...");
        telemetry.update();
        sleep(800);

        vision = new GoldPipeline();

        telemetry.addLine("GoldPipeline instance created.");
        telemetry.update();
        sleep(800);

        vision.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        telemetry.addLine("Vision initialized.");
        telemetry.update();
        sleep(800);

        vision.enable();
        telemetry.addLine("Vision enabled.");
        telemetry.addLine("Press play to start.");
        telemetry.update();
        sleep(2500);


        waitForStart();


        while(opModeIsActive()) {
            blobs = vision.findBlobsOutput();
            telemetry.addData("Blobs", blobs != null ? blobs.toList() : "None detected");
            telemetry.update();
        }

        vision.disable();
    }
}
