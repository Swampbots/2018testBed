package org.firstinspires.ftc.teamcode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import java.util.stream.Collectors;

import java.util.HashMap;

import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;

/**
 * GoldPipeline class.
 *
 * <p>An OpenCV pipeline generated by GRIP.
 *
 * @author GRIP
 */
public class GoldPipeline extends OpenCVPipeline {

    //Outputs
    private Mat rgbThresholdOutput = new Mat();
    private MatOfKeyPoint findBlobsOutput = new MatOfKeyPoint();

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    // This is called every camera frame.
    @Override
    public Mat processFrame(Mat rgba, Mat gray) {

        // Step RGB_Threshold0:
        Mat rgbThresholdInput = rgba;
        double[] rgbThresholdRed = {167.0, 255.0};
        double[] rgbThresholdGreen = {84.0, 255.0};
        double[] rgbThresholdBlue = {0.0, 130.0};
        rgbThreshold(rgbThresholdInput, rgbThresholdRed, rgbThresholdGreen, rgbThresholdBlue, rgbThresholdOutput);

        // Step Find_Blobs0:
        Mat findBlobsInput = rgbThresholdOutput;
        double findBlobsMinArea = 80000.0;
        double[] findBlobsCircularity = {0.0, 1.0};
        boolean findBlobsDarkBlobs = false;
        findBlobs(findBlobsInput, findBlobsMinArea, findBlobsCircularity, findBlobsDarkBlobs, findBlobsOutput);

        return rgba; // display the image seen by the camera
    }

    /**
     * This method is a generated getter for the output of a RGB_Threshold.
     * @return Mat output from RGB_Threshold.
     */
    public Mat rgbThresholdOutput() {
        return rgbThresholdOutput;
    }

    /**
     * This method is a generated getter for the output of a Find_Blobs.
     * @return MatOfKeyPoint output from Find_Blobs.
     */
    public MatOfKeyPoint findBlobsOutput() {
        return findBlobsOutput;
    }


    /**
     * Segment an image based on color ranges.
     * @param input The image on which to perform the RGB threshold.
     * @param red The min and max red.
     * @param green The min and max green.
     * @param blue The min and max blue.
     * @param out The image in which to store the output.
     */
    private void rgbThreshold(Mat input, double[] red, double[] green, double[] blue,
                              Mat out) {
        Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2RGB);
        Core.inRange(out, new Scalar(red[0], green[0], blue[0]),
                new Scalar(red[1], green[1], blue[1]), out);
    }

    /**
     * Detects groups of pixels in an image.
     * @param input The image on which to perform the find blobs.
     * @param minArea The minimum size of a blob that will be found
     * @param circularity The minimum and maximum circularity of blobs that will be found
     * @param darkBlobs The boolean that determines if light or dark blobs are found.
     * @param blobList The output where the MatOfKeyPoint is stored.
     */
    private void findBlobs(Mat input, double minArea, double[] circularity,
                           Boolean darkBlobs, MatOfKeyPoint blobList) {
        FeatureDetector blobDet = FeatureDetector.create(FeatureDetector.SIMPLEBLOB);
        try {
            File tempFile = File.createTempFile("config", ".xml");

            StringBuilder config = new StringBuilder();

            config.append("<?xml version=\"1.0\"?>\n");
            config.append("<opencv_storage>\n");
            config.append("<thresholdStep>10.</thresholdStep>\n");
            config.append("<minThreshold>50.</minThreshold>\n");
            config.append("<maxThreshold>220.</maxThreshold>\n");
            config.append("<minRepeatability>2</minRepeatability>\n");
            config.append("<minDistBetweenBlobs>10.</minDistBetweenBlobs>\n");
            config.append("<filterByColor>1</filterByColor>\n");
            config.append("<blobColor>");
            config.append((darkBlobs ? 0 : 255));
            config.append("</blobColor>\n");
            config.append("<filterByArea>1</filterByArea>\n");
            config.append("<minArea>");
            config.append(minArea);
            config.append("</minArea>\n");
            config.append("<maxArea>");
            config.append(Integer.MAX_VALUE);
            config.append("</maxArea>\n");
            config.append("<filterByCircularity>1</filterByCircularity>\n");
            config.append("<minCircularity>");
            config.append(circularity[0]);
            config.append("</minCircularity>\n");
            config.append("<maxCircularity>");
            config.append(circularity[1]);
            config.append("</maxCircularity>\n");
            config.append("<filterByInertia>1</filterByInertia>\n");
            config.append("<minInertiaRatio>0.1</minInertiaRatio>\n");
            config.append("<maxInertiaRatio>" + Integer.MAX_VALUE + "</maxInertiaRatio>\n");
            config.append("<filterByConvexity>1</filterByConvexity>\n");
            config.append("<minConvexity>0.95</minConvexity>\n");
            config.append("<maxConvexity>" + Integer.MAX_VALUE + "</maxConvexity>\n");
            config.append("</opencv_storage>\n");
            FileWriter writer;
            writer = new FileWriter(tempFile, false);
            writer.write(config.toString());
            writer.close();
            blobDet.read(tempFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        blobDet.detect(input, blobList);
    }




}

