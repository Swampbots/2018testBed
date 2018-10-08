package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;

@Autonomous(name = "Gold Pipeline Test", group = "Testing")
public class GoldPipelineTest extends LinearOpMode {

    private GoldPipeline vision;

    @Override
    public void runOpMode() {

        // This telemetry shows up on the Driver Station
        telemetry.addLine("Creating pipeline instance...");
        telemetry.update();
        sleep(2500);

        // Seems to crash here
        vision = new GoldPipeline();

        // This telemetry never shows up
        telemetry.addLine("GoldPipeline instance created.");
        telemetry.update();
        sleep(2500);

        vision.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        telemetry.addLine("Vision initialized.");
        telemetry.update();
        sleep(2500);

        vision.enable();
        telemetry.addLine("Vision enabled.");
        telemetry.addLine("Press play to start.");
        telemetry.update();
        sleep(2500);


        waitForStart();


        while(opModeIsActive()) {

            // vision.process();
        }

        vision.disable();
    }
}
