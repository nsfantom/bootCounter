package tm.fantom.bootcount.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.System.currentTimeMillis

class Repository(private val dataStore: DataStore<Preferences>) {

    companion object {
        val DATASTORE_KEY_COUNT = intPreferencesKey("count")
        val DATASTORE_KEY_UNIXTIME = longPreferencesKey("unixtime")
    }

    suspend fun saveTimeStamp() {
        dataStore.edit { preferences ->
            val count = preferences[DATASTORE_KEY_COUNT] ?: 0
            val lastBoot = preferences[DATASTORE_KEY_UNIXTIME] ?: 0L

            preferences[DATASTORE_KEY_COUNT] = count.inc()
            preferences[DATASTORE_KEY_UNIXTIME] = currentTimeMillis() - lastBoot
        }
    }

    fun readLastData(): Flow<Pair<Int, Long>> {
        return dataStore.data.map { preferences ->
            Pair(preferences[DATASTORE_KEY_COUNT] ?: 0, preferences[DATASTORE_KEY_UNIXTIME] ?: 0L)
        }
    }
}