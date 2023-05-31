package tm.fantom.bootcount

import android.app.Application
import tm.fantom.bootcount.repo.RepositoryProvider

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RepositoryProvider.initialize(applicationContext)

        NotificationAlarmProvider.setRepeatingAlarm(this)
    }
}
