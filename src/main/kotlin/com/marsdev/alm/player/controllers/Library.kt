package com.marsdev.alm.player.controllers

import com.marsdev.alm.player.models.Album
import com.marsdev.alm.player.service.AlbumService
import tornadofx.*

class Library : Controller() {
    val albumService = AlbumService()
    val albums = HashSet<Album>()

    init {
        albums.addAll(albumService.getAlbums("D:\\temp\\music-small"))
    }

    fun getAlbums(): List<Album> {
        return albums.toList().sorted()
    }

}