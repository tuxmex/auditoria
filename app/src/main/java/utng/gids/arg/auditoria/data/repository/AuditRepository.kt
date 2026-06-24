package utng.gids.arg.auditoria.data.repository

import kotlinx.coroutines.flow.Flow
import utng.gids.arg.auditoria.data.dao.AuditDao
import utng.gids.arg.auditoria.data.entity.*

class AuditRepository(private val auditDao: AuditDao) {

    fun getAuditPlan(): Flow<AuditPlan?> = auditDao.getAuditPlan()
    suspend fun insertAuditPlan(plan: AuditPlan) = auditDao.insertAuditPlan(plan)

    fun getAllClauses(): Flow<List<IsoClause>> = auditDao.getAllClauses()
    suspend fun insertClauses(clauses: List<IsoClause>) = auditDao.insertClauses(clauses)

    fun getQuestionsByClause(clauseNumber: String): Flow<List<AuditQuestion>> = auditDao.getQuestionsByClause(clauseNumber)
    suspend fun insertQuestions(questions: List<AuditQuestion>) = auditDao.insertQuestions(questions)

    fun getChecklistItem(questionId: Long): Flow<ChecklistItem?> = auditDao.getChecklistItem(questionId)
    suspend fun insertChecklistItem(item: ChecklistItem) = auditDao.insertChecklistItem(item)
    fun getAllChecklistItems(): Flow<List<ChecklistItem>> = auditDao.getAllChecklistItems()

    suspend fun seedDatabase() {
        val clauses = listOf(
            IsoClause("4.1", "Understanding the Organization and Its Context", "Focus on internal and external factors affecting the psychopedagogical department."),
            IsoClause("4.2", "Needs and Expectations of Interested Parties", "Identifying what students, parents, and teachers expect from support services."),
            IsoClause("6.1", "Actions to Address Risks and Opportunities", "Preventing service failure and exploiting opportunities for improvement."),
            IsoClause("8.1", "Operational Planning and Control", "Establishing protocols and rules of engagement for support services."),
            IsoClause("8.5.1", "Control of Delivery (Admission & Services)", "Ensuring fair, inclusive, and documented entry into support services."),
            IsoClause("8.5.5", "Post-Delivery Activities", "Follow-up after intervention or at the end of the school year."),
            IsoClause("9.1.2", "Satisfaction of Learners, Beneficiaries, and Staff", "Measuring if the support services improved the learner's experience.")
        )
        auditDao.insertClauses(clauses)

        val questions = listOf(
            AuditQuestion(clauseNumber = "4.1", questionText = "What external trends and internal factors have been identified?", suggestedEvidence = "SWOT Analysis, Context Report, Meeting Minutes"),
            AuditQuestion(clauseNumber = "4.1", questionText = "How does the department's mission align with the strategic direction?", suggestedEvidence = "Strategic Plan, Departmental Mission Statement"),
            
            AuditQuestion(clauseNumber = "4.2", questionText = "Who are the interested parties for the psychopedagogical area?", suggestedEvidence = "Stakeholder Matrix"),
            AuditQuestion(clauseNumber = "4.2", questionText = "What are their specific requirements for confidentiality and accessibility?", suggestedEvidence = "Legal Compliance Register, Data Protection Policy"),

            AuditQuestion(clauseNumber = "6.1", questionText = "What are the risks of the psychopedagogical process?", suggestedEvidence = "Risk Register with mitigation plans"),
            AuditQuestion(clauseNumber = "6.1", questionText = "What opportunities have been identified for improvement?", suggestedEvidence = "Action Plans for new programs"),

            AuditQuestion(clauseNumber = "8.1", questionText = "Are there documented procedures for psychological triage?", suggestedEvidence = "Service Protocols, Manuals"),
            AuditQuestion(clauseNumber = "8.1", questionText = "How is the support service planned to meet individual needs?", suggestedEvidence = "Process Maps, Resource Allocation Plan"),

            AuditQuestion(clauseNumber = "8.5.1", questionText = "What are the criteria for specialized support?", suggestedEvidence = "Admission Forms, Criteria Document"),
            AuditQuestion(clauseNumber = "8.5.1", questionText = "How do you ensure non-discriminatory admission?", suggestedEvidence = "Inclusion Policy, IEPs"),

            AuditQuestion(clauseNumber = "8.5.5", questionText = "How do you follow up after a crisis intervention?", suggestedEvidence = "Follow-up Survey/Reports"),
            AuditQuestion(clauseNumber = "8.5.5", questionText = "What happens to student files once they leave?", suggestedEvidence = "Archival Protocol"),

            AuditQuestion(clauseNumber = "9.1.2", questionText = "How do you measure satisfaction regarding support?", suggestedEvidence = "Satisfaction Surveys tailored for psychopedagogy"),
            AuditQuestion(clauseNumber = "9.1.2", questionText = "How are complaints or negative feedback handled?", suggestedEvidence = "Complaints/Appeals Log")
        )
        auditDao.insertQuestions(questions)
        
        val defaultPlan = AuditPlan(
            objectOfAudit = "Área Psicopedagógica (Campus central y Victoria)",
            leadAuditor = "Anastacio Rodríguez García",
            auditTeam = "Equipo de Auditoría Interna",
            date = "2025-10-25",
            time = "12:30 - 14:00",
            scope = "Verificar el grado de cumplimiento del SGOE y SGC conforme a la norma ISO 21001:2025.",
            criteria = "ISO 21001:2025, documentación interna, requisitos legales.",
            auditType = "Auditoría Interna (presencial)",
            objectives = "Evaluar conformidad, verificar eficacia, identificar mejoras, asegurar cumplimiento de necesidades de estudiantes.",
            methodology = "Revisión documental, Entrevistas, Observación directa, Muestreo, Triangulación."
        )
        auditDao.insertAuditPlan(defaultPlan)
    }
}
