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
        setId(PlayerStyles.bottomPlayerBar)
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
            }
            imageview {
                imageProperty().bind(scope.currentAlbum.image)
                fitHeight = 120.0
                fitWidth = 120.0
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 6.0
                }
            }
            label(scope.currentTrack.title) {
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 17.0
                }
            }
            label(scope.progress) {
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 23.0
                }
            }

            hbox {
                button("Play").action { library.play() }
                button("Pause").action { library.pause() }
                button("Stop").action { library.stop() }
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 38.0
                }
            }

        }

    }
}
