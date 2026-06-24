package utng.gids.arg.auditoria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import utng.gids.arg.auditoria.ui.MainScreen
import utng.gids.arg.auditoria.ui.theme.AuditoriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuditoriaTheme {
                MainScreen()
            }
        }
    }
}
