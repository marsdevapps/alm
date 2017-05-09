package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.PlayerStyles
import com.marsdev.alm.player.controllers.Library
import com.marsdev.alm.player.models.AlbumModel
import com.marsdev.alm.player.models.Track
import com.marsdev.alm.player.models.TrackModel
import javafx.scene.effect.DropShadow
import tornadofx.*

class AlbumView : View("Album") {
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

                tableview(currentAlbum.tracks) {
                    addClass(PlayerStyles.albumTrackList)
                    column("Title", Track::title).pctWidth(40.0)
                    column("Artist", Track::artist).pctWidth(40.0)
                    column("Year", Track::year).pctWidth(20.0)
                    columnResizePolicy = SmartResize.POLICY
                    onUserSelect {
                        currentTrack.item = it
                        library.setMedia(it.directory + "\\" + it.fileName)
                        library.playCommand.execute()
                    }
                }

                /*
                listview(currentAlbum.tracks) {
                    addClass(PlayerStyles.albumTrackList)
                    onUserSelect {
                        currentTrack.item = it
                        library.setMedia(it.directory + "\\" + it.fileName)
                        library.playCommand.execute()
                    }

                    cellCache {
                        hbox {
                            label(it.title)
                            label(it.artist.name)
                            label(it.year.toString())
                            style {
                                spacing = 200.px
                                maxWidth = infinity
                            }
                        }
                    }
                }
                */
            }
        }

    }
}