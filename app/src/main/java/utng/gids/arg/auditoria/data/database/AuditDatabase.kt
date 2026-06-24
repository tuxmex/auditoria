package utng.gids.arg.auditoria.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import utng.gids.arg.auditoria.data.dao.AuditDao
import utng.gids.arg.auditoria.data.entity.*

@Database(
    entities = [AuditPlan::class, IsoClause::class, AuditQuestion::class, ChecklistItem::class],
    version = 1,
    exportSchema = false
)
abstract class AuditDatabase : RoomDatabase() {
    abstract fun auditDao(): AuditDao

    companion object {
        @Volatile
        private var INSTANCE: AuditDatabase? = null

        fun getDatabase(context: Context): AuditDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AuditDatabase::class.java,
                    "audit_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
