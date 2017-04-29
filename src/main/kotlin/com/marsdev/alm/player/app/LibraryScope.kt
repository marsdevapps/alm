package com.marsdev.alm.player.app

import com.marsdev.alm.player.models.Album
import com.marsdev.alm.player.models.AlbumModel
import com.marsdev.alm.player.models.TrackModel
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*

class LibraryScope : Scope() {

    val currentAlbum = AlbumModel()
    val currentTrack = TrackModel()
    val currentAlbums: ObservableList<Album> = FXCollections.observableArrayList<Album>()

    val trackPlaying = SimpleBooleanProperty(false)
    val progress = SimpleDoubleProperty(0.0)
    val duration = SimpleDoubleProperty(0.0)
    val maxDuration = SimpleDoubleProperty(10.0)
}