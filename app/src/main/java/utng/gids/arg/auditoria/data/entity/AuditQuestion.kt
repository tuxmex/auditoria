package utng.gids.arg.auditoria.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "audit_question")
@Serializable
data class AuditQuestion(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val clauseNumber: String,
    val questionText: String,
    val suggestedEvidence: String
)
