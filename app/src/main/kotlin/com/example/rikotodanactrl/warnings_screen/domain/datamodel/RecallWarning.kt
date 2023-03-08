package com.example.rikotodanactrl.warnings_screen.domain.datamodel
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteRecallWarning
import java.time.LocalDate

data class RecallWarning(
    val UPC: Long,
    val name: String,
    val reason: String,
    val batches: List<String>?,
    val expiry_dates: List<LocalDate>?,
    val issued_at: LocalDate,
    val info_url: String) {
    companion object {
        fun convertFromRemote(remote: remoteRecallWarning): RecallWarning {
            val issue_date = LocalDate.parse(remote.issued_at)
            var expiry_list: MutableList<LocalDate> = mutableListOf()
            if (remote.expiry_dates != null) {
                for (ex_date in remote.expiry_dates)
                {
                    expiry_list.add(LocalDate.parse(ex_date))
                }
            }
            return RecallWarning(remote.UPC, remote.name, remote.reason, remote.batches, expiry_list, issue_date,
                remote.info_url)
        }
    }
}