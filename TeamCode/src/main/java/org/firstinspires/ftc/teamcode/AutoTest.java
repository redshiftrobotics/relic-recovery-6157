package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "Auto Julian")

public class AutoTest extends LinearOpMode {

    ColorSensor colorSensor;
    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private DcMotor wheels = null;
    private Servo jewelKicker = null;
    BNO055IMU imu;


    @Override
    public void runOpMode() throws InterruptedException {

        String us = null;
        int spot = 0;


        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");


        leftMotor = hardwareMap.dcMotor.get("LeftMotor");
        rightMotor = hardwareMap.dcMotor.get("RightMotor");
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheels = hardwareMap.dcMotor.get("Wheels");
        jewelKicker = hardwareMap.servo.get("Jewel_Kicker");
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        //initialize imu
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        imu.initialize(parameters);

        if (gamepad1.right_bumper) {
            us = "blue";
            telemetry.log().add("Our team is Blue");

        }
        if (gamepad1.left_bumper) {
            us = "red";
            telemetry.log().add("Our team is Red");
        }
        telemetry.update();

        if (gamepad1.a) {
            spot = 1;
            telemetry.log().add("Our spot is 1");
        }
        if (gamepad1.b) {
            spot = 2;
            telemetry.log().add("Our spot is 2");
        }
        if (gamepad1.x) {
            spot = 3;
            telemetry.log().add("Our spot is 3");
        }
        if (gamepad1.y) {
            spot = 4;
            telemetry.log().add("Our spot is 4");
        }

        waitForStart();

        while (opModeIsActive()) {


            jewelKicker.setPosition(0.5);
            telemetry.log().add("Reading Color..." + colorSensor.toString());


            if (spot == 2) {
                telemetry.log().add("Pressed A");

                if (us == "red") {
                    if (colorSensor.blue() > colorSensor.red()) {
                        telemetry.log().add("We are Red. Seeing Blue. Hitting Blue.");
                        rotate(+5);
                        rotate(-5);
                    } else {
                        telemetry.log().add("We are Red. Seeing Red. Hitting Blue.");
                        rotate(-5);
                        rotate(+5);
                    }

                } else {
                    if (colorSensor.blue() > colorSensor.red()) {
                        telemetry.log().add("We are Blue. Seeing Blue. Hitting Red");
                        rotate(-5);
                        rotate(+5);
                    } else {
                        telemetry.log().add("We are Blue. Seeing Red. Hitting Red");
                        rotate(+5);
                        rotate(-5);
                    }
                }
                telemetry.update();

                //raise the jewel kicker

                jewelKicker.setPosition(0);

                //look towards the glyf pit

                rotate(-10);

                //move 1 yard forward

                move(36);

                //push out

                wheels.setPower(-1);

                //rotate towards the block pile

                rotate(-140);

                //start sucking in

                wheels.setPower(1);

                //move 1.5 yards

                move(54);

                //stop sucking in

                wheels.setPower(0);

                //turn back around

                rotate(140);

                //move 1.5 yards

                move(54);

                //push out the block

                wheels.setPower(-1);
                sleep(500);
                wheels.setPower(0);

                //park in the glyf holder

                leftMotor.setPower(0);
                rightMotor.setPower(0);
                wheels.setPower(0);


                telemetry.update();
            }

            if (spot == 1) {
                telemetry.log().add("Pressed A");

                if (us == "red") {
                    if (colorSensor.blue() > colorSensor.red()) {
                        telemetry.log().add("We are Red. Seeing Blue. Hitting Blue.");
                        rotate(+5);
                        rotate(-5);
                    } else {
                        telemetry.log().add("We are Red. Seeing Red. Hitting Blue.");
                        rotate(-5);
                        rotate(+5);
                    }

                } else {
                    if (colorSensor.blue() > colorSensor.red()) {
                        telemetry.log().add("We are Blue. Seeing Blue. Hitting Red");
                        rotate(-5);
                        rotate(+5);
                    } else {
                        telemetry.log().add("We are Blue. Seeing Red. Hitting Red");
                        rotate(+5);
                        rotate(-5);
                    }
                }
                telemetry.update();

                //raise the jewel kicker

                jewelKicker.setPosition(0);

                //look towards the glyf pit

                rotate(-10);

                //move 1 yard forward

                move(36);

                //push out

                wheels.setPower(-1);

                //rotate towards the block pile

                rotate(-140);

                //start sucking in

                wheels.setPower(1);

                //move 1.5 yards

                move(54);

                //stop sucking in

                wheels.setPower(0);

                //turn back around

                rotate(140);

                //move 1.5 yards

                move(54);

                //push out the block

                wheels.setPower(-1);
                sleep(500);
                wheels.setPower(0);

                //park in the glyf holder

                leftMotor.setPower(0);
                rightMotor.setPower(0);
                wheels.setPower(0);


                telemetry.update();
            }
        }
    }

    private void rotate(int degrees) {
        while (Math.abs(imu.getAngularOrientation().firstAngle + degrees) > 0.4) {
            leftMotor.setPower(imu.getAngularOrientation().firstAngle / 40);
            rightMotor.setPower(-(imu.getAngularOrientation().firstAngle / 40));
        }
    }

    private void move(int inches) {
        int ticsToMove = inches * (int) (28.662420382165605);
        while (leftMotor.getCurrentPosition() <= ticsToMove) {
            leftMotor.setPower(1);
            rightMotor.setPower(1);
            idle();
        }
    }


}
