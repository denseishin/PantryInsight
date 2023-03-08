package com.example.rikotodanactrl.warnings_screen.presentation.warnings
import com.example.rikotodanactrl.warnings_screen.domain.datamodel.RecallWarning

data class RecallWarningStates(
    val recallWarnings: List<RecallWarning> = emptyList(),
    val error: String = ""
)
