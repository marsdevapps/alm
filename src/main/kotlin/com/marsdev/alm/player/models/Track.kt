package com.marsdev.alm.player.models

data class Track(val artist: Artist, val album: Album, val title: String, val year: Int,
                 val sampleRate: Int, val channels: Int, val bps: Int, val bitRate: Int, val startPosition: Long, val fileName: String, val directory: String)
