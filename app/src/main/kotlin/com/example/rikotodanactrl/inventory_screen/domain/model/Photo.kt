package com.example.rikotodanactrl.inventory_screen.domain.model
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.rikotodanactrl.inventory_screen.data.remote.dtos.remotePhoto
import java.time.LocalDateTime
import java.util.Base64

class Photo (val photo: ByteArray,
             val taken_at: LocalDateTime) {
    fun toBitmap(): Bitmap
    {
        return BitmapFactory.decodeByteArray(photo,0, photo.size)
    }
    companion object {
        fun convertFromRemoteDtos(remote: remotePhoto): Photo {
            val decoder: Base64.Decoder = Base64.getDecoder()
            val pbytes = decoder.decode(remote.photo)
            return Photo(pbytes, LocalDateTime.parse(remote.taken_at))
        }
    }
}