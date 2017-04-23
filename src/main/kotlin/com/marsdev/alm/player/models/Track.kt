package com.marsdev.alm.player.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

data class Track(val artist: Artist, val album: Album, val title: String, val year: Int,
                 val sampleRate: Int, val channels: Int, val bps: Int, val bitRate: Int, val startPosition: Long, val fileName: String, val directory: String)

class TrackModel : ItemViewModel<Track>() {
    val artist = bind { if (item == null) SimpleObjectProperty() else item?.artist?.toProperty() }
    val album = bind { if (item == null) SimpleObjectProperty() else item?.album?.toProperty() }
    val title = bind { if (item == null) SimpleStringProperty() else SimpleStringProperty(item?.title) }
    val year = bind { if (item == null) SimpleIntegerProperty() else SimpleIntegerProperty(item?.year ?: 0) }
    val sampleRate = bind { if (item == null) SimpleIntegerProperty() else SimpleIntegerProperty(item?.sampleRate ?: 0) }
    val channels = bind { if (item == null) SimpleIntegerProperty() else SimpleIntegerProperty(item?.channels ?: 0) }
    val bps = bind { if (item == null) SimpleIntegerProperty() else SimpleIntegerProperty(item?.bps ?: 0) }
    val bitRate = bind { if (item == null) SimpleIntegerProperty() else SimpleIntegerProperty(item?.bitRate ?: 0) }
    val startPosition = bind { if (item == null) SimpleLongProperty() else SimpleLongProperty(item?.startPosition ?: 0) }
    val fileName = bind { if (item == null) SimpleStringProperty() else SimpleStringProperty(item?.fileName) }
    val directory = bind { if (item == null) SimpleStringProperty() else SimpleStringProperty(item?.directory) }
}

