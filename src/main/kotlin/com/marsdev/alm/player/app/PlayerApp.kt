package com.marsdev.alm.player.app

import com.marsdev.alm.player.views.FirstLaunchView
import tornadofx.*
import uk.co.caprica.vlcj.discovery.NativeDiscovery

class PlayerApp : App(FirstLaunchView::class, PlayerStyles::class) {
    init {
        NativeDiscovery().discover()
        reloadStylesheetsOnFocus()
    }

    override fun stop() {
        super.stop()
        System.exit(0)
    }
}