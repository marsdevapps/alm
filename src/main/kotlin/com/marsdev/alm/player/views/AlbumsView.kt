package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.PlayerStyles
import com.marsdev.alm.player.controllers.Library
import javafx.scene.effect.DropShadow
import tornadofx.*

class AlbumsView : View("Albums") {

    val library: Library by inject()


    override val root = borderpane {

        top {
            label("Albums").setId(PlayerStyles.subTitle)
        }
        center {
            setId(PlayerStyles.innerContentPane)
            datagrid(library.getAlbums()) {
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
            }
        }

    }
}