package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by SwampbotsAdmin on 9/16/2018.
 */

public class MecanumHardware {

    HardwareMap hwMap;

    // Motors
    public DcMotor frontLeftDrive = null;
    public DcMotor frontRightDrive = null;
    public DcMotor rearLeftDrive = null;
    public DcMotor rearRightDrive = null;

    // Speed button variables
    public final double SLOW = 0.1;
    public final double NORMAL = 0.2;
    public final double FAST = 0.4;

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        frontLeftDrive = ahwMap.dcMotor.get("fl_motor");
        frontRightDrive = ahwMap.dcMotor.get("fr_motor");
        rearLeftDrive = ahwMap.dcMotor.get("rl_motor");
        rearRightDrive = ahwMap.dcMotor.get("rr_motor");

        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}