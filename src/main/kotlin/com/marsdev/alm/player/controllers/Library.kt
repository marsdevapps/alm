package com.marsdev.alm.player.controllers

import com.marsdev.alm.player.app.LibraryScope
import com.marsdev.alm.player.service.AlbumService
import com.marsdev.alm.player.views.AlbumsView
import com.marsdev.alm.player.views.MainContentView
import tornadofx.*

class Library : Controller() {
    override val scope = super.scope as LibraryScope
    val albumService = AlbumService()
    val albumsView = find(AlbumsView::class)
    val mainContentView: MainContentView by inject()

    fun loadAlbums(): Boolean {
        if (scope.currentAlbums.size == 0) {
            scope.currentAlbums.addAll(albumService.getAlbums("D:\\temp\\music-small"))
        }
        return true
    }

    fun showAlbumsView() {
        mainContentView.getContentPane().children.setAll(albumsView.root)
    }

}