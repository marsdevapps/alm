package com.marsdev.alm.player.models

import tornadofx.*

data class Track(val artist: Artist, val album: Album, val title: String, val year: Int,
                 val sampleRate: Int, val channels: Int, val bps: Int, val bitRate: Int, val startPosition: Long, val fileName: String, val directory: String)

class TrackModel : ItemViewModel<Track>() {
    val artist = bind(Track::artist)
    val album = bind(Track::album)
    val title = bind(Track::title)
    val year = bind(Track::year)
    val sampleRate = bind(Track::sampleRate)
    val channels = bind(Track::channels)
    val bps = bind(Track::bps)
    val bitRate = bind(Track::bitRate)
    val startPosition = bind(Track::startPosition)
    val fileName = bind(Track::fileName)
    val directory = bind(Track::directory)
}

