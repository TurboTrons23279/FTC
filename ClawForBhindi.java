
package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.hardware.Servo;

public class ClawForBhindi {
    //    RevColorSensorV3 distance;
    Servo leftClaw;
    Servo rightClaw;
    double clawTargetPos;

    public ClawForBhindi(HardwareMap hardwareMap) {
        leftClaw = hardwareMap.servo.get("leftClaw");
        rightClaw = hardwareMap.servo.get("rightClaw");

    }

    public void set(String position) {
        if (position == "open") {
            leftClaw.setPosition(0.5);
            rightClaw.setPosition(-0.5);
        } else {
            leftClaw.setPosition(-0.5);
            rightClaw.setPosition(0.5);
        }
    }
}