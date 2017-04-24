package com.marsdev.alm.player.views

import javafx.scene.layout.StackPane
import tornadofx.*

class MainContentView : View("Content") {
    override val root = stackpane {
    }

    fun getContentPane(): StackPane {
        return root
    }
}