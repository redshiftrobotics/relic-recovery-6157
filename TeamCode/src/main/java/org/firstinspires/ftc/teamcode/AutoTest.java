package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;


/**
 * Created by Luca Sandoval on 10/18/2017.
 */
@Autonomous (name="6157Auto")

public class AutoTest extends LinearOpMode {

     ColorSensor colorSensor;



    @Override
    public void runOpMode() throws InterruptedException {

        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");
        //private DcMotor motor1 = null;
        //private DcMotor motor2 = null;
        //private DcMotor motor3 = null;


        /*motor1 = hardwareMap.dcMotor.get("LeftMotor");
        motor2 = hardwareMap.dcMotor.get("RightMotor");
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3 = hardwareMap.dcMotor.get("Wheels");*/

        waitForStart();
        //Move sensor downw
        while(opModeIsActive()) {



            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Color is blue: ", colorSensor.blue() > colorSensor.red());
            //Move based off result and bring sensor back up

            //motor1.setPower(-1);
            // motor2.setPower(1);

            // sleep(2500);

            // motor1.setPower(1);
            // motor2.setPower(-1);
            // motor3.setPower(1);

            // sleep(500);

            // motor1.setPower(-1);
            // motor2.setPower(1);
            // motor3.setPower(1);

            //  sleep(550);

//        motor1.setPower(1);
            //      motor2.setPower(-1);
            //    motor3.setPower(1);

            // sleep(300);

            //motor1.setPower(0);
            //motor2.setPower(0);
            //motor3.setPower(0);

            // Read the sensor



            telemetry.update();
        }
    }
}