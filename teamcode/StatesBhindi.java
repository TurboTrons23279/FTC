package org.firstinspires.ftc.teamcode;


import org.firstinspires.ftc.teamcode.LinearSlide;
import org.firstinspires.ftc.teamcode.AngleMotor;
import org.firstinspires.ftc.teamcode.FiveTimeBreakingClaw;

public class StatesBhindi {

    public statePos states;
    public LinearSlide linearMotor;
    public AngleMotor angleMotor;
    public FiveTimeBreakingClaw clawFive;

    public String linear_Motor = "linearsliderest";
    public String angle_Motor = "anglerrest";
    public String claw_Five = "ClawFiveRest";



   public StatesBhindi(LinearSlide linearMotor, AngleMotor angleMotor, FiveTimeBreakingClaw ClawFive) {
        this.linearMotor = linearMotor;
        this.angleMotor = angleMotor;
        this.clawFive = ClawFive;
        states = statePos.INIT;
    }



    public void update(){
        switch (states){
            case INIT:
                linear_Motor = "linearsliderest";
                angle_Motor = "anglerrest";
                claw_Five = "ClawFiveRest";

                break;
            case PICKUP:
                angle_Motor = "pickup";
                break;

            case DROPLOW:
                angle_Motor = "droplowbasket";
                break;

            case EXTEND:
                linear_Motor = "extend";
                break;

            case DEEXTEND:
                linear_Motor = "down";
                break;

            case CLOSE:
                 claw_Five = "close";
                break;

            case OPEN:
               claw_Five = "open";
                break;

            case DROP:
                angle_Motor = "drop";
                break;

            case EXTENDBACK:
                linear_Motor = "linearback";
                break;

            case PICKUPGROUND:
                angle_Motor = "pickupground";
                break;
        }
        linearMotor.setLinear(linear_Motor);
        angleMotor.setAngle(angle_Motor);
        clawFive.set5thClaw(claw_Five);
    }

    public void setStatePos(statePos state){
        states = state;
    }

    public enum statePos {
        INIT,
        PICKUP,
        //DROPHIGH,
        EXTEND,
        DEEXTEND,
        CLOSE,
        OPEN,
        DROPLOW,
        DROP,
        EXTENDBACK,
        PICKUPGROUND



    }
}