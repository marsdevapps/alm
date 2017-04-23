package com.marsdev.alm.player.controllers

import com.marsdev.alm.player.app.LibraryScope
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
    override val scope = super.scope as LibraryScope
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
        println(equalizer.bandCount)
    }

    fun loadAlbums(): Boolean {
        if (scope.currentAlbums.size == 0) {
            scope.currentAlbums.addAll(albumService.getAlbums("D:\\temp\\music-small"))
        }
        return true
    }

    fun showAlbumsView() {
        mainContentView.getContentPane().children.setAll(albumsView.root)
    }

    fun setMedia(song: String) {
        fileName = song
        scope.progress.set(0.0)
        scope.duration.set(0.0)

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
    }

    fun pause() {
        executor.execute({ mediaPlayerComponent.mediaPlayer.pause() })
    }

    fun stop() {
        executor.execute({ mediaPlayerComponent.mediaPlayer.stop() })
    }

    override fun audioDeviceChanged(mediaPlayer: MediaPlayer?, audioDevice: String?) {
        println("audioDeviceChanged")
    }

    override fun volumeChanged(mediaPlayer: MediaPlayer?, volume: Float) {
        println("volumeChanged")
    }

    override fun mediaSubItemAdded(mediaPlayer: MediaPlayer?, subItem: libvlc_media_t?) {
        println("mediaSubItemAdded")
    }

    override fun endOfSubItems(mediaPlayer: MediaPlayer?) {
        println("endOfSubItems")
    }

    override fun scrambledChanged(mediaPlayer: MediaPlayer?, newScrambled: Int) {
        println("scrambledChanged")
    }

    override fun positionChanged(mediaPlayer: MediaPlayer?, newPosition: Float) {
        println("positionChanged")
    }

    override fun elementaryStreamSelected(mediaPlayer: MediaPlayer?, type: Int, id: Int) {
        println("elementaryStreamSelected")
    }

    override fun seekableChanged(mediaPlayer: MediaPlayer?, newSeekable: Int) {
        println("seekableChanged")
    }

    override fun stopped(mediaPlayer: MediaPlayer?) {
        println("stopped")
    }

    override fun snapshotTaken(mediaPlayer: MediaPlayer?, filename: String?) {
        println("snapshotTaken")
    }

    override fun muted(mediaPlayer: MediaPlayer?, muted: Boolean) {
        println("muted")
    }

    override fun forward(mediaPlayer: MediaPlayer?) {
        println("forward")
    }

    override fun pausableChanged(mediaPlayer: MediaPlayer?, newPausable: Int) {
        println("pausableChanged")
    }

    override fun playing(mediaPlayer: MediaPlayer?) {
        println("playing")
    }

    override fun titleChanged(mediaPlayer: MediaPlayer?, newTitle: Int) {
        println("titleChanged")
    }

    override fun corked(mediaPlayer: MediaPlayer?, corked: Boolean) {
        println("corked")
    }

    override fun chapterChanged(mediaPlayer: MediaPlayer?, newChapter: Int) {
        println("chapterChanged")
    }

    override fun subItemFinished(mediaPlayer: MediaPlayer?, subItemIndex: Int) {
        println("subItemFinished")
    }

    override fun elementaryStreamDeleted(mediaPlayer: MediaPlayer?, type: Int, id: Int) {
        println("elementaryStreamDeleted")
    }

    override fun mediaFreed(mediaPlayer: MediaPlayer?) {
        println("mediaFreed")
    }

    override fun opening(mediaPlayer: MediaPlayer?) {
        println("opening")
    }

    override fun mediaSubItemTreeAdded(mediaPlayer: MediaPlayer?, item: libvlc_media_t?) {
        println("mediaSubItemTreeAdded")
    }

    override fun backward(mediaPlayer: MediaPlayer?) {
        println("backward")
    }

    override fun elementaryStreamAdded(mediaPlayer: MediaPlayer?, type: Int, id: Int) {
        println("elementaryStreamAdded")
    }

    override fun videoOutput(mediaPlayer: MediaPlayer?, newCount: Int) {
        println("videoOutput")
    }

    override fun mediaParsedChanged(mediaPlayer: MediaPlayer?, newStatus: Int) {
        println("mediaParsedChanged")
    }

    override fun subItemPlayed(mediaPlayer: MediaPlayer?, subItemIndex: Int) {
        println("subItemPlayed")
    }

    override fun mediaParsedStatus(mediaPlayer: MediaPlayer?, newStatus: Int) {
        println("mediaParsedStatus")
    }

    override fun error(mediaPlayer: MediaPlayer?) {
        println("error")
    }

    override fun mediaChanged(mediaPlayer: MediaPlayer?, media: libvlc_media_t?, mrl: String?) {
        println("mediaChanged")
    }

    override fun finished(mediaPlayer: MediaPlayer?) {
        println("finished")
    }

    override fun mediaStateChanged(mediaPlayer: MediaPlayer?, newState: Int) {
        println("mediaStateChanged")
    }

    override fun paused(mediaPlayer: MediaPlayer?) {
        println("paused")
    }

    override fun timeChanged(mediaPlayer: MediaPlayer?, newTime: Long) {
        println("timeChanged")
        Platform.runLater({
            scope.duration.set(newTime.toDouble())
            scope.progress.set(newTime.toDouble() / scope.maxDuration.get())
        })
    }

    override fun buffering(mediaPlayer: MediaPlayer?, newCache: Float) {
        println("buffering")
    }

    override fun mediaDurationChanged(mediaPlayer: MediaPlayer?, newDuration: Long) {
        println("mediaDurationChanged")
    }

    override fun lengthChanged(mediaPlayer: MediaPlayer?, newLength: Long) {
        println("lengthChanged")
        Platform.runLater({ scope.maxDuration.set(newLength.toDouble()) })
    }

    override fun mediaMetaChanged(mediaPlayer: MediaPlayer?, metaType: Int) {
        println("mediaMetaChanged")
    }

    override fun newMedia(mediaPlayer: MediaPlayer?) {
        println("newMedia")
    }

}