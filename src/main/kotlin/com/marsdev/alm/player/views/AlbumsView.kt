package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.PlayerStyles
import com.marsdev.alm.player.models.AlbumModel
import com.marsdev.alm.player.models.LibraryModel
import javafx.scene.effect.DropShadow
import javafx.scene.input.ScrollEvent
import javafx.scene.paint.Color
import tornadofx.*

class AlbumsView : View("Albums") {
    val currentAlbum: AlbumModel by inject()

    object AlbumsScroll : FXEvent()


    override val root = borderpane {
        addClass(PlayerStyles.albumsView)
        top {
            label("Albums").setId(PlayerStyles.subTitle)
        }
        center {
            setId(PlayerStyles.innerContentPane)
            datagrid(LibraryModel.currentAlbums) {
                addClass(PlayerStyles.albumDataGrid)
                cellHeight = 300.0
                cellWidth = 300.0
                cellCache {
                    vbox {
                        imageview(it.image) {
                            fitHeight = 250.0
                            fitWidth = 250.0
                            effect = DropShadow()
                        }
                        label(it.name).setId(PlayerStyles.albumTitle)
                        label(it.artist.name).setId(PlayerStyles.albumArtist)
                        addClass(PlayerStyles.albumCell)
                    }
                }
                onUserSelect {
                    println("Album: ${it.name}  Track Count:  ${it.tracks.size}")
                    currentAlbum.item = it
                    replaceWith(AlbumView::class, ViewTransition.FadeThrough(1.0.seconds, Color.BLACK))
                }
            }.addEventFilter(ScrollEvent.ANY) {
                fire(AlbumsScroll)
            }
        }

    }
}