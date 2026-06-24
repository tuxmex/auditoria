package utng.gids.arg.auditoria.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import utng.gids.arg.auditoria.data.entity.ChecklistItem
import utng.gids.arg.auditoria.data.repository.AuditRepository

class MainViewModel(private val repository: AuditRepository) : ViewModel() {

    val auditPlan = repository.getAuditPlan()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val clauses = repository.getAllClauses()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getQuestionsByClause(clauseNumber: String) = repository.getQuestionsByClause(clauseNumber)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getChecklistItem(questionId: Long) = repository.getChecklistItem(questionId)

    fun updateChecklist(item: ChecklistItem) {
        viewModelScope.launch {
            repository.insertChecklistItem(item)
        }
    }

    val allChecklistItems = repository.getAllChecklistItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            repository.seedDatabase()
        }
    }
}
