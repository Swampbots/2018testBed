package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/**
 * Created by SwampbotsAdmin on 8/19/2018.
 */

@TeleOp(name = "Mecanum test")
public class MecanumTest extends OpMode {

    MecanumHardware hardware = new MecanumHardware();

    public void init() {
        hardware.init(hardwareMap);
    }

    public void loop() {

        double driverSpeedMod;

        if(gamepad1.left_bumper)        driverSpeedMod = hardware.FAST;
        else if(gamepad1.right_bumper)  driverSpeedMod = hardware.SLOW;
        else                            driverSpeedMod = hardware.NORMAL;

        // Wheel speeds
        double frontLeft;
        double frontRight;
        double rearLeft;
        double rearRight;

        // Do the math
        frontLeft = ((-gamepad1.left_stick_y) + gamepad1.left_stick_x + gamepad1.right_stick_x);
        frontRight = ((-gamepad1.left_stick_y) - gamepad1.left_stick_x - gamepad1.right_stick_x);
        rearLeft = ((-gamepad1.left_stick_y) + gamepad1.left_stick_x - gamepad1.right_stick_x);
        rearRight = ((-gamepad1.left_stick_y) - gamepad1.left_stick_x + gamepad1.right_stick_x);

        // Set the power
        hardware.frontLeftDrive.setPower(frontLeft * driverSpeedMod);
        hardware.frontRightDrive.setPower(frontRight * driverSpeedMod);
        hardware.rearLeftDrive.setPower(rearLeft * driverSpeedMod);
        hardware.rearRightDrive.setPower(rearRight * driverSpeedMod);

    }
}
