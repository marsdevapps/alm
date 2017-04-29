package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.LibraryScope
import com.marsdev.alm.player.app.PlayerStyles
import com.marsdev.alm.player.controllers.Library
import com.marsdev.alm.player.models.AlbumModel
import com.marsdev.alm.player.models.TrackModel
import javafx.scene.effect.DropShadow
import tornadofx.*

class AlbumView : View("Album") {
    override val scope = super.scope as LibraryScope
    val library: Library by inject()
    val currentAlbum: AlbumModel by inject()
    val currentTrack: TrackModel by inject()
    override val root = borderpane {
        addClass(PlayerStyles.albumView)
        center {
            vbox {
                label(currentAlbum.name)

                imageview {
                    fitHeight = 350.0
                    fitWidth = 350.0
                    effect = DropShadow()
                    imageProperty().bind(currentAlbum.image)

                }
                listview(currentAlbum.tracks) {
                    addClass(PlayerStyles.albumTrackList)
                    onUserSelect {
                        currentTrack.item = it
                        library.setMedia(it.directory + "\\" + it.fileName)
                        library.play()
                    }

                    cellCache {
                        hbox {
                            label(it.title)
                            spacer { prefWidth = 25.0 }
                            label(it.artist.name)
                            spacer { prefWidth = 25.0 }
                            label(it.year.toString())
                        }
                    }
                }
            }
        }

    }
}