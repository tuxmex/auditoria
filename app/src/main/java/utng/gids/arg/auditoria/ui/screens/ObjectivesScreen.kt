package utng.gids.arg.auditoria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import utng.gids.arg.auditoria.ui.MainViewModel
import utng.gids.arg.auditoria.ui.theme.AuditoriaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectivesScreen(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    val auditPlan by viewModel.auditPlan.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Objectives") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                windowInsets = TopAppBarDefaults.windowInsets
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            auditPlan?.objectives?.split(",")?.forEach { objective ->
                ObjectiveItem(text = objective.trim())
            }
        }
    }
}

@Composable
fun ObjectiveItem(text: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ObjectivesPreview() {
    AuditoriaTheme {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ObjectiveItem(text = "Evaluar conformidad")
            ObjectiveItem(text = "Verificar eficacia")
        }
    }
}
