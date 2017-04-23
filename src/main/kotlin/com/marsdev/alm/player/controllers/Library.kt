package com.marsdev.alm.player.controllers

import com.marsdev.alm.player.models.Album
import com.marsdev.alm.player.service.AlbumService
import tornadofx.*

class Library : Controller() {
    val albumService = AlbumService()
    val albums = HashSet<Album>()

    fun getAlbums(): List<Album> {
        if (albums.size == 0) {
            loadAlbums()
        }
        return albums.toList().sorted()
    }

    fun loadAlbums(): Boolean {
        if (albums.size == 0) {
            albums.addAll(albumService.getAlbums("D:\\temp\\music-small"))
        }
        return true
    }

}