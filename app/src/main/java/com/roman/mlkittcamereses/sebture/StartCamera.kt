package com.roman.mlkittcamereses.sebture

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.roman.mlkittcamereses.R

class StartCamera: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_scan, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ssilkaButton = view.findViewById<Button>(R.id.startButtonScan)
        ssilkaButton.setOnClickListener {
            val entent = Intent (MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(entent)
        }
    }
    }
