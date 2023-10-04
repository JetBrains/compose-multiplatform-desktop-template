import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.window.singleWindowApplication

fun main() = application {
    val state = rememberWindowState(size = DpSize.Unspecified)

    Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "Adaptive Sizing Flicker Bug",
        resizable = false,
    ) {
        var width by remember { mutableStateOf(480.dp) }

        LaunchedEffect(width) {
            state.size = DpSize.Unspecified
        }

        Box(
            modifier = Modifier
                .background(Color.Red)
                .requiredWidth(width)
                .requiredHeight(width / 2)
        )

        MenuBar {
            Menu("View") {
                Item(text = "480x240", shortcut = KeyShortcut(Key.F1)) { width = 480.dp }
                Item(text = "800x400", shortcut = KeyShortcut(Key.F2)) { width = 800.dp }
                Item(text = "1024x512", shortcut = KeyShortcut(Key.F3)) { width = 1024.dp }
            }
        }
    }
}