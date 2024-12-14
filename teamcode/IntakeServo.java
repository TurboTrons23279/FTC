//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//
//public class IntakeServo {
//    //    RevColorSensorV3 distance;
//    Servo intakeServo;
//    double clawTargetPos;
//
//    public IntakeServo(HardwareMap hardwareMap) {
//        intakeServo = hardwareMap.servo.get("intakeServo");
//
//    }
//
//    public void setIntake(String position) {
//        if (position == "close") {
//
//            intakeServo.setPosition(0);
//
//        } else if (position == "open") {
//            intakeServo.setPosition(0.1);
//        }
//    }
//}
