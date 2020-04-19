package com.example.letsmanagework

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters


// Worker class created to define the work which needs to be done.
class MyWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    // Primary function
    override fun doWork(): Result {
        displayNotification("Hey i am Vibhor Chinda.", "What is your name.")
        return Result.success()
    }

    private fun displayNotification(task: String, desc: String) {
        // Creating a notification manager
        val manager: NotificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Checking version
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // creating notification channel
            val channel = NotificationChannel("Vibhor Chinda", "Vibhor Chinda", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        // Creating Notification
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, "Vibhor Chinda")
            .setContentTitle(task)
            .setContentText(desc)
            .setSmallIcon(R.mipmap.ic_launcher)

        manager.notify(1, builder.build())
    }
}