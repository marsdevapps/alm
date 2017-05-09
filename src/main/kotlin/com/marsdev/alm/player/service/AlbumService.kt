package com.marsdev.alm.player.service

import com.marsdev.alm.player.models.Album
import com.marsdev.alm.player.models.Artist
import com.marsdev.alm.player.models.Track
import javafx.scene.image.Image
import org.jaudiotagger.audio.flac.FlacFileReader
import java.io.ByteArrayInputStream
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class AlbumService {

    companion object {
        const val ARTIST = "ARTIST"
        const val ALBUM_ARTIST = "ALBUM_ARTIST"
        const val TITLE = "TITLE"
        const val ALBUM = "ALBUM"
        const val YEAR = "YEAR"
        const val GENRE = "GENRE"
        const val TRACK = "TRACK"
        const val TRACK_TOTAL = "TRACK_TOTAL"
        const val DISC_NO = "DISC_NO"
        const val DISC_TOTAL = "DISC_TOTAL"
        const val RECORD_LABEL = "RECORD_LABEL"
        const val CATALOG_NO = "CATALOG_NO"
        const val COMMENT = "COMMENT"
        const val RATING = "RATING"
    }

    val albums = HashSet<Album>()

    fun getAlbums(musicDirectory: String): Set<Album> {
        this.albums.clear()
        rescan(musicDirectory)
        return this.albums
    }

    private fun rescan(musicDirectory: String): Set<Track> {
        val tracks = HashSet<Track>()
        val albums = HashSet<Album>()
        val artists = ArrayList<Artist>()

        val queue = LinkedList<File>()
        val f = File(musicDirectory)
        if (f.exists())
            queue.add(f)

        while (!queue.isEmpty()) {
            try {
                val file = queue.pop()
                if (file.isDirectory) {
                    queue.addAll(0, Arrays.asList(*file.listFiles()))
                } else {
                    if (file.extension.equals("flac", true)) {
                        val reader = FlacFileReader()
                        val audioFile = reader.read(file)
                        val tag = audioFile.tag
                        audioFile.tag.firstArtwork.binaryData

                        val artistName = tag.getFields(ARTIST)[0].toString()
                        val templateArtist = Artist(artistName)
                        if (!artists.contains(templateArtist)) {
                            artists.add(templateArtist)
                        }

                        val albumTemplate = Album()
                        albumTemplate.artist = artists[artists.indexOf(templateArtist)]
                        albumTemplate.name = tag.getFields(ALBUM)[0].toString()
                        albumTemplate.image = Image(ByteArrayInputStream(audioFile.tag.firstArtwork.binaryData))

                        if (!albums.contains(albumTemplate)) {
                            albums.add(albumTemplate)
                        }

                        val currentAlbum = albums.filter { it.name.equals(tag.getFields(ALBUM)[0].toString()) }.first()


                        val dateString = if (tag.getFields("DATE").size > 0) {
                            tag.getFields("DATE")[0].toString()
                        } else {
                            ""
                        }
                        var trackYear = 0
                        if (!dateString.equals("") && dateString.length > 4) {
                            trackYear = dateString.substring(0, 3).toInt()
                        }

                        val newTrack = Track(
                                templateArtist,
                                currentAlbum,
                                tag.getFields(TITLE)[0].toString(),
                                trackYear,
                                0,
                                0,
                                0,
                                0,
                                0L,
                                file.name,
                                file.parentFile.path
                        )
                        currentAlbum.tracks.add(newTrack)
                        tracks.add(newTrack)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            this.albums.addAll(albums)

        }
        return tracks
    }
}