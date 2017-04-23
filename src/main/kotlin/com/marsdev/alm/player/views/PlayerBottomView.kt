package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.PlayerStyles
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import tornadofx.*

class PlayerBottomView : View("Bottom") {
    val bottomIcon = MaterialIconView(MaterialIcon.FORMAT_LIST_BULLETED)

    init {
        bottomIcon.glyphSize = 40.0
    }

    override val root = gridpane {
        setId(PlayerStyles.bottomPlayerBar)
        minHeight = 120.0
        maxHeight = 120.0
        prefHeight = 120.0
        minHeight = 120.0
        row {
            add(bottomIcon)
        }

    }
}
