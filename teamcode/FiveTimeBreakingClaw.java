package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FiveTimeBreakingClaw {
    //    RevColorSensorV3 distance;
    Servo clawFive;
    double clawTargetPos;

    public FiveTimeBreakingClaw(HardwareMap hardwareMap) {
        clawFive = hardwareMap.servo.get("intakeServo");

    }

    public void set5thClaw(String position) {
        if (position == "close") {

            clawFive.setPosition(0);

        } else if (position == "open") {
            clawFive.setPosition(0.18);
        } else if (position == "ClawFiveRest") {
            clawFive.setPosition(0.18);
        }
    }
}
