    package org.firstinspires.ftc.teamcode;
    import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.hardware.Servo;
    import com.qualcomm.robotcore.util.ElapsedTime;
    import com.qualcomm.robotcore.hardware.DistanceSensor;

    import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

    @Autonomous(name = "Blue 2 Autonomous")
    public class Blue2DistanceSensor extends LinearOpMode {

        private DistanceSensor sensorCenter;
        private DistanceSensor sensorLeft;
        private DistanceSensor sensorRight;
        private ElapsedTime runtime = new ElapsedTime();
        DrivetrainAutonomous drive;
        StatesBhindi states;
        org.firstinspires.ftc.teamcode.ArmForBhindi arm;
        ClawForBhindi claw;
        private DcMotor frontLeft;
        private DcMotor frontRight;
        private DcMotor backLeft;
        private DcMotor backRight;

        static final double COUNTS_PER_MOTOR_REV = 537.6;
        static final double DRIVE_SPEED = 0.3;
        static final double TURN_SPEED = 0.2;

        Servo servo, leftClaw,rightClaw;

        @Override
        public void runOpMode() {


            sensorCenter = hardwareMap.get(DistanceSensor.class, "sensor_center");
            sensorLeft = hardwareMap.get(DistanceSensor.class, "sensor_left");
            sensorRight = hardwareMap.get(DistanceSensor.class, "sensor_right");

            drive = new DrivetrainAutonomous(hardwareMap);

            claw = new ClawForBhindi(hardwareMap);
            arm = new org.firstinspires.ftc.teamcode.ArmForBhindi(hardwareMap);

            states = new StatesBhindi(claw, arm);


            states.setStatePos(StatesBhindi.statePos.INIT);
            states.update();
            states.setStatePos(StatesBhindi.statePos.CLAW_CLOSE);
            states.update();

            waitForStart();

            encoderDrive(DRIVE_SPEED, 25, 25, 25, 25);
            int inchesToFront = (int) sensorCenter.getDistance(DistanceUnit.INCH);
            int inchesToLeft = (int) sensorLeft.getDistance(DistanceUnit.INCH);
            int inchesToRight = (int) sensorRight.getDistance(DistanceUnit.INCH);
            telemetry.addData("Front Sensor Distance", inchesToFront);
            telemetry.addData("Left Sensor Distance", inchesToLeft);
            telemetry.addData("Right Sensor Distance", inchesToRight);
            telemetry.update();
            //encoderDrive(DRIVE_SPEED, -35,35,35,-35);   Left
            //encoderDrive(DRIVE_SPEED, 35,-35,-35,35);   Right

            sleep(2000);
            if (inchesToFront < inchesToLeft && inchesToFront < inchesToRight) {
                telemetry.addData("Front Sensor Distance is shortest ", inchesToFront);
                encoderDrive(DRIVE_SPEED, 1, 1, 1, 1);
                encoderDrive(DRIVE_SPEED, -4,-4,-4,-4);
                encoderDrive(TURN_SPEED,-24,24,-24,24);
                encoderDrive(DRIVE_SPEED,75,75,75,75);
                encoderDrive(DRIVE_SPEED,3,-3,-3,3);
                encoderDrive(DRIVE_SPEED,4,4,4,4);
                pixelDeposit();
               // sleep(3000);
            } else if (inchesToLeft < inchesToRight && inchesToLeft < inchesToFront) {

                telemetry.addData("Left Sensor Distance is shortest ", inchesToLeft);
                encoderDrive(TURN_SPEED, -24,24,-24,24);
                encoderDrive(DRIVE_SPEED,1,1,1,1);
                encoderDrive(DRIVE_SPEED,-4,-4,-4,-4);
                encoderDrive(DRIVE_SPEED,-25,25,25,-25);
                encoderDrive(DRIVE_SPEED,-1,-1,-1,-1);
                encoderDrive(DRIVE_SPEED,60,60,60,60);
                encoderDrive(DRIVE_SPEED,24,-24,-24,24);
                encoderDrive(DRIVE_SPEED,13,13,13,13);
                encoderDrive(DRIVE_SPEED,-4,4,4,-4);
                encoderDrive(DRIVE_SPEED,3,3,3,3);
                encoderDrive(DRIVE_SPEED,-2,2,2,-2);
                encoderDrive(DRIVE_SPEED,5,5,5,5);
                encoderDrive(DRIVE_SPEED,-4,4,4,-4);
                pixelDeposit();
             //   sleep(3000);
            } else {
                telemetry.addData("Right Sensor Distance is shortest ", inchesToRight);

                encoderDrive(TURN_SPEED, 24, -24, 24, -24);
                encoderDrive(DRIVE_SPEED, 1, 1, 1, 1);
                encoderDrive(DRIVE_SPEED, -4, -4, -4, -4);
                encoderDrive(TURN_SPEED, -24,24,-24,24);
                encoderDrive(TURN_SPEED, -24,24,-24,24);
                encoderDrive(DRIVE_SPEED, 75, 75, 75, 75);
                encoderDrive(DRIVE_SPEED,4,-4,-4,4);
                encoderDrive(DRIVE_SPEED,1,1,1,1);
                encoderDrive(DRIVE_SPEED,2,-2,-2,2);
                pixelDeposit();
              //  sleep(3000);
            }
        }
        public void pixelDeposit(){
            runtime.reset();
            //please let us know if we are doing the right thing here.
            //The code works perfectly well upto this point.
            while (opModeIsActive() && runtime.seconds() <= 7) {
                states.setStatePos(StatesBhindi.statePos.DEPOSIT);
                states.update();
                while (runtime.seconds() > 3 && runtime.seconds() <=5 ) {
                    states.setStatePos(StatesBhindi.statePos.CLAW_OPEN);
                    states.update();
                }
                while (runtime.seconds() > 5 && runtime.seconds() <= 7) {

                    states.setStatePos(StatesBhindi.statePos.INIT);
                    states.update();
                }
            }
        }
        public void encoderDrive(double speed,
                                 double leftInches, double rightInches, double backleft, double backright) {


                {
                    drive.encoderDrive(speed,leftInches,rightInches,backleft,backright, telemetry,this);
                    drive.stopDriveTrain();
                }



            }
        }

