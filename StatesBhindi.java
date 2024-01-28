
package org.firstinspires.ftc.teamcode;

        import org.firstinspires.ftc.teamcode.ArmForBhindi;
        import org.firstinspires.ftc.teamcode.ClawForBhindi;

public class StatesBhindi {

    public ClawForBhindi claw;
    public ArmForBhindi arm;
    public DroneLauncher droneLauncher;
    public HangMechanism hangMechanism;
    public statePos states;

    public String stage_arm = "rest";
    public String stage_claw = "close";
    public String stage_launcher = "close";



    public StatesBhindi(ClawForBhindi claw, ArmForBhindi arm, DroneLauncher droneLauncher, HangMechanism hangMechanism){
        this.claw = claw;
        this.arm = arm;
        this.droneLauncher = droneLauncher;
        this.hangMechanism = hangMechanism;
        states = statePos.INIT;
    }

    public StatesBhindi(ClawForBhindi claw, ArmForBhindi arm){
        this.claw = claw;
        this.arm = arm;
        states = statePos.INIT;
    }
    public void update(){
        switch (states){
            case INIT:
                stage_arm = "rest";
                stage_claw = "close";
                break;
            case DEPOSIT:
                stage_arm = "deposit";
                break;
            case INTAKE_AND_OPEN_CLAW:
                stage_arm = "intake";
                stage_claw = "open";
                break;
            case CLAW_OPEN:
                stage_claw = "open";
                break;
            case CLAW_CLOSE:
                stage_claw = "close";
                break;
            case LAUNCH_DRONE:
                stage_launcher = "launch";
                break;
            case HANGARM:
                stage_claw = "close";
                stage_arm = "hangarm";
                break;
        }

        claw.set(stage_claw);
        arm.setArm(stage_arm);
        arm.setArmExtend(stage_arm);
        try {
            droneLauncher.set(stage_launcher);
        }catch (Exception e){
        }
    }

    public void setStatePos(statePos state){
        states = state;
    }

    public enum statePos {
        INIT,
        DEPOSIT,
        INTAKE,
        CLAW_OPEN,
        CLAW_CLOSE,
        INTAKE_AND_OPEN_CLAW,
        LAUNCH_DRONE,
        HANGARM
    }
}