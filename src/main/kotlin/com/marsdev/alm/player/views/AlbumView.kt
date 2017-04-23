package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.LibraryScope
import com.marsdev.alm.player.app.PlayerStyles
import javafx.scene.effect.DropShadow
import tornadofx.*

class AlbumView : View("Album") {
    override val scope = super.scope as LibraryScope
    override val root = borderpane {
        addClass(PlayerStyles.albumView)
        center {
            vbox {
                label(scope.currentAlbum.name)

                imageview {
                    fitHeight = 350.0
                    fitWidth = 350.0
                    effect = DropShadow()
                    imageProperty().bind(scope.currentAlbum.image)

                }
                listview(scope.currentAlbum.tracks) {
                }
            }
        }

    }
}