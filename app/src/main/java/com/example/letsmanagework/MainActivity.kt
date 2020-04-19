package com.example.letsmanagework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creating a work request
        val request: OneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker :: class.java).build()

        work_button.setOnClickListener {

            // Getting the instance of Work manager to execute the work request
            WorkManager.getInstance(applicationContext).enqueue(request)
        }

        // Observing Work Info using Work Manager and Live data
        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(request.id).observe(this, Observer {
            work_info_text_view.text =  work_info_text_view.text.toString() + it.toString()
        })
    }
}
