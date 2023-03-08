package com.example.rikotodanactrl.warnings_screen.presentation.warnings
import com.example.rikotodanactrl.warnings_screen.domain.datamodel.ExpiryWarning

data class ExpiryWarningStates(
    val expiryWarnings: List<ExpiryWarning> = emptyList(),
    val error: String = ""
)
