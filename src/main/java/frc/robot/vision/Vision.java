/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.vision;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;


/**
 * Add your docs here.
 */
public class Vision {




    // Vision visionThread = new VisionThread(Robot.camTop, new GripPipeline(), pipeline -> {
    //     if (!pipeline.filterContoursOutput().isEmpty()) {
    //     Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
    //     synchronized (imgLock) {
    //     centerX = r.x + (r.width / 2);
    //     }
    //     }
    //     });
    //     visionThread.start();
}
