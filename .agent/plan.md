# Project Plan

An Android application called "auditoria" for managing an internal audit plan for the Psychopedagogical area at UTNG, based on ISO 21001:2025. The app should include sections for general audit information, objectives, ISO clauses to evaluate (4.1, 4.2, 6.1, 8.1, 8.5.1, 8.5.5, 9.1.2), a chronogram, methodology details, a question guide for each clause with suggested evidence, and a checklist for tracking compliance. The app must follow Material Design 3, have a vibrant/energetic color scheme, adaptive icon, and full edge-to-edge display.

## Project Brief

# Project Brief: Auditoria - Internal Audit Manager (ISO 21001:2025)

This application is designed to manage and execute the internal audit plan for the Psychopedagogical area at UTNG, strictly adhering to the **ISO 21001:2025** standard. It provides a structured digital environment for auditors to evaluate educational management systems with a modern, high-energy interface.

## Features

*   **ISO 21001:2025 Audit Dashboard:** Centralized access to the audit's general information, specific objectives, and methodology tailored for the Psychopedagogical department.
*   **Clause-Specific Question Guide:** An interactive guide covering mandatory clauses (4.1, 4.2, 6.1, 8.1, 8.5.1, 8.5.5, 9.1.2) with detailed question sets and suggested evidence requirements.
*   **Internal Audit Chronogram:** A visual timeline and scheduling tool to manage the audit's progress and ensure all sessions are completed according to the plan.
*   **Digital Compliance Checklist:** A real-time tracking system to evaluate and record compliance status for each ISO requirement during the audit process.

## High-Level Technical Stack

*   **Language:** Kotlin
*   **UI Framework:** Jetpack Compose with **Material Design 3** (vibrant/energetic theme)
*   **Navigation:** **Jetpack Navigation 3** (State-driven architecture)
*   **Adaptive Strategy:** **Compose Material Adaptive** library (supporting edge-to-edge displays and various form factors)
*   **Concurrency:** Kotlin Coroutines & Flow
*   **Image Loading:** Coil (for evidence documentation)

## Implementation Steps
**Total Duration:** 5h 11m 26s

### Task_1_Foundation_Navigation: Initialize the project architecture, including the Material 3 theme with a vibrant color scheme, Edge-to-Edge support, and Navigation 3 setup. Define data models and Room database for persisting audit data and checklist status.
- **Status:** COMPLETED
- **Updates:** Initialized project architecture with Material 3 theme, Edge-to-Edge support, and Navigation 3. Defined Room database and data models for AuditPlan, IsoClause, AuditQuestion, and ChecklistItem. Implemented seeding for ISO 21001:2025 clauses and questions. Project builds successfully.
- **Acceptance Criteria:**
  - Vibrant Material 3 theme and Edge-to-Edge implemented
  - Navigation 3 structure is functional
  - Room database and data models for Audit and Checklist are defined
  - Project builds successfully
- **Duration:** 1h 14m 35s

### Task_2_Dashboard_Chronogram: Implement the Audit Dashboard as the main entry point, providing access to general audit info, objectives, and methodology. Build the Internal Audit Chronogram view to visualize the audit timeline using adaptive layouts.
- **Status:** COMPLETED
- **Updates:** Implemented Audit Dashboard with general information and navigation to Objectives and Methodology screens. Built a visual Internal Audit Chronogram with adaptive layout. Updated Navigation 3 routes and maintained Material 3 theme and Edge-to-Edge support.
- **Acceptance Criteria:**
  - Dashboard displays audit overview
  - Chronogram screen shows a visual timeline of audit activities
  - Objectives and Methodology screens are implemented and reachable
  - Adaptive layouts used for different screen sizes
- **Duration:** 1h 4m 16s

### Task_3_ISO_Guide_Checklist: Develop the interactive Question Guide for ISO 21001:2025 clauses (4.1, 4.2, 6.1, 8.1, 8.5.1, 8.5.5, 9.1.2) and the Digital Compliance Checklist for real-time tracking.
- **Status:** COMPLETED
- **Updates:** Developed the interactive Question Guide for ISO 21001:2025 clauses (4.1, 4.2, 6.1, 8.1, 8.5.1, 8.5.5, 9.1.2) and the Digital Compliance Checklist. Implemented real-time evaluation with status marking and observations. Ensured persistence using Room. Added an adaptive app icon and refined the vibrant Material 3 aesthetic. Project builds successfully.
- **Acceptance Criteria:**
  - Question guide covers all specified ISO clauses with suggested evidence
  - Compliance checklist allows marking status and persists data using Room
  - Navigation between clauses and checklist is seamless
- **Duration:** 1h 11m 2s

### Task_4_Assets_Verification: Create an adaptive app icon matching the audit theme. Conduct final UI refinements for the vibrant aesthetic and perform a comprehensive app verification to ensure stability and requirement alignment.
- **Status:** COMPLETED
- **Updates:** Created an adaptive app icon matching the audit theme. Conducted final UI refinements for the vibrant aesthetic and performed a comprehensive app verification. Resolved issues with database seeding and edge-to-edge inset handling. The app is stable, functionally complete (Dashboard, Chronogram, Question Guide, Checklist), and follows Material Design 3 guidelines. Final verification by critic agent passed.
- **Acceptance Criteria:**
  - Adaptive icon is implemented and matches the app's purpose
  - Vibrant and energetic color scheme is consistently applied across all screens
  - App builds and runs without crashes
  - All features (Dashboard, Chronogram, Checklist, Guide) are verified for stability
- **Duration:** 1h 41m 33s

