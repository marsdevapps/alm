package com.marsdev.alm.player.service

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class AlbumServiceSpec : Spek({

    describe("Album Service tests") {
        val albumService = AlbumService()

        on("rescan") {
            val albums = albumService.getAlbums("D:\\temp\\music-small")

            it("should have files") {
                assert(albums.size > 0)
            }
        }
    }
})
