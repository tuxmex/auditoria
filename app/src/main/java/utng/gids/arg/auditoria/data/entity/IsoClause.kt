package utng.gids.arg.auditoria.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "iso_clause")
@Serializable
data class IsoClause(
    @PrimaryKey val clauseNumber: String, // e.g., "4.1"
    val title: String,
    val description: String
)
