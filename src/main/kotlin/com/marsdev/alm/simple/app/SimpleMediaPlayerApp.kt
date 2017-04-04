package com.marsdev.alm.simple.app

import com.marsdev.alm.simple.controllers.SimpleMediaPlayerController
import com.marsdev.alm.simple.views.SimpleMediaPlayerView
import tornadofx.*
import uk.co.caprica.vlcj.discovery.NativeDiscovery

class SimpleMediaPlayerApp : App(SimpleMediaPlayerView::class, Styles::class) {
    init {
        NativeDiscovery().discover()
        reloadStylesheetsOnFocus()
    }

    override fun stop() {

        find<SimpleMediaPlayerController>().close()
        super.stop()
        System.exit(0)
    }
}

