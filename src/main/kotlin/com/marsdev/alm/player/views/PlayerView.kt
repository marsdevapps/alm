package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.PlayerStyles
import tornadofx.*

class PlayerView : View("ALM Player") {

    override val root = borderpane {
        addClass(PlayerStyles.contentPane)
        left(PlayerSideNavView::class)
        center {
            borderpane {
                top(PlayerTopView::class)
                center(AlbumsView::class)
            }
        }
        bottom(PlayerBottomView::class)
    }
}
