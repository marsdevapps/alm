package com.marsdev.alm.player.app

import com.marsdev.alm.player.views.PlayerView
import tornadofx.*

class PlayerApp : App(PlayerView::class, PlayerStyles::class) {
    init {
        reloadStylesheetsOnFocus()
        reloadViewsOnFocus()
    }
}