package com.roman.mlkittcamereses.Services

import android.annotation.SuppressLint
import android.util.Size
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import com.roman.mlkittcamereses.scanActiviti.LendmarkView
import java.lang.Integer.max
import kotlin.math.min

class ImageProcessor(
    private val view: LendmarkView
): ImageAnalysis.Analyzer {
    private val options = AccuratePoseDetectorOptions
        .Builder()
        .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
        .build()
    private val detector = PoseDetection.getClient(options)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {

        val frameImage = image.image
        if (frameImage != null) {
            val imageDetected = InputImage.fromMediaImage(frameImage, image.imageInfo.rotationDegrees)
            val detectTusk = detector.process(imageDetected)

            detectTusk
                .addOnSuccessListener {
                    it.getPoseLandmark(PoseLandmark.LEFT_ELBOW)

                    val size = Size(
                        min(image.width, image.height),
                        max(image.width, image.height)
                    )
                    view.setPose(it, size)

                    image.close()
                }

                .addOnFailureListener {



                    image.close()
                }
        }


    }

}