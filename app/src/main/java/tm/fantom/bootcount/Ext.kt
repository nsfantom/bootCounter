package tm.fantom.bootcount

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tm.fantom.bootcount.repo.RepositoryProvider


fun Context.showNotification() {
    CoroutineScope(Dispatchers.IO).launch {
        RepositoryProvider.myRepository.readLastData().collect { pair ->
            NotificationManagerProvider.showNotification(
                context = this@showNotification,
                title = "",
                message = when (pair.first) {
                    0 -> getString(R.string.no_boot)
                    1 -> getString(R.string.first_boot, pair.second)
                    else -> getString(R.string.last_boot, pair.second)
                }
            )
        }
    }
}
