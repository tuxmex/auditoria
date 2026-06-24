package utng.gids.arg.auditoria.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import utng.gids.arg.auditoria.data.entity.IsoClause
import utng.gids.arg.auditoria.ui.MainViewModel
import utng.gids.arg.auditoria.ui.theme.AuditoriaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClauseListScreen(
    viewModel: MainViewModel,
    onClauseClick: (String) -> Unit
) {
    val clauses by viewModel.clauses.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ISO 21001:2025 Guide") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                windowInsets = TopAppBarDefaults.windowInsets
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(clauses) { clause ->
                ClauseItem(clause = clause, onClick = { onClauseClick(clause.clauseNumber) })
            }
        }
    }
}

@Composable
fun ClauseItem(clause: IsoClause, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Clause ${clause.clauseNumber}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = clause.title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClauseListPreview() {
    AuditoriaTheme {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ClauseItem(
                clause = IsoClause("4.1", "Understanding the Organization", "Description..."),
                onClick = {}
            )
            ClauseItem(
                clause = IsoClause("4.2", "Needs of Interested Parties", "Description..."),
                onClick = {}
            )
        }
    }
}
