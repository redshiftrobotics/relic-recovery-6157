package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.navigation.*;
/*
ROBOT DIAGRAM

                                        COLLECTOR WHEELS
     ` ...                      o/::/:::::::/::::::/::::::::::::hy
-:::::::.`                 .`/` h. ` `            .  `        -`yy    `.///////////////////-
/       /o////+syyyhhhhhhdds/++yy-   `                         `s++::+:hMMMMMMMMMMMMMMMMMMMM/
:  ``   /.                     .`y///////////////////++oosooooosN. `   ```.....-----::://NMM/
:   .   +`                  `. -`o.-------:-::-:::::---..````  sh` .`  `                `NMM/
/:-`` `/-                        .````  `      `               `-:::+.`     -/////++++++oMMM/
syyyhmms+/-.:/                  `       `                       `-:/+o..-` oNMMMMMMMMMMMMMMM/
`MNdddsyshhdMMNNNNNNdddd::+-      :y.   .                    `-:s+:+`-     yMMM++++++++++++/`
 MMMMMMMMMNMMM:.:+oyhmNMMMMNd/--::oNN     `             `` -:so+.-/. .     /MMM
 ssssssssssmMM:      ``./yyhhmhhh+:od:-:----------:--:://+:.`/++:o.`  ` `  .MMM
           yMM/ -..`    oMNNh/`.-/+hhyyso/:.`     .    .s+  /` -`h    /-:  .MMM
           yMMo -.-.`   :MMMNy.   `+/``.-/+osys-` .`    s.:--:s+:-    .- -`.MMM
           yMMs ` - s `  ..../s/-::-o`       ```   y   `o/-::od+      -.`-:/MMM
           yMMy`` : s         :o+:o/o              M    -/-:++.       ...+/+MMM
           yMMd-``: / `        -++/``-          `  M            `     +:.+:/MMMs
           yMMm/-//./                / `     .  `  d                  /:://`MMMy
           yMMN. :-:-                `  `     `.   m                 ```````NMMy
           yMMM`  `                                N                       `NMmy
           yMMM. ..``      ```     ``   `      `   o///////////-    .-     `NM+/
           yMMM: `.`` `    ```     ``   `      `     .              ``. : -`NMo`
           yMNM/.............------------:-----/:::::+::::::::::::::///////+NMmo
           sNyNmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmNmmmmmmmNNNmmNmNNmMMMh/-/.
           `ho://::-/--/::-:/-:::-.--:----+:oy:-:...-.....--:-::::o:o.:-:-/NMMy/+`.``
            ys-./`-`-./::`/.`:`:-:.-::`- `.+-o`.-         :`.--:/`::/.../::mMMd/::.
        .`  :y-`-.`-.-.:/./-./.+--``.-:.``-.-:----`.`.```-:/-./.-:/:-:--::-dMMh``
        `.   osmmdhhdyhdmdhdmdmddhddddmdmdmddddmmmmmmmmmmmmmmmNmmmmmmmmmmmmNMMy
            .yMNssssssssssssooyso++++yssss+sooo+s++os+o///:ss/--:/-:-:o--/+-mMs
             yMd         .``  /.:.  .:-..-.-../:.` -.`..:-.`+`  .:. -`.`. `-mMh.    `
            `yMm-- .  -` ` -` :  -   . ` `./-:.`..   `..  ` ``   ..-`    `.`mMy`  .
            `yMm``.`..`. `- ` `- .  `    `./sh+.`-       `` `.```-    `  `  mMy`.
             dMm:`    ` `.``/`  ``` .`.`  `hdods.`.-` `--```--.:````..`     dMy :``````````      `
             MMm .`...` `. .   `.``..  ``. `sh/sd/ `  .`-:```..-.`.``       mMh/hdddyyhyssssosyyyhs
             MMm         .`     [ANDROID PHONE]/do:ds:-....//-`` ` `.    `.` .dMdoooo+++/+///://:-:sM.
             MMm   .`  `        `     . `    `.hh:/ooooooossyso/:...  `... -dMy ````:`...````.``.+M.    <——— Relic Arm
             MMm `  .       `      `       `-/shmy   ```.....-/ohdhs/-`/ ` `dMhohhhhhhdddddhhdhyyhh:
             MMm           `    -  `.--:://oNMNdhyyyyhhhddhhhdddhhydMo`.-``-dMMmoo+/:oooooo++++/::.`
             MMN    `         `  `:dmhhyysooo+///::----..```       sM: .. ``hMNy
             dMN                 `-My     `[RELIC RECOVERY CLAW]   dM``     hMMy
             oMN       `          :Ms `        ```.```..-.-:::/++ooNd       hMMy
             oMN`      `    ``    /My++oossyyyyhhhdddddddhhyyssso++/.       hMMy
             +MMyyysssssssssooooo+sNNNmmddhyysso++//::::-------:.........```hMm:
     `    `  `sysssyyyyyyhhhhdhhhddddmmmmmmmmmmmmNNNNNMNNNNNNNNNNNNNNNNNNNNNNMh.........:+.
    `     `   `             /h   :++++//+/::---..--``.m+```..........-------:/ooosssssssmyy
  -h-```.-`  -`             :N`   mMMMMMMMMMMMMMMM`  `N+           `         `.` ```.`  smy-/shhhhh-
+shMm/.:.--:/.           `  .M.  `NMMMMMMMMMMMMMMM`  `N/                             `--`/yhs-+yMMM/
MNsso-.```                 `.N+``-MMMMMMMMMMMMMMMM`  `N/                                `  `/yhssdN/ <—————— RELIC RECOVERY SLIDER
No..... `.                  `NddddmmmmmNNNNNNNNNNNdhhhMo                                 -..`  :sN/-
Nyo++++++++++++++///////////oN`   `        ``````.....om+++++++++++/////////////////////////////hm/`
+++//---:/+++/+/+++++++++++++:          `   .         `+++++++++++++++/+++++/++++++++++++++++++++-``
                                 `      `` ``



 */


@Autonomous(name = "Auto Julian")

public class ZenithAuto extends LinearOpMode {

    private ColorSensor     colorSensor;
    private DcMotor         leftMotor = null;
    private DcMotor         rightMotor = null;
    private DcMotor         wheels = null;
    private Servo           jewelKicker = null;
    private DcMotor         vertSlide = null;
    private BNO055IMU       imu;

    private Orientation     lastAngles = new Orientation();


    private VuforiaLocalizer vuforia;

    private String          us = null;

    private ElapsedTime     runtime = new ElapsedTime();
    static final double     COUNTS_PER_MOTOR_REV    = 1000 ;
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    private int             jewelCheck = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        //vuforia initialization
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AbCeqNT/////AAAAGUEBdLwd8U5Ug76DSiVNR/CDU3wSCFh0S0fV1mIXd6mNZZ+oG8WboMWdXAuBsKh9YJw08lGvY1yTnBU683JGlyI621za8IDz6CaWW+VcwZgYFC2dCgiKTjYPVcIVn7uX03K1Ya6mjsNd5r8VEO4jD4W3jnqD7hiyzQcjR25HJPSax5H08gcovdMvgj5i3EgLS6LYv5xDCYZbkQ0iJ84p4UnOpnz61BIhiC7XX/zPan297U8URyHAC97sLBsG1IpYiloUEv8kh8usCS8a7S3uziDsiVVjc5ubtfZ5c7m/oD8iCrqIZIEPhllOz6ZqLKRwRdC/bPHID7J5xrkL5/jccrnVfHyOhcFjO+zdvvGPdqC+";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "VuForia initialized and Ready to GO...");
        telemetry.update();


        int spot = 0;
        int columnNum = 0;


        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");


        leftMotor = hardwareMap.dcMotor.get("LeftMotor");

        rightMotor = hardwareMap.dcMotor.get("RightMotor");
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheels = hardwareMap.dcMotor.get("JimBob");
        vertSlide = hardwareMap.dcMotor.get("Wheels");
        jewelKicker = hardwareMap.servo.get("Jewel_Kicker");
        imu = hardwareMap.get(BNO055IMU.class, "imu");


        //initialize imu
        BNO055IMU.Parameters imuParameters = new BNO055IMU.Parameters();
        imu.initialize(imuParameters);


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
            telemetry.log().add("> Our spot is 1");
        }
        if (gamepad1.b) {
            spot = 2;
            telemetry.log().add("> Our spot is 2");
        }
        if (gamepad1.x) {
            spot = 3;
            telemetry.log().add("> Our spot is 3");
        }
        if (gamepad1.y) {
            spot = 4;
            telemetry.log().add("> Our spot is 4");
        }


        telemetry.log().add("> Ready for Auto");
        telemetry.update();


        waitForStart();

        relicTrackables.activate();

        //vuforia switch case

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

        switch (vuMark) {
            case LEFT:
                columnNum = -1;
                break;
            case CENTER:
                columnNum = 0;
                break;
            case RIGHT:
                columnNum = 1;
                break;
            default:
                columnNum = 0;
                break;
        }




        while (opModeIsActive()) {
            kickJewel();

            drive(1000, 12,12);

            drive(1000, -12, 12);






        }
    }




    private void kickJewel() {
        jewelKicker.setPosition(0.5);

        if (us == "red" && jewelCheck == 0) {
            if (colorSensor.blue() > colorSensor.red()) {
                sleep(500);
                telemetry.log().add("We are Red. Seeing Blue. Hitting Blue.");
                sleep(500);
                //right turn
                drive(100,1,-1);
                telemetry.log().add("done rotating");
                sleep(500);
                jewelKicker.setPosition(0);
                //left turn
                drive(100,-1,1);
                jewelCheck = 1;

            } else if (colorSensor.blue() < colorSensor.red()) {
                sleep(500);
                telemetry.log().add("We are Red. Seeing Red. Hitting Blue.");
                sleep(500);
                //left turn
                drive(100,-1,1);
                sleep(500);
                jewelKicker.setPosition(0);
                //right turn
                drive(100,1,-1);
                jewelCheck = 1;
            } else
                telemetry.log().add("Did not fucking work");

        } else if (us == "blue" && jewelCheck == 0) {
            if (colorSensor.blue() > colorSensor.red()) {
                sleep(500);
                telemetry.log().add("> We are Blue. Seeing Blue. Hitting Red");
                sleep(500);
                //left turn
                drive(100,-1,1);
                sleep(500);
                jewelKicker.setPosition(0);
                //right turn
                drive(100,1,-1);
                jewelCheck = 1;
            } else if (colorSensor.blue() < colorSensor.red()) {
                sleep(500);
                telemetry.log().add("> We are Blue. Seeing Red. Hitting Red");
                sleep(500);
                //right turn
                drive(100,1,-1);
                sleep(500);
                jewelKicker.setPosition(0);
                //left turn
                drive(100,-1,1);
                jewelCheck = 1;
            } else {
                telemetry.log().add("> What the actual fuck");
            }

            jewelKicker.setPosition(0);


        }
    }

    public void drive(int timeOut, int motor1, int motor2){
        leftMotor.setPower(motor1);
        rightMotor.setPower(motor2);
        sleep(timeOut);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }



}
