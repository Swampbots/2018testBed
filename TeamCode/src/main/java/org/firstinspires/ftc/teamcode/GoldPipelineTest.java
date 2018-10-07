package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;

@Autonomous
public class GoldPipelineTest extends LinearOpMode {
    private GoldPipeline vision;
    @Override
    public void runOpMode() {
        vision = new GoldPipeline();

        while(opModeIsActive()) {
            // vision.process();
        }
    }
}
