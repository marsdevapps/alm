package com.marsdev.alm.player.models

import tornadofx.*

data class Artist(val name: String)

class ArtistModel : ItemViewModel<Artist>() {
    val name = bind(Artist::name)
}



