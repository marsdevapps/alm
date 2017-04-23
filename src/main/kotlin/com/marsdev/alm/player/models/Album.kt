package com.marsdev.alm.player.models

import javafx.scene.image.Image

data class Album(val artist: Artist, val name: String, val tracks: Set<Track>, val image: Image)
