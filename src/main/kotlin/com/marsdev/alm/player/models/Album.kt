package com.marsdev.alm.player.models

import javafx.scene.image.Image

data class Album(val artist: Artist, val name: String, val tracks: HashSet<Track>, val image: Image) : Comparable<Album> {
    override fun compareTo(other: Album): Int {
        return name.compareTo(other.name, true)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Album

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = artist.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}
