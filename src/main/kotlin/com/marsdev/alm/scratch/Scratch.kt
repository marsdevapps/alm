package com.marsdev.alm.scratch

import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent
import uk.co.caprica.vlcj.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.MediaPlayer
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.MediaPlayerFactory


fun main(args: Array<String>) {
    if (NativeDiscovery().discover()) {
        val simplePlayer = SimplePlayer()
        simplePlayer.start("D:\\music\\Depeche Mode\\The Singles 86»98, Disc 2\\09 Depeche Mode - Only When I Lose Myself.flac")
        val factory = MediaPlayerFactory()
        val mediaMeta = factory.getMediaMeta("D:\\music\\Depeche Mode\\The Singles 86»98, Disc 2\\09 Depeche Mode - Only When I Lose Myself.flac", true)
        println(mediaMeta)
    }
}

class SimplePlayer {
    val mediaPlayerComponent = AudioMediaPlayerComponent()


    init {
        mediaPlayerComponent.mediaPlayer.addMediaPlayerEventListener(object : MediaPlayerEventAdapter() {
            override fun stopped(mediaPlayer: MediaPlayer?) {
                exit(0)
            }

            override fun finished(mediaPlayer: MediaPlayer?) {
                exit(0)
            }

            override fun error(mediaPlayer: MediaPlayer?) {
                exit(1)
            }
        })
    }

    fun start(mrl: String) {
        mediaPlayerComponent.mediaPlayer.playMedia(mrl)
    }

    private fun exit(result: Int) {
        mediaPlayerComponent.release()
        System.exit(result)
    }
}

