package com.example.rikotodanactrl.inventory_screen.data.remote.dtos

@kotlinx.serialization.Serializable
class remotePhoto (val photo: String,
                   val taken_at: String) {

    /*private fun photoBytes(): ByteArray
    {
        val decoder: Base64.Decoder = Base64.getDecoder()
        return decoder.decode(this.photo)
    }

    fun toPhoto(): Photo
    {
        return Photo(this.photoBytes(), LocalDateTime.parse(taken_at))
    }*/
}

