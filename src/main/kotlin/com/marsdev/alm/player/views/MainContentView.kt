package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.PlayerStyles
import javafx.scene.layout.StackPane
import tornadofx.*

class MainContentView : View("Content") {
    override val root = stackpane {
        addClass(PlayerStyles.contentPane)
    }

    fun getContentPane(): StackPane {
        return root
    }
}