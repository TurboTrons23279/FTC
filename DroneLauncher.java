
package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.hardware.Servo;


public class DroneLauncher  {
    //Servo motor parameters


    Servo droneLauncher;
    double launcherTargetPos;

    public DroneLauncher(HardwareMap hardwareMap) {
        droneLauncher = hardwareMap.servo.get("droneLauncher");
    }

    public void set(String position) {
        if (position == "launch") {
            droneLauncher.setPosition(0.5);
        }
        }
    }

