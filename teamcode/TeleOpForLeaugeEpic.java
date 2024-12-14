package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.AngleMotor;
import org.firstinspires.ftc.teamcode.LinearSlide;
import org.firstinspires.ftc.teamcode.DrivetrainBhindi;
import org.firstinspires.ftc.teamcode.StatesBhindi;
import org.firstinspires.ftc.teamcode.FiveTimeBreakingClaw;
//@Config\
@TeleOp(name = "TeleOp For League", group = "Robot")
public class TeleOpForLeaugeEpic extends LinearOpMode {
    public LinearSlide linearMotor;
    public AngleMotor angleMotor;
    public FiveTimeBreakingClaw clawFive;
    StatesBhindi states;
    DrivetrainBhindi drive;


    @Override
    public void runOpMode() throws InterruptedException {
        linearMotor = new LinearSlide(hardwareMap);
        angleMotor = new AngleMotor(hardwareMap);
        clawFive = new FiveTimeBreakingClaw(hardwareMap);
        states = new StatesBhindi(linearMotor,angleMotor,clawFive);

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        drive = new DrivetrainBhindi(hardwareMap, gamepad1);

        waitForStart();
        while (opModeIsActive()) {

            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);

            previousGamepad2.copy(currentGamepad2);
            currentGamepad2.copy(gamepad2);
            if (currentGamepad2.a && !previousGamepad2.a) {
                //pick up from submersible
                states.setStatePos(StatesBhindi.statePos.PICKUP);
            }

            if (currentGamepad2.right_bumper && !previousGamepad2.right_bumper) {
                //extend full
                states.setStatePos(StatesBhindi.statePos.EXTEND);
            }
            if (currentGamepad2.left_bumper && !previousGamepad2.left_bumper) {
                //extend half
                states.setStatePos(StatesBhindi.statePos.EXTENDBACK);
            }


            if (currentGamepad2.x && !previousGamepad2.x) {
                states.setStatePos(StatesBhindi.statePos.DEEXTEND);
            }
            if (currentGamepad2.b && !previousGamepad2.b) {
                states.setStatePos(StatesBhindi.statePos.INIT);
            }
            if (currentGamepad2.y && !previousGamepad2.y) {
                states.setStatePos(StatesBhindi.statePos.DROP);
            }
            if (currentGamepad1.right_bumper && !previousGamepad1.right_bumper) {
                states.setStatePos(StatesBhindi.statePos.CLOSE);
            }
            if (currentGamepad1.left_bumper && !previousGamepad1.left_bumper) {
                states.setStatePos(StatesBhindi.statePos.OPEN);
            }
            if (currentGamepad1.a && !previousGamepad1.a) {
                states.setStatePos(StatesBhindi.statePos.PICKUPGROUND);
            }
            if (currentGamepad1.y && !previousGamepad1.y) {
                states.setStatePos(StatesBhindi.statePos.DROPLOW);
            }

            states.update();
            drive.move();
        }


    }
}


