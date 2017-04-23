package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.PlayerStyles
import com.marsdev.alm.player.controllers.Library
import com.marsdev.alm.simple.app.fontAwesomeIcon
import com.marsdev.alm.simple.app.materialIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import tornadofx.*
import tornadofx.control.*

class PlayerSideNavView : View("Navigation") {
    val hamburgerMenuItem = ListItem("", materialIcon(MaterialIcon.DEHAZE, 30))
    val searchMenuItem = ListItem("", fontAwesomeIcon(FontAwesomeIcon.SEARCH, 30))
    val locationItem = ListItem("", fontAwesomeIcon(FontAwesomeIcon.LOCATION_ARROW, 30))
    val albumItem = ListItem("", fontAwesomeIcon(FontAwesomeIcon.DOT_CIRCLE_ALT, 30))
    val musicItem = ListItem("", fontAwesomeIcon(FontAwesomeIcon.MUSIC, 30))
    val graphicEQItem = ListItem("", materialIcon(MaterialIcon.GRAPHIC_EQ, 30))
    val userMenuItem = ListItem("", fontAwesomeIcon(FontAwesomeIcon.USER_ALT, 30))
    val settingsItem = ListItem("", fontAwesomeIcon(FontAwesomeIcon.GEARS, 30))
    val navMenu = ListMenu(hamburgerMenuItem, searchMenuItem, locationItem, albumItem, musicItem, graphicEQItem, userMenuItem)
    val bottomNavMenu = ListMenu(settingsItem)
    val library: Library by inject()
    init {
        navMenu.iconPosition = Side.TOP
        albumItem.setOnMouseClicked {
            library.showAlbumsView()
        }
    }

    override val root = vbox {
        vgrow = Priority.ALWAYS
        alignment = Pos.TOP_CENTER
        prefHeight = 960.0
        minWidth = 120.0
        add(
                svgpath("M40.668,43.206H30.9a.32.32,0,0,1-.256-.127L20.975,30.672l6.856-3.959a.041.041,0,0,0,.012.016L40.92,42.673A.327.327,0,0,1,40.668,43.206ZM27.845,26.312a.229.229,0,0,0-.042.067l-6.47-3.737,8.623-10.691a.323.323,0,0,1,.254-.12H39.5a.325.325,0,0,1,.247.538ZM19.808,43.206H12.154a.325.325,0,0,1-.325-.325V35.953l8.3-4.794V42.881A.325.325,0,0,1,19.808,43.206Zm-7.979-31.05a.325.325,0,0,1,.325-.325h7.578a.325.325,0,0,1,.325.325l.035,9.77-8.263-4.771Z") {
                    stroke = Color.WHITE
                    fill = Color.WHITE
                }
        )
        add(region {
            prefHeight = 40.0
            minHeight = 40.0
        })
        add(navMenu)
        add(region { prefHeight = 800.0 })
        add(bottomNavMenu)
        add(region {
            prefHeight = 50.0
            minHeight = 50.0
        })
        setId(PlayerStyles.sideBar)
    }
}