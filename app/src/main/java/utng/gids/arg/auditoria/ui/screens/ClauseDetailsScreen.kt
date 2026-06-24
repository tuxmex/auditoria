package utng.gids.arg.auditoria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.HelpOutline
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import utng.gids.arg.auditoria.data.entity.AuditQuestion
import utng.gids.arg.auditoria.data.entity.ChecklistItem
import utng.gids.arg.auditoria.data.entity.ComplianceStatus
import utng.gids.arg.auditoria.ui.MainViewModel
import utng.gids.arg.auditoria.ui.theme.AuditoriaTheme

import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClauseDetailsScreen(
    clauseNumber: String,
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    val clauses by viewModel.clauses.collectAsState()
    val clause = clauses.find { it.clauseNumber == clauseNumber }
    val questions by viewModel.getQuestionsByClause(clauseNumber).collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clause $clauseNumber Guide") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            clause?.let {
                item {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = it.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                }
            }

            item {
                Text(
                    text = "Compliance Evaluation",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            items(questions) { question ->
                val checklistItem by viewModel.getChecklistItem(question.id).collectAsState(null)
                QuestionChecklistCard(
                    question = question,
                    checklistItem = checklistItem,
                    onStatusChange = { status ->
                        val currentItem = checklistItem ?: ChecklistItem(questionId = question.id)
                        viewModel.updateChecklist(currentItem.copy(complianceStatus = status))
                    },
                    onFindingsChange = { findings ->
                        val currentItem = checklistItem ?: ChecklistItem(questionId = question.id)
                        viewModel.updateChecklist(currentItem.copy(findings = findings))
                    }
                )
            }
        }
    }
}

@Composable
fun QuestionChecklistCard(
    question: AuditQuestion,
    checklistItem: ChecklistItem?,
    onStatusChange: (ComplianceStatus) -> Unit,
    onFindingsChange: (String) -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.HelpOutline,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = question.questionText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            Spacer(Modifier.height(8.dp))
            
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    imageVector = Icons.Rounded.Description,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(18.dp).padding(top = 2.dp)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "Evidence: ${question.suggestedEvidence}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

            Text("Compliance Status:", style = MaterialTheme.typography.labelMedium)
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatusButton(
                    status = ComplianceStatus.COMPLIANT,
                    isSelected = checklistItem?.complianceStatus == ComplianceStatus.COMPLIANT,
                    onClick = { onStatusChange(ComplianceStatus.COMPLIANT) },
                    modifier = Modifier.weight(1f)
                )
                StatusButton(
                    status = ComplianceStatus.NON_CONFORMITY,
                    isSelected = checklistItem?.complianceStatus == ComplianceStatus.NON_CONFORMITY,
                    onClick = { onStatusChange(ComplianceStatus.NON_CONFORMITY) },
                    modifier = Modifier.weight(1f)
                )
                StatusButton(
                    status = ComplianceStatus.OBSERVATION,
                    isSelected = checklistItem?.complianceStatus == ComplianceStatus.OBSERVATION,
                    onClick = { onStatusChange(ComplianceStatus.OBSERVATION) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = checklistItem?.findings ?: "",
                onValueChange = onFindingsChange,
                label = { Text("Observations") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun StatusButton(
    status: ComplianceStatus,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = when (status) {
        ComplianceStatus.COMPLIANT -> MaterialTheme.colorScheme.primary
        ComplianceStatus.NON_CONFORMITY -> MaterialTheme.colorScheme.error
        ComplianceStatus.OBSERVATION -> MaterialTheme.colorScheme.tertiary
        else -> MaterialTheme.colorScheme.outline
    }

    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = { 
            Text(
                text = status.name.split("_")
                    .joinToString(" ") { 
                        it.lowercase().replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString() } 
                    },
                style = MaterialTheme.typography.labelSmall
            ) 
        },
        modifier = modifier,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = color,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun QuestionChecklistPreview() {
    AuditoriaTheme {
        Box(Modifier.padding(16.dp)) {
            QuestionChecklistCard(
                question = AuditQuestion(clauseNumber = "4.1", questionText = "Is the SWOT analysis up to date?", suggestedEvidence = "SWOT Document"),
                checklistItem = null,
                onStatusChange = {},
                onFindingsChange = {}
            )
        }
    }
}
