package tm.fantom.bootcount

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import tm.fantom.bootcount.repo.RepositoryProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.showNotification()

        lifecycleScope.launch {
            RepositoryProvider.myRepository.readLastData().collect { pair ->
                findViewById<TextView>(R.id.textViewCount)?.apply {
                    text = when (pair.first) {
                        0 -> getString(R.string.no_boot)
                        else -> getString(R.string.text_main, pair.first, pair.second)
                    }
                }
            }
        }
    }
}
