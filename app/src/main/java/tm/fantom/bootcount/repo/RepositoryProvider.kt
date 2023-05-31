package tm.fantom.bootcount.repo

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

object RepositoryProvider {

    lateinit var myRepository: Repository

    fun initialize(context: Context) {
        myRepository = Repository(dataStore = context.dataStore)

    }
}

private val Context.dataStore by preferencesDataStore("app_preferences")