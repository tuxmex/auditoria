package utng.gids.arg.auditoria.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {
    @Serializable
    data object Dashboard : Route

    @Serializable
    data object ClauseList : Route

    @Serializable
    data class ClauseDetails(val clauseNumber: String) : Route

    @Serializable
    data object Chronogram : Route

    @Serializable
    data object Checklist : Route

    @Serializable
    data object Objectives : Route

    @Serializable
    data object Methodology : Route
}
