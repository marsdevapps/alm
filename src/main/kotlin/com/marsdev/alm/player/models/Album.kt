package com.marsdev.alm.player.models

data class Album(val artist: Artist, val name: String, val tracks: Set<Track>)
