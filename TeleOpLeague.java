


package org.firstinspires.ftc.teamcode;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.util.ElapsedTime;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.hardware.DcMotorEx;

@Disabled
@TeleOp(name= "TeleOp" , group = "Robot")
public class TeleOpLeague extends LinearOpMode {
    double x1 = 0; // left/right
    double y1 = 0; // front/back
    //  double turn;
    // double drive;
    // double left;
    // double right;
    double fortyFiveInRads = -Math.PI / 4;
    double cosine45 = Math.cos(fortyFiveInRads);
    double sine45 = Math.sin(fortyFiveInRads);
    double x2 = 0;
    double y2 = 0;

    boolean extendActive = false;

    public DcMotor leftFront = null;
    public DcMotor rightFront = null;
    public DcMotor leftBack = null;
    public DcMotor rightBack = null;

    private DcMotor extendArm = null;


    Servo leftClaw = null;
    Servo rightClaw = null;


    private ElapsedTime period = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {


        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftClaw = hardwareMap.servo.get("leftClaw");
        rightClaw = hardwareMap.servo.get("rightClaw");

        extendArm = hardwareMap.get(DcMotorEx.class, "extendArm");
        //    extendArm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        extendActive = true;

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);


        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extendArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Say", "Hello Beta");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {


            double spin = gamepad1.right_stick_x; // For controling the spin
//            if (Math.abs(spin) > 0.1) {
//                rightFront.setPower(-spin);
//                rightBack.setPower(-spin);
//
//                leftFront.setPower(spin);
//                leftBack.setPower(spin);
//
//            } else {
//                y1 = -gamepad1.left_stick_y;
//                x1 = gamepad1.left_stick_x;
//
//                y2 = y1 * cosine45 + x1 * sine45;
//                x2 = x1 * cosine45 - y1 * sine45;
//
//                // left = drive + turn;
//                //  right = drive - turn;
//
//                rightFront.setPower(y2);
//                leftFront.setPower(x2);
//                rightBack.setPower(x2);
//                leftBack.setPower(y2);
//            //}
//
//            telemetry.addData("y2 Power", y2);
//            telemetry.addData("x2 Power", x2);
//
//            telemetry.update();

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;


            leftFront.setPower(y + x + rx);
            leftBack.setPower(y - x + rx);
            rightFront.setPower(y - x - rx);
            rightBack.setPower(y + x - rx);

            if (gamepad2.x) {

                leftClaw.setPosition(-0.5);
                rightClaw.setPosition(0.5);
            } else {

                leftClaw.setPosition(0.5);
                rightClaw.setPosition(-0.5);
            }

            if(gamepad1.a){

                if (extendActive == true) {
                    extendArm.setTargetPosition(20);
                    extendArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    extendArm.setPower(0.15 );
                    sleep(1000);
                    extendArm.setPower(0);
                    extendActive = false;
                }
            }
         /*   else if  (gamepad1.y){

                extendArm.setTargetPosition(-10);
                extendArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                extendArm.setPower(-0.10);

            }
               if (gamepad2.left_stick_y > 0) {
                extendArm.setPower(0.1);
            }

            else if (gamepad2.left_stick_y < 0) {
                extendArm.setPower(-0.1);
            }

            else extendArm.setPower(0);
            telemetry.addData("arm_motor_position" ,extendArm.getCurrentPosition());
            telemetry.update();*/

        }

     /*   telemetry.addData("x1" , "%.2f", x1);
            telemetry.addData("y1" , "%.2f", y1);*/
        telemetry.addData("arm_motor_position", extendArm.getCurrentPosition());
        telemetry.update();


    }

}

