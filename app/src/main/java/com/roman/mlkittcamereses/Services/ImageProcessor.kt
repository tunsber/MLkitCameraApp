package com.roman.mlkittcamereses.Services

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class ImageProcessor: ImageAnalysis.Analyzer {

    override fun analyze(image: ImageProxy) {

        println("sdfghjkjhf")

        image.close()
    }

}