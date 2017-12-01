package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "Auto Julian")

public class JulianTest extends LinearOpMode {

    ColorSensor colorSensor;
    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private DcMotor wheels = null;
    private Servo jewelKicker = null;
    BNO055IMU imu;


    @Override
    public void runOpMode() throws InterruptedException {

        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");


        leftMotor = hardwareMap.dcMotor.get("LeftMotor");
        rightMotor = hardwareMap.dcMotor.get("RightMotor");
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheels = hardwareMap.dcMotor.get("Wheels");
        jewelKicker = hardwareMap.servo.get("Jewel_Kicker");
        imu = hardwareMap.get(BNO055IMU.class, "imu");


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        imu.initialize(parameters);

        waitForStart();
        //Move sensor downw
        while (opModeIsActive()) {


            //telemetry.addData("Red  ", colorSensor.red());
            /*
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Color is blue: ", colorSensor.blue() > colorSensor.red());
            telemetry.addData("Reading color...", colorSensor.blue());
             */

            jewelKicker.setPosition(0.5);
            telemetry.log().add("Reading Color..." + colorSensor.toString());



            if (gamepad1.y) {
                if (colorSensor.blue() > colorSensor.red()) {
                  telemetry.log().add("Reading Color...");

                    while (Math.abs(imu.getAngularOrientation().firstAngle + 5) > 0.4) {
                        leftMotor.setPower(imu.getAngularOrientation().firstAngle / 40);
                        rightMotor.setPower(-(imu.getAngularOrientation().firstAngle / 40));
                    }

                }

                while (Math.abs(imu.getAngularOrientation().firstAngle - 10) > 0.4) {
                    leftMotor.setPower(imu.getAngularOrientation().firstAngle / 40);
                    rightMotor.setPower(-(imu.getAngularOrientation().firstAngle / 40));
                }

                while (leftMotor.getCurrentPosition() <= 1031.8) {
                    leftMotor.setPower(1);
                    rightMotor.setPower(1);
                    idle();
                }

                wheels.setPower(1);

                while (Math.abs(imu.getAngularOrientation().firstAngle - 140) > 0.4) {
                    leftMotor.setPower(imu.getAngularOrientation().firstAngle / 40);
                    rightMotor.setPower(-(imu.getAngularOrientation().firstAngle / 40));
                }

                while (leftMotor.getCurrentPosition() <= 2000) {
                    leftMotor.setPower(1);
                    rightMotor.setPower(1);
                    idle();
                }

                wheels.setPower(-1);
                sleep(500);
                wheels.setPower(0);

                wheels.setPower(1);


                while (leftMotor.getCurrentPosition() <= 1031.8) {
                    leftMotor.setPower(1);
                    rightMotor.setPower(1);
                    idle();
                }

                sleep(3000);
                wheels.setPower(0);


                while (Math.abs(imu.getAngularOrientation().firstAngle + 140) > 0.4) {
                    leftMotor.setPower(imu.getAngularOrientation().firstAngle / 40);
                    rightMotor.setPower(-(imu.getAngularOrientation().firstAngle / 40));
                }

                while (leftMotor.getCurrentPosition() <= 2000) {
                    leftMotor.setPower(1);
                    rightMotor.setPower(1);
                    idle();
                }

                telemetry.update();
            }
        }
    }
}