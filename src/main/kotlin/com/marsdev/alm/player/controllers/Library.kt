package com.marsdev.alm.player.controllers

import com.marsdev.alm.player.models.LibraryModel
import com.marsdev.alm.player.service.AlbumService
import com.marsdev.alm.player.views.AlbumsView
import com.marsdev.alm.player.views.MainContentView
import javafx.application.Platform
import tornadofx.*
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent
import uk.co.caprica.vlcj.player.MediaPlayer
import uk.co.caprica.vlcj.player.MediaPlayerEventListener
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class Library : Controller(), MediaPlayerEventListener {
    val albumService = AlbumService()
    val albumsView = find(AlbumsView::class)
    val mainContentView: MainContentView by inject()

    private val mediaPlayerComponent = AudioMediaPlayerComponent()
    private val executor = ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, ArrayBlockingQueue<Runnable>(64))
    private val factory = mediaPlayerComponent.getMediaPlayerFactory()
    private val equalizer = factory.newEqualizer()
    private var fileName: String = ""

    init {
        executor.allowCoreThreadTimeOut(false)
        mediaPlayerComponent.mediaPlayer.addMediaPlayerEventListener(this)
    }

    fun loadAlbums(): Boolean {
        if (LibraryModel.currentAlbums.size == 0) {
            LibraryModel.currentAlbums.addAll(albumService.getAlbums("D:\\temp\\music-small"))
        }
        return true
    }

    fun showAlbumsView() {
        mainContentView.getContentPane().children.setAll(albumsView.root)
    }

    fun setMedia(song: String) {
        fileName = song
        LibraryModel.progress.set(0.0)
        LibraryModel.duration.set(0.0)

        mediaPlayerComponent.mediaPlayer.prepareMedia(fileName)
        mediaPlayerComponent.mediaPlayer.parseMedia()
        executor.execute({
            mediaPlayerComponent.mediaPlayer.playMedia(fileName)
        })
    }

    fun setPlayerSkipTo(value: Double) {
        mediaPlayerComponent.mediaPlayer.time = value.toLong()
    }

    fun close() {
        if (mediaPlayerComponent.mediaPlayer.isPlaying) {
            mediaPlayerComponent.mediaPlayer.stop()
            mediaPlayerComponent.release()
            executor.shutdown()
        }
    }

    fun play() {
        executor.execute({ mediaPlayerComponent.mediaPlayer.play() })
        LibraryModel.trackPlaying.set(true)
    }

    fun pause() {
        executor.execute({ mediaPlayerComponent.mediaPlayer.pause() })
        LibraryModel.trackPlaying.set(false)
    }

    fun stop() {
        executor.execute({ mediaPlayerComponent.mediaPlayer.stop() })
    }

    override fun audioDeviceChanged(mediaPlayer: MediaPlayer?, audioDevice: String?) {
    }

    override fun volumeChanged(mediaPlayer: MediaPlayer?, volume: Float) {
    }

    override fun mediaSubItemAdded(mediaPlayer: MediaPlayer?, subItem: libvlc_media_t?) {
    }

    override fun endOfSubItems(mediaPlayer: MediaPlayer?) {
    }

    override fun scrambledChanged(mediaPlayer: MediaPlayer?, newScrambled: Int) {
    }

    override fun positionChanged(mediaPlayer: MediaPlayer?, newPosition: Float) {
    }

    override fun elementaryStreamSelected(mediaPlayer: MediaPlayer?, type: Int, id: Int) {
    }

    override fun seekableChanged(mediaPlayer: MediaPlayer?, newSeekable: Int) {
    }

    override fun stopped(mediaPlayer: MediaPlayer?) {
    }

    override fun snapshotTaken(mediaPlayer: MediaPlayer?, filename: String?) {
    }

    override fun muted(mediaPlayer: MediaPlayer?, muted: Boolean) {
    }

    override fun forward(mediaPlayer: MediaPlayer?) {
    }

    override fun pausableChanged(mediaPlayer: MediaPlayer?, newPausable: Int) {
    }

    override fun playing(mediaPlayer: MediaPlayer?) {
    }

    override fun titleChanged(mediaPlayer: MediaPlayer?, newTitle: Int) {
    }

    override fun corked(mediaPlayer: MediaPlayer?, corked: Boolean) {
    }

    override fun chapterChanged(mediaPlayer: MediaPlayer?, newChapter: Int) {
    }

    override fun subItemFinished(mediaPlayer: MediaPlayer?, subItemIndex: Int) {
    }

    override fun elementaryStreamDeleted(mediaPlayer: MediaPlayer?, type: Int, id: Int) {
    }

    override fun mediaFreed(mediaPlayer: MediaPlayer?) {
    }

    override fun opening(mediaPlayer: MediaPlayer?) {
    }

    override fun mediaSubItemTreeAdded(mediaPlayer: MediaPlayer?, item: libvlc_media_t?) {
    }

    override fun backward(mediaPlayer: MediaPlayer?) {
    }

    override fun elementaryStreamAdded(mediaPlayer: MediaPlayer?, type: Int, id: Int) {
    }

    override fun videoOutput(mediaPlayer: MediaPlayer?, newCount: Int) {
    }

    override fun mediaParsedChanged(mediaPlayer: MediaPlayer?, newStatus: Int) {
    }

    override fun subItemPlayed(mediaPlayer: MediaPlayer?, subItemIndex: Int) {
    }

    override fun mediaParsedStatus(mediaPlayer: MediaPlayer?, newStatus: Int) {
    }

    override fun error(mediaPlayer: MediaPlayer?) {
    }

    override fun mediaChanged(mediaPlayer: MediaPlayer?, media: libvlc_media_t?, mrl: String?) {
    }

    override fun finished(mediaPlayer: MediaPlayer?) {
    }

    override fun mediaStateChanged(mediaPlayer: MediaPlayer?, newState: Int) {
    }

    override fun paused(mediaPlayer: MediaPlayer?) {
    }

    override fun timeChanged(mediaPlayer: MediaPlayer?, newTime: Long) {
        Platform.runLater({
            LibraryModel.duration.set(newTime.toDouble())
            LibraryModel.progress.set(newTime.toDouble() / LibraryModel.maxDuration.get())
        })
    }

    override fun buffering(mediaPlayer: MediaPlayer?, newCache: Float) {
    }

    override fun mediaDurationChanged(mediaPlayer: MediaPlayer?, newDuration: Long) {
    }

    override fun lengthChanged(mediaPlayer: MediaPlayer?, newLength: Long) {
        Platform.runLater({ LibraryModel.maxDuration.set(newLength.toDouble()) })
    }

    override fun mediaMetaChanged(mediaPlayer: MediaPlayer?, metaType: Int) {
    }

    override fun newMedia(mediaPlayer: MediaPlayer?) {
    }

}