package com.marsdev.alm.player.app

import com.marsdev.alm.player.models.Album
import com.marsdev.alm.player.models.AlbumModel
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*

class LibraryScope : Scope() {

    val currentAlbum = AlbumModel()
    val currentAlbums: ObservableList<Album> = FXCollections.observableArrayList<Album>()
}