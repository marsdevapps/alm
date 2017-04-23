package com.marsdev.alm.player.models

import javafx.beans.property.SimpleDoubleProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList

class LibraryModel {
    val currentAlbum = AlbumModel()
    val currentTrack = TrackModel()
    val currentAlbums: ObservableList<Album> = FXCollections.observableArrayList<Album>()

    val progress = SimpleDoubleProperty(0.0)
    val duration = SimpleDoubleProperty(0.0)
    val maxDuration = SimpleDoubleProperty(10.0)
}