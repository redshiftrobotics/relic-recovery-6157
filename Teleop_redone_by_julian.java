package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by julian on 11/29/17.
 */
@TeleOp(name = "6157TeleOp3")

public class Teleop_redone_by_julian extends LinearOpMode {

    private DcMotor motor1 = null;
    private DcMotor motor2 = null;
    private DcMotor motor3 = null;
    private DcMotor motor4 = null;
    private Servo clawHand = null;
    private Servo clawArm = null;


    public void runOpMode() throws InterruptedException {

        //hardware map
        motor1 = hardwareMap.dcMotor.get("LeftMotor");
        motor2 = hardwareMap.dcMotor.get("RightMotor");
        motor3 = hardwareMap.dcMotor.get("Wheels");
        motor4 = hardwareMap.dcMotor.get("VertSlide");
        clawHand = hardwareMap.servo.get("ClawHand");
        clawArm = hardwareMap.servo.get("ClawArm");


        //wait for start
        waitForStart();

        //while running...
        while (opModeIsActive()) {

            //lifter
            while (gamepad1.left_trigger == 1) {
                motor4.setPower(1);
            }
            while (gamepad1.right_trigger == 1) {
                motor4.setPower(-1);
            }

            //movement
            motor1.setPower(gamepad1.left_stick_y);
            motor2.setPower(gamepad1.right_stick_y);


            //roller
            motor3.setPower(gamepad2.right_stick_y);


            //claw up
            if (gamepad2.x) {
                clawArm.setPosition(0.5);
            }
            //claw down
            if (gamepad2.y) {
                clawArm.setPosition(-0.5);
            }
            //claw close
            if (gamepad2.a) {
                clawHand.setPosition(-0.25);
            }
            //claw open
            if (gamepad2.b) {
                clawHand.setPosition(0.25);
            }
        }
    }
}

