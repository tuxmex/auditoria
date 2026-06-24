package utng.gids.arg.auditoria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import utng.gids.arg.auditoria.data.entity.ChecklistItem
import utng.gids.arg.auditoria.data.entity.ComplianceStatus
import utng.gids.arg.auditoria.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChecklistScreen(viewModel: MainViewModel) {
    val allChecklistItems by viewModel.allChecklistItems.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compliance Checklist") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            if (allChecklistItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No evaluations yet. Start by checking clauses in the Guide.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = "Summary of Evaluations",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    items(allChecklistItems) { item ->
                        ChecklistSummaryItem(item)
                    }
                }
            }
        }
    }
}

@Composable
fun ChecklistSummaryItem(item: ChecklistItem) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatusIcon(item.complianceStatus)
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = "Question ID: ${item.questionId}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "Status: ${item.complianceStatus.name.replace("_", " ")}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                if (item.findings.isNotEmpty()) {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Observations: ${item.findings}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
fun StatusIcon(status: ComplianceStatus) {
    val color = when (status) {
        ComplianceStatus.COMPLIANT -> MaterialTheme.colorScheme.primary
        ComplianceStatus.NON_CONFORMITY -> MaterialTheme.colorScheme.error
        ComplianceStatus.OBSERVATION -> MaterialTheme.colorScheme.tertiary
        else -> MaterialTheme.colorScheme.outline
    }
    val icon = when (status) {
        ComplianceStatus.COMPLIANT -> Icons.Rounded.CheckCircle
        ComplianceStatus.NON_CONFORMITY -> Icons.Rounded.Cancel
        ComplianceStatus.OBSERVATION -> Icons.Rounded.Warning
        else -> Icons.Rounded.RadioButtonUnchecked
    }
    Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(32.dp))
}
