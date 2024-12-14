//This is the latest code for Red1 Autonomous with Pixel Deposit.
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Blue 2 Autonomous Sample 2024-2025")
public class Blue2AutoSampleDrop extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    DrivetrainAutonomous drive;
    //  Servo servo, leftClaw,rightClaw;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    static final double COUNTS_PER_MOTOR_REV = 537.6;
    static final double DRIVE_SPEED = 0.3;
    static final double TURN_SPEED = 0.2;

    @Override
    public void runOpMode() {

        drive = new DrivetrainAutonomous(hardwareMap);

        waitForStart();
        if (opModeIsActive()) {
            driveFunction(DRIVE_SPEED, 2, 2, 2, 2);
            driveFunction(DRIVE_SPEED,-15,15,15,-15);
            driveFunction(DRIVE_SPEED,48,48,48,48);
            driveFunction(DRIVE_SPEED,-7,7,7,-7);
            driveFunction(DRIVE_SPEED,-45,-45,-45,-45);
            driveFunction(DRIVE_SPEED,-13,13,13,-13);
            driveFunction(DRIVE_SPEED,3,3,3,3       );
            driveFunction(DRIVE_SPEED, 120,-120,-120,120);
            driveFunction(DRIVE_SPEED,-3,-3,-3,-3);

            telemetry.update();

            sleep(2000);

        }
    }
    public void driveFunction(double speed,
                              double leftInches, double rightInches, double backleft, double backright)
    {


        drive.encoderDrive(speed,leftInches,rightInches,backleft,backright, telemetry,this);
        drive.stopDriveTrain();

    }
}
