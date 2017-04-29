package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.fontAwesomeIcon
import com.marsdev.alm.player.app.materialIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import javafx.geometry.Orientation
import javafx.geometry.Side
import tornadofx.*

class PlayerTopView : View("Top") {

    override val root = vbox {
        listmenu {
            iconPosition = Side.LEFT
            orientation = Orientation.HORIZONTAL
            item("", fontAwesomeIcon(FontAwesomeIcon.ANGLE_LEFT, 30))
            item("", fontAwesomeIcon(FontAwesomeIcon.ANGLE_RIGHT, 30))
            item("", materialIcon(MaterialIcon.ARROW_UPWARD, 30))
        }
    }
}

