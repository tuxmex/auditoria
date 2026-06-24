package utng.gids.arg.auditoria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Assignment
import androidx.compose.material.icons.automirrored.rounded.Rule
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import utng.gids.arg.auditoria.data.entity.AuditPlan
import utng.gids.arg.auditoria.ui.MainViewModel
import utng.gids.arg.auditoria.ui.theme.AuditoriaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: MainViewModel,
    onNavigateToObjectives: () -> Unit,
    onNavigateToMethodology: () -> Unit
) {
    val auditPlan by viewModel.auditPlan.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Audit Dashboard", style = MaterialTheme.typography.headlineMedium) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            auditPlan?.let { plan ->
                InfoCard(
                    title = "General Information",
                    icon = Icons.Rounded.Info,
                    content = {
                        InfoItem(label = "Object", value = plan.objectOfAudit, icon = Icons.Rounded.Business)
                        InfoItem(label = "Lead Auditor", value = plan.leadAuditor, icon = Icons.Rounded.Person)
                        InfoItem(label = "Date", value = plan.date, icon = Icons.Rounded.CalendarToday)
                        InfoItem(label = "Time", value = plan.time, icon = Icons.Rounded.Schedule)
                    }
                )

                InfoCard(
                    title = "Audit Details",
                    icon = Icons.AutoMirrored.Rounded.Assignment,
                    content = {
                        InfoItem(label = "Scope", value = plan.scope, icon = Icons.Rounded.TrackChanges)
                        InfoItem(label = "Criteria", value = plan.criteria, icon = Icons.AutoMirrored.Rounded.Rule)
                        InfoItem(label = "Type", value = plan.auditType, icon = Icons.Rounded.Category)
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = onNavigateToObjectives,
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Icon(Icons.Rounded.GpsFixed, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Objectives")
                    }
                    Button(
                        onClick = onNavigateToMethodology,
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Icon(Icons.Rounded.AccountTree, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Methodology")
                    }
                }
            } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun InfoCard(
    title: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            content()
        }
    }
}

@Composable
fun InfoItem(label: String, value: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp).padding(top = 2.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun DashboardPreview() {
    AuditoriaTheme {
        // Mock data for preview
        val mockPlan = AuditPlan(
            objectOfAudit = "Área Psicopedagógica (Campus central y Victoria)",
            leadAuditor = "Anastacio Rodríguez García",
            auditTeam = "Equipo de Auditoría Interna",
            date = "2025-10-25",
            time = "12:30 - 14:00",
            scope = "Verificar el grado de cumplimiento del SGOE y SGC conforme a la norma ISO 21001:2025.",
            criteria = "ISO 21001:2025, documentación interna, requisitos legales.",
            auditType = "Auditoría Interna (presencial)",
            objectives = "...",
            methodology = "..."
        )
        
        Scaffold { padding ->
            Column(Modifier.padding(padding).padding(16.dp)) {
                InfoCard(
                    title = "General Information",
                    icon = Icons.Rounded.Info,
                    content = {
                        InfoItem(label = "Object", value = mockPlan.objectOfAudit, icon = Icons.Rounded.Business)
                        InfoItem(label = "Lead Auditor", value = mockPlan.leadAuditor, icon = Icons.Rounded.Person)
                    }
                )
            }
        }
    }
}
