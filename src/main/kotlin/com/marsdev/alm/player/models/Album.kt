package com.marsdev.alm.player.models

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.image.Image
import tornadofx.*

class Album : Comparable<Album> {
    val imageProperty = SimpleObjectProperty<Image>()
    var image by imageProperty

    val tracks = FXCollections.observableArrayList<Track>()

    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    val artistProperty = SimpleObjectProperty<Artist>()
    var artist by artistProperty

    override fun compareTo(other: Album): Int {
        return name.compareTo(other.name, true)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Album

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = artist.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}

class AlbumModel : ItemViewModel<Album>() {
    val image = bind(Album::imageProperty)
    val tracks = bind(Album::tracks)
    val name = bind(Album::nameProperty)
    val artist = bind(Album::artistProperty)
}




