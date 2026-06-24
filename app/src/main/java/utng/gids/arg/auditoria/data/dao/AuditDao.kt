package utng.gids.arg.auditoria.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import utng.gids.arg.auditoria.data.entity.*

@Dao
interface AuditDao {
    @Query("SELECT * FROM audit_plan LIMIT 1")
    fun getAuditPlan(): Flow<AuditPlan?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuditPlan(plan: AuditPlan)

    @Query("SELECT * FROM iso_clause")
    fun getAllClauses(): Flow<List<IsoClause>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClauses(clauses: List<IsoClause>)

    @Query("SELECT * FROM audit_question WHERE clauseNumber = :clauseNumber")
    fun getQuestionsByClause(clauseNumber: String): Flow<List<AuditQuestion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<AuditQuestion>)

    @Query("SELECT * FROM checklist_item WHERE questionId = :questionId")
    fun getChecklistItem(questionId: Long): Flow<ChecklistItem?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChecklistItem(item: ChecklistItem)

    @Query("SELECT * FROM checklist_item")
    fun getAllChecklistItems(): Flow<List<ChecklistItem>>
}
