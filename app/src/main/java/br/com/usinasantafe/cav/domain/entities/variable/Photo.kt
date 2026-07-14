package br.com.usinasantafe.cav.domain.entities.variable

import android.net.Uri

data class Photo(
    val id: Int,
    val uri: Uri,
    val path: String
)
