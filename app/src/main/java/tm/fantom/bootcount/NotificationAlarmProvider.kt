package tm.fantom.bootcount

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.core.content.ContextCompat.getSystemService
import tm.fantom.bootcount.receiver.NotificationBroadcastReceiver

object NotificationAlarmProvider {
    private const val INTERVAL_MINUTES = 15L * 60_000

    @SuppressLint("UnspecifiedImmutableFlag")
    fun setRepeatingAlarm(context: Context) {
        val alarmManager = getSystemService(context, AlarmManager::class.java)
        val intent = Intent(context, NotificationBroadcastReceiver::class.java)
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )
        } else {
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val intervalMillis = INTERVAL_MINUTES

        alarmManager?.cancel(pendingIntent)

        alarmManager?.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + intervalMillis,
            intervalMillis,
            pendingIntent
        )
    }
}
