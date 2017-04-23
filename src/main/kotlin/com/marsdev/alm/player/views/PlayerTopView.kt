package com.marsdev.alm.player.views

import com.marsdev.alm.simple.app.fontAwesomeIcon
import com.marsdev.alm.simple.app.materialIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import javafx.geometry.Orientation
import javafx.geometry.Side
import tornadofx.*
import tornadofx.control.*

class PlayerTopView : View("Top") {
    val backItem = ListItem("", fontAwesomeIcon(FontAwesomeIcon.ANGLE_LEFT, 30))
    val forwardItem = ListItem("", fontAwesomeIcon(FontAwesomeIcon.ANGLE_RIGHT, 30))
    val upItem = ListItem("", materialIcon(MaterialIcon.ARROW_UPWARD, 30))
    val topMenu = ListMenu(backItem, forwardItem, upItem)

    init {
        topMenu.orientation = Orientation.HORIZONTAL
        topMenu.iconPosition = Side.LEFT
    }

    override val root = vbox {
        add(topMenu)


    }
}

