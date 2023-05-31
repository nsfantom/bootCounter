package tm.fantom.bootcount.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import tm.fantom.bootcount.showNotification

class NotificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.showNotification()
    }
}