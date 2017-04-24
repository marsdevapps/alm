package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.LibraryScope
import com.marsdev.alm.player.app.PlayerStyles
import com.marsdev.alm.player.controllers.Library
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.scene.layout.Priority
import tornadofx.*

class PlayerBottomView : View("Bottom") {
    override val scope = super.scope as LibraryScope
    val library: Library by inject()
    val bottomIcon = MaterialIconView(MaterialIcon.FORMAT_LIST_BULLETED)

    init {
        bottomIcon.glyphSize = 40.0
    }

    override val root = gridpane {
        addClass(PlayerStyles.bottomPlayerBar)
        maxHeight = 120.0
        prefHeight = 120.0
        minHeight = 120.0
        row {
            hbox {
                add(bottomIcon)
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 6.0
                }
            }.setId(PlayerStyles.bottomPlayerBarRightCorner)
            imageview {
                imageProperty().bind(scope.currentAlbum.image)
                fitHeight = 120.0
                fitWidth = 120.0
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 6.0
                }
            }.setId(PlayerStyles.bottomPlayerBar)
            label(scope.currentTrack.title) {
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 17.0
                }
            }.setId(PlayerStyles.bottomPlayerBarTrackTitle)
            label(stringBinding(scope.duration) {
                com.marsdev.alm.util.TimeFormatter.formatMilliseconds(value.toLong())
            }) {
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 23.0
                }
            }.setId(PlayerStyles.bottomPlayerBarTrackDuration)

            hbox {
                button(graphic = PlayerStyles.playIcon()) {
                    setOnAction { library.play() }

                }
                button(graphic = PlayerStyles.pauseIcon()) {
                    setOnAction { library.pause() }
                }

                button(graphic = PlayerStyles.stopIcon()) {
                    setOnAction { library.stop() }
                }
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 38.0
                }
            }.setId(PlayerStyles.bottomPlayerBarTrackControls)

        }

    }
}
