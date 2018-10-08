package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;

@Autonomous
public class GoldPipelineTest extends LinearOpMode {
    private GoldPipeline vision;
    @Override
    public void runOpMode() {
        vision = new GoldPipeline();
        vision.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        vision.enable();

        waitForStart();


        while(opModeIsActive()) {

            // vision.process();
        }
    }
}
