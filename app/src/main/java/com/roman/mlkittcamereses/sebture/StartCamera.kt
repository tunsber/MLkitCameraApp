package com.roman.mlkittcamereses.sebture

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.roman.mlkittcamereses.R
import com.roman.mlkittcamereses.scanActiviti.ScanActivity

class StartCamera: Fragment() {

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        { isGranted ->
            if (isGranted == true) {
                startScan()
            } else {

            }

        }
    )

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
            val permission = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            )
            if (permission == PackageManager.PERMISSION_GRANTED)
            {
                startScan()
            }   else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
    private fun startScan()
    {
        val entent = Intent(requireContext(), ScanActivity::class.java)
        startActivity(entent)
    }

}
