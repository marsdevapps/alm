package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.LibraryScope
import com.marsdev.alm.player.app.PlayerStyles
import com.marsdev.alm.player.controllers.Library
import com.marsdev.alm.player.models.AlbumModel
import com.marsdev.alm.player.models.TrackModel
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.geometry.Pos
import javafx.scene.effect.GaussianBlur
import javafx.scene.layout.Priority
import tornadofx.*

class PlayerBottomView : View("Bottom") {
    override val scope = super.scope as LibraryScope
    val library: Library by inject()
    val currentAlbum: AlbumModel by inject()
    val currentTrack: TrackModel by inject()

    val bottomIcon = MaterialIconView(MaterialIcon.FORMAT_LIST_BULLETED)

    val bottomHeight = 100.0

    init {
        bottomIcon.glyphSize = 40.0
    }

    override val root = gridpane {
        addClass(PlayerStyles.bottomPlayerBar)
        maxHeight = bottomHeight
        prefHeight = bottomHeight
        minHeight = bottomHeight
        row {
            hbox {
                prefWidth = 120.0
                alignment = Pos.CENTER
                add(bottomIcon)
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                }
            }.setId(PlayerStyles.bottomPlayerBarRightCorner)

            imageview {
                imageProperty().bind(currentAlbum.image)
                fitHeight = bottomHeight
                fitWidth = 100.0
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 6.0
                }
            }.setId(PlayerStyles.bottomPlayerBar)

            stackpane {
                rectangle {
                    fill = c("#0E211E")
//                    effect = BoxBlur(10.0, 10.0, 3)
                    effect = GaussianBlur()
                    opacity = 0.70
                    widthProperty().bind(this@stackpane.widthProperty())
                    heightProperty().bind(this@stackpane.heightProperty())

                }
                progressbar(scope.progress) {
                    hgrow = Priority.ALWAYS
                    addClass(PlayerStyles.progressBar)
                }
                hbox {
                    label(currentTrack.title) {
                        setId(PlayerStyles.bottomPlayerBarTrackTitle)
                        hgrow = Priority.ALWAYS
                    }

                    label(stringBinding(scope.duration) {
                        com.marsdev.alm.util.TimeFormatter.formatMilliseconds(value.toLong())
                    }) {
                        setId(PlayerStyles.bottomPlayerBarTrackDurationLabel)
                        hgrow = Priority.ALWAYS
                    }

                }
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 40.0
                }
                setId(PlayerStyles.bottomPlayerBarTrackDuration)
            }
            hbox {
                button {
                    addClass(PlayerStyles.previousButton)
                    isPickOnBounds = true
                }

                togglebutton {
                    selectedProperty().bindBidirectional(scope.trackPlaying)
                    addClass(PlayerStyles.playButton)
                    isPickOnBounds = true
                    setOnAction {
                        if (isSelected) {
                            library.play()
                        } else {
                            library.pause()
                        }
                    }
                }

                button {
                    addClass(PlayerStyles.nextButton)
                    isPickOnBounds = true
                }

                button {
                    addClass(PlayerStyles.volumeButton)
                    isPickOnBounds = true
                }



                gridpaneColumnConstraints {
                    hgrow = Priority.ALWAYS
//                    percentWidth = 38.0
                    alignment = Pos.CENTER_RIGHT
                }
            }.setId(PlayerStyles.bottomPlayerBarTrackControls)

        }

    }
}
