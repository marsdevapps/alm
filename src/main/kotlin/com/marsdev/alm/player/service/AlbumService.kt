package com.marsdev.alm.player.service

import com.marsdev.alm.player.models.Album
import com.marsdev.alm.player.models.Artist
import com.marsdev.alm.player.models.Track
import javafx.scene.image.Image
import org.jaudiotagger.audio.flac.FlacFileReader
import java.io.ByteArrayInputStream
import java.io.File
import java.util.*

class AlbumService {

    fun getAlbums(musicDirectory: String): Set<Album> {
        val albums = HashSet<Album>()


        return albums
    }

    fun rescan(musicDirectory: String): Set<Track> {
        val tracks = HashSet<Track>()

        val queue = LinkedList<File>()
        val f = File(musicDirectory)
        if (f.exists())
            queue.add(f)

        while (!queue.isEmpty()) {
            try {
                val file = queue.pop()
                if (file.isDirectory) {
//                    queue.addAll(0, Arrays.asList(*file.listFiles(FileFilter { f -> f.extension.equals(".flac", true) })))
                    queue.addAll(0, Arrays.asList(*file.listFiles()))
                } else {
                    if (file.extension.equals("flac", true)) {
                        val reader = FlacFileReader()
                        val audioFile = reader.read(file)
                        val tag = audioFile.tag
                        audioFile.tag.firstArtwork.binaryData

                        val artist = Artist(tag.getFields("ARTIST")[0].toString())
                        val album = Album(artist, tag.getFields("ALBUM")[0].toString(), HashSet<Track>(), Image(ByteArrayInputStream(audioFile.tag.firstArtwork.binaryData)))
                        val newTrack = Track(
                                artist,
                                album,
                                tag.getFields("TITLE")[0].toString(),
                                tag.getFields("DATE")[0].toString().substring(0, 3).toInt(),
                                0,
                                0,
                                0,
                                0,
                                0L,
                                file.name,
                                file.parentFile.path
                        )
                        tracks.add(newTrack)
                    }
//                    var trackData = TrackData(file.toURI(), 0)
//                    val track = trackDatas.get(trackData)
//                    if (track != null) {
//                        if (track!!.getTrackData().getLastModified() !== file.lastModified()) {
//                            track!!.getTrackData().clearTags()
//                            TrackIO.getAudioFileReader(file.name).reload(track)
//                        }
//                        tracks.add(track)
//                    } else {
//                        temp.clear()
//                        TrackIO.getAudioFileReader(file.name).read(file, temp)
//                        for (newTrack in temp) {
//                            trackData = newTrack.getTrackData()
//                            if (trackDatas.containsKey(trackData)) {
//                                 it must be the cue file, so  merge the track data
//                                trackData.merge(newTrack.getTrackData())
//                            }
//                            tracks.add(newTrack)
//                        }
//                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return tracks
    }
}