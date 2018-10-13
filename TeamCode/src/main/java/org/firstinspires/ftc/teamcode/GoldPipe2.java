package org.firstinspires.ftc.teamcode;

import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class GoldPipe2 extends OpenCVPipeline {

    

    // This is called every camera frame.
    @Override
    public Mat processFrame(Mat rgba, Mat gray) {


        return rgba; // display the image seen by the camera
    }
}
