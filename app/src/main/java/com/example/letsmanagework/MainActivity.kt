package com.example.letsmanagework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creating Input Data Object
        val data: Data = Data.Builder()
            .putString("Title", "This is the Input Title")
            .putString("Description", "This is the Input Description")
            .build()

        // Defining Constraints
        val constraint: Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        // Creating a work request
        val request: OneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker :: class.java)
            .setConstraints(constraint)
            .setInputData(data)
            .build()

        work_button.setOnClickListener {
            // Getting the instance of Work manager to execute the work request
            WorkManager.getInstance(applicationContext).enqueue(request)
        }

        // Observing Work Info using Work Manager and Live data
        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(request.id).observe(this, Observer {
            // Receiving Output Data
            if(it.state.isFinished) {
                work_info_text_view.text =  it.outputData.getString("Success")
            }
        })
    }
}
