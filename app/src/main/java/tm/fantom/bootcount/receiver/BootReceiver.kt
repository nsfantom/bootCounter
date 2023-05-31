package tm.fantom.bootcount.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tm.fantom.bootcount.repo.RepositoryProvider
import tm.fantom.bootcount.showNotification

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action ||
            "android.intent.action.ACTION_BOOT_COMPLETED" == intent?.action) {
            CoroutineScope(Dispatchers.IO).launch {
                RepositoryProvider.myRepository.saveTimeStamp()
                context?.showNotification()
            }
        }
    }
}

