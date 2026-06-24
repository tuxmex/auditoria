package utng.gids.arg.auditoria.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ListAlt
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import utng.gids.arg.auditoria.data.database.AuditDatabase
import utng.gids.arg.auditoria.data.repository.AuditRepository
import utng.gids.arg.auditoria.navigation.Route
import utng.gids.arg.auditoria.ui.screens.*

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val database = remember { AuditDatabase.getDatabase(context) }
    val repository = remember { AuditRepository(database.auditDao()) }
    val viewModel: MainViewModel = viewModel { MainViewModel(repository) }

    val backStack = rememberNavBackStack(Route.Dashboard)
    val currentRoute = backStack.last()

    Scaffold(
        bottomBar = {
            if (currentRoute !is Route.Objectives && currentRoute !is Route.Methodology && currentRoute !is Route.ClauseDetails) {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Rounded.Dashboard, contentDescription = "Dashboard") },
                        label = { Text("Dashboard") },
                        selected = currentRoute is Route.Dashboard,
                        onClick = { 
                            if (currentRoute !is Route.Dashboard) {
                                backStack.clear()
                                backStack.add(Route.Dashboard) 
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.AutoMirrored.Rounded.ListAlt, contentDescription = "Guide") },
                        label = { Text("Guide") },
                        selected = currentRoute is Route.ClauseList || currentRoute is Route.ClauseDetails,
                        onClick = { 
                            if (currentRoute !is Route.ClauseList) {
                                backStack.clear()
                                backStack.add(Route.ClauseList) 
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Rounded.Schedule, contentDescription = "Chronogram") },
                        label = { Text("Chronogram") },
                        selected = currentRoute is Route.Chronogram,
                        onClick = { 
                            if (currentRoute !is Route.Chronogram) {
                                backStack.clear()
                                backStack.add(Route.Chronogram) 
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Rounded.Checklist, contentDescription = "Checklist") },
                        label = { Text("Checklist") },
                        selected = currentRoute is Route.Checklist,
                        onClick = { 
                            if (currentRoute !is Route.Checklist) {
                                backStack.clear()
                                backStack.add(Route.Checklist) 
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            onBack = { if (backStack.size > 1) backStack.removeAt(backStack.size - 1) }
        ) { route ->
            NavEntry(route) {
                when (route) {
                    is Route.Dashboard -> DashboardScreen(
                        viewModel = viewModel,
                        onNavigateToObjectives = { backStack.add(Route.Objectives) },
                        onNavigateToMethodology = { backStack.add(Route.Methodology) }
                    )
                    is Route.Objectives -> ObjectivesScreen(
                        viewModel = viewModel,
                        onBack = { backStack.removeAt(backStack.size - 1) }
                    )
                    is Route.Methodology -> MethodologyScreen(
                        viewModel = viewModel,
                        onBack = { backStack.removeAt(backStack.size - 1) }
                    )
                    is Route.ClauseList -> ClauseListScreen(
                        viewModel = viewModel,
                        onClauseClick = { backStack.add(Route.ClauseDetails(it)) }
                    )
                    is Route.ClauseDetails -> ClauseDetailsScreen(
                        clauseNumber = route.clauseNumber,
                        viewModel = viewModel,
                        onBack = { backStack.removeAt(backStack.size - 1) }
                    )
                    is Route.Chronogram -> ChronogramScreen()
                    is Route.Checklist -> ChecklistScreen(viewModel = viewModel)
                }
            }
        }
    }
}
