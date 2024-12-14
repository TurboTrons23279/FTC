package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class  LinearSlide {
    private PIDController PIDFLinear;

    public static double pSlide = 0.0013, i = 0, d = 0;

    private DcMotorEx linearMotor;

    public static double targetSlide = 0;

    public double power;

    public LinearSlide(HardwareMap hardwareMap) {
        PIDFLinear = new PIDController(pSlide, i, d);
        linearMotor = hardwareMap.get(DcMotorEx.class, "linearMotor");
        linearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        linearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearMotor.setDirection(DcMotor.Direction.REVERSE); // reverse makes the slider go to up position
        linearMotor.setPower(0);
    }

    public void setLinear(String position) {
        PIDFLinear.setPID(pSlide, i, d);
        double armPosBase = linearMotor.getCurrentPosition();

        double targetPosition = 0;

//        if (position == "pickup") {
//            targetPosition = 13000; //-925 }
//        else if (position == "dropoffhigh") {
//            targetPosition = 13000;}
         if (position =="extend"){
            targetPosition = 9900;//14000

        }
         //else if (position =="dropofflow"){
         //   targetPosition = 7500;
      //  }
        else if (position == "down") {
            targetPosition = 7400; //7500
        } else if (position == "linearsliderest")
            targetPosition = 0;
        else if (position == "linearback") {
            targetPosition = 0;
         }

        double pid = PIDFLinear.calculate(armPosBase, targetPosition);

        double power = pid;

        linearMotor.setPower(power);



    }
    public void switchOffPower(LinearOpMode opMode){
        pSlide = 0;
        PIDFLinear.setPID(pSlide, i, d);
        double pid = PIDFLinear.calculate(0, 0);
        double power = pid;

        linearMotor.setPower(power);
        linearMotor.setPower(power);
        linearMotor.setMotorDisable();
        linearMotor.setMotorDisable();
    }
}
