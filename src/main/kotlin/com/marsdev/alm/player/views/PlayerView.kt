package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.PlayerStyles
import tornadofx.*

class PlayerView : View("ALM Player") {
    override val root = stackpane {
        borderpane {
            addClass(PlayerStyles.contentPane)
            left(PlayerSideNavView::class)
            center {
                borderpane {
                    top(PlayerTopView::class)
                    center(MainContentView::class).addClass(PlayerStyles.stackContentPane)
                }
            }
        }
        borderpane {
            bottom(PlayerBottomView::class)
            pickOnBoundsProperty().set(false)
        }

    }
}
