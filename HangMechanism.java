package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class  HangMechanism {

    private DcMotor HangMechanismLeft, HangMechanismRight;

    public HangMechanism(HardwareMap hardwareMap) {

        HangMechanismLeft = hardwareMap.get(DcMotor.class, "LeftHang");
        HangMechanismRight = hardwareMap.get(DcMotor.class, "RightHang");

        HangMechanismLeft.setDirection(DcMotor.Direction.FORWARD);
        HangMechanismLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HangMechanismLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        HangMechanismRight.setDirection(DcMotor.Direction.FORWARD);
        HangMechanismRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HangMechanismRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void executeHang(LinearOpMode opMode) {

        if (opMode.opModeIsActive()) {
            HangMechanismLeft.setTargetPosition(-3000);
            HangMechanismRight.setTargetPosition(-3000);

            HangMechanismLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HangMechanismRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            HangMechanismLeft.setPower(Math.abs(0.9));
            HangMechanismRight.setPower(Math.abs(0.9));
            //driveFunction(DRIVE_SPEED,500);

            while (opMode.opModeIsActive() &&
                    HangMechanismLeft.isBusy() && HangMechanismRight.isBusy()){
                //&& frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {

                // Display it for the driver.
                //  telemetry.addData("Running to",  " %7d :%7d", newLeftTargetFront,  newRightTargetFront);
              //  telemetry.addData("Currently at",  " at %7d",
                   //     HangMechanismLeft.getCurrentPosition() );
             //   telemetry.update();


            }
        }

    }
}

