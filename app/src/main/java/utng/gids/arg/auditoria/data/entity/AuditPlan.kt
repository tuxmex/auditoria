package utng.gids.arg.auditoria.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "audit_plan")
@Serializable
data class AuditPlan(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val objectOfAudit: String,
    val leadAuditor: String,
    val auditTeam: String,
    val date: String,
    val time: String,
    val scope: String,
    val criteria: String,
    val auditType: String,
    val objectives: String,
    val methodology: String
)
