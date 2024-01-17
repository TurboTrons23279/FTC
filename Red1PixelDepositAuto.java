//This is the latest code for Red1 Autonomous with Pixel Deposit.
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "Red 1 Autonomous with Pixel Deposit")
public class Red1PixelDepositAuto extends LinearOpMode {

    private DistanceSensor sensorCenter;
    private DistanceSensor sensorLeft;
    private DistanceSensor sensorRight;
    private ElapsedTime runtime = new ElapsedTime();

    DrivetrainAutonomous drive;
    //  Servo servo, leftClaw,rightClaw;
    StatesBhindi states;
    org.firstinspires.ftc.teamcode.ArmForBhindi arm;
    ClawForBindi claw;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    static final double COUNTS_PER_MOTOR_REV = 537.6;
    // static final double DRIVE_GEAR_REDUCTION = 1.0;
    // static final double WHEEL_DIAMETER_INCHES = 3.77953;
    //static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
    //    (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.3;
    static final double TURN_SPEED = 0.2;

    @Override
    public void runOpMode() {
        sensorCenter = hardwareMap.get(DistanceSensor.class, "sensor_center");
        sensorLeft = hardwareMap.get(DistanceSensor.class, "sensor_left");
        sensorRight = hardwareMap.get(DistanceSensor.class, "sensor_right");

        drive = new DrivetrainAutonomous(hardwareMap);

        claw = new ClawForBindi(hardwareMap);
        arm = new org.firstinspires.ftc.teamcode.ArmForBhindi(hardwareMap);

        states = new StatesBhindi(claw, arm);

        claw.leftClaw.setPosition(-0.5);
        claw.rightClaw.setPosition(0.5);
        states.setStatePos(StatesBhindi.statePos.INIT);
        states.update();
        states.setStatePos(StatesBhindi.statePos.CLAW_CLOSE);
        states.update();

        waitForStart();
        if (opModeIsActive()) {
            driveFunction(DRIVE_SPEED, 25, 25, 25, 25);
            int inchesToFront = (int) sensorCenter.getDistance(DistanceUnit.INCH);
            int inchesToLeft = (int) sensorLeft.getDistance(DistanceUnit.INCH);
            int inchesToRight = (int) sensorRight.getDistance(DistanceUnit.INCH);

            telemetry.addData("Front Sensor Distance", inchesToFront);
            telemetry.addData("Left Sensor Distance", inchesToLeft);
            telemetry.addData("Right Sensor Distance", inchesToRight);
            telemetry.update();

            sleep(2000);

            if (inchesToRight < inchesToLeft && inchesToRight < inchesToFront) {
                driveFunction(TURN_SPEED, 24, -24, 24, -24); //right
                driveFunction(DRIVE_SPEED, 1, 1, 1, 1);
                driveFunction(DRIVE_SPEED, -2, -2, -2, -2);
                driveFunction(DRIVE_SPEED, 25, -25, -25, 25);
                driveFunction(DRIVE_SPEED, 34, 34, 34, 34);
                driveFunction(DRIVE_SPEED,-18,18,18,-18);
                pixelDeposit();
                driveFunction(DRIVE_SPEED,20,-20,-20,20);
            } else if (inchesToLeft < inchesToRight && inchesToLeft < inchesToFront) {
                driveFunction(TURN_SPEED,-24,24,-24,24);
                driveFunction(DRIVE_SPEED, 1,1,1,1);
                driveFunction(DRIVE_SPEED,-34,-34,-34,-34);
                driveFunction(DRIVE_SPEED,6,-6,-6,6);
                driveFunction(TURN_SPEED,-48,48,-48,48);
                pixelDeposit();
                driveFunction(DRIVE_SPEED,-14,14,14,-14);

            } else {//center
                driveFunction(DRIVE_SPEED, -1,-1,-1,-1);
                driveFunction(DRIVE_SPEED, 38,-38,-38,38);
                driveFunction(TURN_SPEED,24,-24,24,-24);
                pixelDeposit();
                driveFunction(DRIVE_SPEED,27,-27,-27,27);//strafing to the right
                driveFunction(DRIVE_SPEED,4,4,4,4);

            }

        }
    }

    public void pixelDeposit(){
        runtime.reset();
        //please let us know if we are doing the right thing here.
        //The code works perfectly well upto this point.
        while (opModeIsActive() && runtime.seconds() <= 7) {
            states.setStatePos(StatesBhindi.statePos.DEPOSIT);
            states.update();
            while (runtime.seconds() > 2 && runtime.seconds() <=4 ) {
                states.setStatePos(StatesBhindi.statePos.CLAW_OPEN);
                states.update();
            }
            while (runtime.seconds() > 4 && runtime.seconds() <= 7) {

                states.setStatePos(StatesBhindi.statePos.INIT);
                states.update();
            }

        }

    }
    public void driveFunction(double speed,
                              double leftInches, double rightInches, double backleft, double backright)
    {


        drive.encoderDrive(speed,leftInches,rightInches,backleft,backright, telemetry,this);
        drive.stopDriveTrain();

    }
}
