package com.marsdev.alm.player.models

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

data class Artist(val name: String)

class ArtistModel : ItemViewModel<Artist>() {
    val name = bind { if (item == null) SimpleStringProperty() else SimpleStringProperty(item?.name) }
}



