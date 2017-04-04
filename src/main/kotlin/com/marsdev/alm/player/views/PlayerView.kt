package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.PlayerStyles
import tornadofx.*

class PlayerView : View("ALM Player") {
    override val root = borderpane {
        addClass(PlayerStyles.contentPane)
        left {
            label("Nav Bar")
        }
        center {
            vbox {
                label("Albums").setId(PlayerStyles.title)
                label("Albums").setId(PlayerStyles.subTitle)

            }
        }
    }
}