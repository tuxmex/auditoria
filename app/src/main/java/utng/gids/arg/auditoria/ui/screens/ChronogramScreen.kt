package utng.gids.arg.auditoria.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ChronogramActivity(
    val time: String,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChronogramScreen() {
    val activities = listOf(
        ChronogramActivity("12:30 - 12:35", "Reunión de apertura"),
        ChronogramActivity("12:35 - 13:45", "Ejecución de la auditoría"),
        ChronogramActivity("13:45 - 13:55", "Análisis y preparación de hallazgos"),
        ChronogramActivity("13:55 - 14:00", "Reunión de cierre")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Audit Chronogram") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                windowInsets = TopAppBarDefaults.windowInsets
            )
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val isWideScreen = maxWidth > 600.dp
            
            if (isWideScreen) {
                Row(modifier = Modifier.fillMaxSize()) {
                    TimelineList(activities = activities, modifier = Modifier.weight(1f))
                    Spacer(Modifier.width(32.dp))
                    TimelineVisual(activities = activities, modifier = Modifier.weight(1f))
                }
            } else {
                TimelineList(activities = activities, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun TimelineList(activities: List<ChronogramActivity>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(activities) { index, activity ->
            TimelineItem(
                activity = activity,
                isFirst = index == 0,
                isLast = index == activities.lastIndex
            )
        }
    }
}

@Composable
fun TimelineItem(activity: ChronogramActivity, isFirst: Boolean, isLast: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.width(24.dp).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .weight(1f)
                    .background(if (isFirst) Color.Transparent else MaterialTheme.colorScheme.tertiary)
            )
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary)
            )
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .weight(1f)
                    .background(if (isLast) Color.Transparent else MaterialTheme.colorScheme.tertiary)
            )
        }
        Spacer(Modifier.width(16.dp))
        ElevatedCard(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = activity.time,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = activity.description,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun TimelineVisual(activities: List<ChronogramActivity>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxHeight().padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Total Duration: 1h 30min",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(Modifier.height(16.dp))
            activities.forEach { activity ->
                Text(
                    text = "• ${activity.description}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChronogramPreview() {
    MaterialTheme {
        TimelineList(
            activities = listOf(
                ChronogramActivity("12:30 - 12:35", "Reunión de apertura"),
                ChronogramActivity("12:35 - 13:45", "Ejecución de la auditoría")
            )
        )
    }
}
