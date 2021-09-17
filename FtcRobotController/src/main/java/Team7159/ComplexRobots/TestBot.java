package Team7159.ComplexRobots;

import com.arcrobotics.ftclib.hardware.motors.*;

import com.qualcomm.robotcore.hardware.HardwareMap;

import Team7159.Enums.Direction;
import Team7159.Enums.Version;
import Team7159.Utils.MotorGroup;
import Team7159.Utils.RobotComp;
import Team7159.Utils.RobotMath;

public class BasicMecanum {

    public RobotComp Comp = new RobotComp();

    MotorGroup Left;
    MotorGroup Right;

    public Motor RFMotor;
    public Motor RBMotor;
    public Motor LFMotor;
    public Motor LBMotor;

    public void init(HardwareMap Map) {

        LFMotor = new Motor(Map, "LBDrive");
        LBMotor = new Motor(Map, "RBDrive");
        RFMotor = new Motor(Map, "LFDrive");
        RBMotor = new Motor(Map, "RFDrive");

        RFMotor.set(0.0);
        RBMotor.set(0.0);
        LFMotor.set(0.0);
        LBMotor.set(0.0);

        //TODO: Figure out which motors need to be reversed, etc. so that the robot actually goes forward lmao
        LFMotor.setInverted(false);
        RFMotor.setInverted(true);
        LBMotor.setInverted(true);
        RBMotor.setInverted(true);

        //for now, we do this (maybe change later-
        LFMotor.setRunMode(Motor.RunMode.RawPower);
        RFMotor.setRunMode(Motor.RunMode.RawPower);
        LBMotor.setRunMode(Motor.RunMode.RawPower);
        RBMotor.setRunMode(Motor.RunMode.RawPower);

        LFMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        RFMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        LBMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        RBMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        //:crab: william is gone :crab:
    }

    public void moveStraight(double power) {
        LFMotor.set(power);
        RFMotor.set(power);
        LBMotor.set(power);
        RBMotor.set(power);
    }

    public void stop() {
        LFMotor.set(0);
        RFMotor.set(0);
        LBMotor.set(0);
        RBMotor.set(0);
    }
//    NOTE: Deprecated
//    public void turn(Direction direction, int degrees) {
//        Right.resetEncoders();
//        Left.resetEncoders();
//        //TODO: Make sure wDistance is actually correct
//        int LeftDistance = Comp.computeTurningPos(direction, degrees, Direction.LEFT, 17.5, Version.TWO);
//        int RightDistance = Comp.computeTurningPos(direction, degrees, Direction.RIGHT, 17.5, Version.TWO);
//        Left.setTargetPosition(LeftDistance);
//        Right.setTargetPosition(RightDistance);
//        Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        moveStraight(0.5);
//        while (Left.isBusy() && Right.isBusy()) {
//        }
//        stop();
//        Left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        Right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//    }

//    NOTE: Deprecated
//    public void driveDir(Direction direction, double distance) {
//        Right.resetEncoders();
//        Left.resetEncoders();
//        Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        switch (direction) {
//            case FORWARDS:
//                int pos = -Comp.computePositionD(RobotMath.toMeters(distance), Version.TWO);
//                Right.setTargetPosition(pos);
//                Left.setTargetPosition(pos);
//                break;
//            case BACKWARDS:
//                pos = Comp.computePositionD(RobotMath.toMeters(distance), Version.TWO);
//                Right.setTargetPosition(pos);
//                Left.setTargetPosition(pos);
//                break;
//        }
//        moveStraight(0.5);
//        while (Right.isBusy() && Left.isBusy()) {
//        }
//        stop();
//        Right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        Left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//    }

//    NOTE: Deprecated
//    public void driveDirPower(Direction direction, double power, double time) throws InterruptedException{
//        switch (direction) {
//            case FORWARDS:
//                LFMotor.set(power);
//                RFMotor.set(power);
//                RBMotor.set(power);
//                LBMotor.set(power);
//                wait((int)time * 1000);
//                stop();
//                break;
//            case BACKWARDS:
//                LFMotor.set(-power);
//                RFMotor.set(-power);
//                RBMotor.set(-power);
//                LBMotor.set(-power);
//                wait((int)time * 1000);
//                stop();
//                break;
//        }
//        moveStraight(0.5);
//        while (Right.isBusy() && Left.isBusy()) {
//        }
//        stop();
//        Right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        Left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//    }

    public void strafe(Direction direction, double power, double time) throws InterruptedException{
        if(direction == Direction.LEFT){
            LFMotor.set(-power);
            RFMotor.set(power);
            LBMotor.set(power);
            RBMotor.set(-power);
            wait((int)time * 1000);
            stop();
        }else if(direction == Direction.RIGHT){
            LFMotor.set(power);
            RFMotor.set(-power);
            LBMotor.set(-power);
            RBMotor.set(power);
            wait((int)time * 1000);
            stop();
        }else{
            //Throw an exception
        }
    }
}