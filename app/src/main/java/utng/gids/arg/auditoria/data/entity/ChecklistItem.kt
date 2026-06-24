package utng.gids.arg.auditoria.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "checklist_item")
@Serializable
data class ChecklistItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val questionId: Long,
    val complianceStatus: ComplianceStatus = ComplianceStatus.NOT_EVALUATED,
    val findings: String = "",
    val evidenceNote: String = ""
)

@Serializable
enum class ComplianceStatus {
    COMPLIANT,
    NON_CONFORMITY,
    OBSERVATION,
    NOT_APPLICABLE,
    NOT_EVALUATED
}
