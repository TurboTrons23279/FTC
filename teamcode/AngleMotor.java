package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class  AngleMotor {
    private PIDController PIDFLinear;

    public static double pSlide = 0.0013, i = 0, d = 0;

    private DcMotorEx angleMotor;

    public static double targetSlide = 0;

    public double power;

    public AngleMotor(HardwareMap hardwareMap) {
        PIDFLinear = new PIDController(pSlide, i, d);
        angleMotor = hardwareMap.get(DcMotorEx.class, "angleMotor");
        angleMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        angleMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        angleMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setAngle(String position) {
        PIDFLinear.setPID(pSlide, i, d);
        double armPosBase = angleMotor.getCurrentPosition();

        double targetPosition = 0;

        if (position == "pickup") {
            targetPosition = -255; //-270
        } else if (position == "drop") {
            targetPosition = -1000;  //-1000,-800,-700,-950,-1000 (works but ftc members skib)
        } else if (position == "anglerrest"){
            targetPosition = 0;
        } else if (position == "pickupground") {
            targetPosition = -210;
        }
       else if (position == "droplowbasket") {
            targetPosition = -800;
        }


        double pid = PIDFLinear.calculate(armPosBase, targetPosition);

        double power = pid;

        angleMotor.setPower(power);



    }
    public void switchOffPower(LinearOpMode opMode){
        pSlide = 0;
        PIDFLinear.setPID(pSlide, i, d);
        double pid = PIDFLinear.calculate(0, 0);
        double power = pid;

        angleMotor.setPower(power);


        angleMotor.setPower(power);
        angleMotor.setMotorDisable();
        angleMotor.setMotorDisable();
    }
}
