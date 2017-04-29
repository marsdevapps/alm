package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.LibraryScope
import com.marsdev.alm.player.app.PlayerStyles
import com.marsdev.alm.player.controllers.Library
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.geometry.Pos
import javafx.scene.effect.GaussianBlur
import javafx.scene.layout.Priority
import tornadofx.*

class PlayerBottomView : View("Bottom") {
    override val scope = super.scope as LibraryScope
    val library: Library by inject()
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
                imageProperty().bind(scope.currentAlbum.image)
                fitHeight = bottomHeight
                fitWidth = 100.0
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 6.0
                }
            }.setId(PlayerStyles.bottomPlayerBar)

            // track title
            stackpane {
                rectangle {
                    fill = c("#364743")
//                    effect = BoxBlur(10.0, 10.0, 3)
                    effect = GaussianBlur()
                    opacity = 0.70
                    widthProperty().bind(this@stackpane.widthProperty())
                    heightProperty().bind(this@stackpane.heightProperty())

                }
                label(scope.currentTrack.title).setId(PlayerStyles.bottomPlayerBarTrackTitle)
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 17.0
                }
            }

            stackpane {
                rectangle {
                    fill = c("#0E211E")
//                    effect = BoxBlur(10.0, 10.0, 3)
                    effect = GaussianBlur()
                    opacity = 0.70
                    widthProperty().bind(this@stackpane.widthProperty())
                    heightProperty().bind(this@stackpane.heightProperty())

                }
                label(stringBinding(scope.duration) {
                    com.marsdev.alm.util.TimeFormatter.formatMilliseconds(value.toLong())
                }) {
                    maxWidth = Double.MAX_VALUE
                    alignment = Pos.CENTER_RIGHT
                    setId(PlayerStyles.bottomPlayerBarTrackDurationLabel)
                }
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 23.0
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
