package com.marsdev.alm.player.app

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.geometry.Pos
import javafx.scene.effect.DropShadow
import javafx.scene.effect.GaussianBlur
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import javafx.scene.text.Font
import tornadofx.*
import java.net.URI

class PlayerStyles : Stylesheet() {

    companion object {
        val backGroundColor = c("#292E33", 1.0)
        val greenTextColor = c("#40F1A6", 1.0)
        val whiteTextColor = c("#F4F5F9", 1.0)
        val altWhiteTextColor = c("#DDDFE6", 1.0)

        val title by cssid()
        val subTitle by cssid()
        val contentPane by cssclass()
        val stackContentPane by cssclass()
        val innerContentPane by cssid()
        val firstLaunchView by cssclass()
        val firstLaunchViewTitle by cssid()
        val firstLaunchViewSubTitle by cssid()
        val firstLaunchViewOverlay by cssid()

        val clearButton by cssclass()

        // icons
        val angleLeft by cssclass()
        val angleRight by cssclass()
        val arrowUp by cssclass()

        val playButton by cssclass()
        val previousButton by cssclass()
        val nextButton by cssclass()
        val volumeButton by cssclass()
        val shuffleButton by cssclass()

        val sideBar by cssid()

        val bottomPlayerBar by cssclass()
        val bottomPlayerBarRightCorner by cssid()
        val bottomPlayerBarTrackTitle by cssid()
        val bottomPlayerBarTrackTitleBackground by cssid()
        val bottomPlayerBarTrackDuration by cssid()
        val bottomPlayerBarTrackDurationLabel by cssid()
        val bottomPlayerBarTrackControls by cssid()

        val openSansRegular: Font = loadFont("/fonts/Open_Sans/OpenSans-Regular.ttf", 20.0) ?: Font.font(20.0)

        val openSansLight: Font = loadFont("/fonts/Open_Sans/OpenSans-Light.ttf", 20.0) ?: Font.font(20.0)

        val listMenu by cssclass()
        val listItem by cssclass()
        val active by csspseudoclass()

        val albumView by cssclass()

        val albumDataGrid by cssclass()
        val albumCell by cssclass()
        val albumTitle by cssid()
        val albumArtist by cssid()

        val playIcon by cssclass()
        fun playIcon() = FontAwesomeIconView(FontAwesomeIcon.PLAY).apply {
            glyphSize = 54
            addClass(playIcon)
        }

        val pauseIcon by cssclass()
        fun pauseIcon() = FontAwesomeIconView(FontAwesomeIcon.PAUSE).apply {
            glyphSize = 54
            addClass(pauseIcon)
        }

        val stopIcon by cssclass()
        fun stopIcon() = FontAwesomeIconView(FontAwesomeIcon.STOP).apply {
            glyphSize = 54
            addClass(stopIcon)
        }

        val cleanButton by cssid()
        val albumTrackList by cssclass()

    }

    init {

        albumTrackList contains star {
            backgroundColor += Color.TRANSPARENT
            font = openSansLight
            textFill = Color.WHITE
        }

        albumTrackList {
            backgroundColor += Color.TRANSPARENT

            font = openSansLight
            textFill = Color.WHITE
            listCell {
                backgroundColor += Color.TRANSPARENT
            }
        }

        cleanButton {
            backgroundColor += Color.TRANSPARENT
            borderColor += box(Color.TRANSPARENT)
            fill = Color.RED
        }

        firstLaunchView {
        }

        firstLaunchViewTitle {
            font = openSansLight
            textFill = Color.WHITE
            fontSize = 58.px
        }

        firstLaunchViewSubTitle {
            font = openSansLight
            textFill = Color.WHITE
            fontSize = 24.px
        }


        firstLaunchViewOverlay {
            backgroundColor += LinearGradient(0.533, 1.0, 0.53, 0.041, true, CycleMethod.NO_CYCLE, Stop(0.0, c("#2d3e40", 1.0)), Stop(1.0, c("#185c62", 0.576)))
            opacity = 1.00
        }

        contentPane {
            backgroundImage += URI("/images/background_image.png")
//            backgroundColor += backGroundColor
        }

        stackContentPane {
            backgroundColor += Color.TRANSPARENT
        }


        innerContentPane {
            padding = box(40.0.px)
        }

        title {
            textFill = greenTextColor
            font = openSansRegular
        }

        subTitle {
            fontSize = 20.px
            textFill = whiteTextColor
            font = openSansLight
        }



        label {
            textFill = altWhiteTextColor
        }
        sideBar {
            backgroundColor += LinearGradient(0.5, 0.5, 0.5, 1.1, true, CycleMethod.NO_CYCLE, Stop(0.0, c("#292e33", 1.0)), Stop(1.0, c("#1a1b1c", 1.0)))
            effect = DropShadow().also { GaussianBlur() }
            padding = box(20.px)
            spacing = 10.px
        }

        bottomPlayerBar {
            //            padding = box(40.px)
//            spacing = 10.px
            alignment = Pos.CENTER_LEFT
            borderColor += box(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK)
            borderStyle += BorderStrokeStyle.SOLID
            borderWidth += box(1.px)
            effect = DropShadow().also { GaussianBlur() }
        }
        bottomPlayerBarRightCorner {
            backgroundColor += greenTextColor

        }
        bottomPlayerBarTrackTitleBackground {
            backgroundColor += c("#083120")
            font = openSansLight
            textFill = Color.WHITE
            fontSize = 18.px
            opacity = 1.0
        }
        bottomPlayerBarTrackTitle {
            backgroundColor += Color.TRANSPARENT
//            effect = GaussianBlur()
            font = openSansLight
            textFill = Color.WHITE
            fontSize = 18.px
        }

        bottomPlayerBarTrackDurationLabel {
            padding = box(20.px)
            font = openSansRegular
            textFill = Color.WHITE
            fontSize = 18.px
        }

        bottomPlayerBarTrackDuration {
            backgroundColor += Color.TRANSPARENT
            effect = GaussianBlur()
            opacity = 0.7
        }

        bottomPlayerBarTrackControls {
            backgroundColor += greenTextColor
            alignment = Pos.CENTER
            spacing = 80.px

        }

        clearButton {
            backgroundColor += Color.TRANSPARENT
        }


        listItem contains star {
            fill = Color.WHITE
            and(active) {
                fill = greenTextColor
            }
            and(hover) {
                fill = greenTextColor
            }
            alignment = Pos.BASELINE_CENTER


        }
        listItem {
            padding = box(20.px)
            backgroundColor += Color.TRANSPARENT
            alignment = Pos.BASELINE_CENTER
        }

        albumDataGrid contains star {
            backgroundColor += Color.TRANSPARENT
        }
        albumDataGrid {
            scrollBar contains star {
                backgroundColor += Color.TRANSPARENT
//                backgroundColor += backGroundColor
            }

            scrollBar {
                decrementArrow {
                    padding = box(0.px)
                    shape = ""
                }

                incrementArrow {
                    padding = box(0.px)
                    shape = ""
                }
            }
        }

        albumCell {
            backgroundColor += Color.TRANSPARENT
            alignment = Pos.CENTER
            textFill = whiteTextColor
            spacing = 2.px
        }

        albumTitle {
            font = openSansRegular
            fontSize = 16.px
        }

        albumArtist {
            font = openSansRegular
            fontSize = 12.px
        }

        albumView {
            padding = box(40.0.px)
        }

        playButton {
            backgroundColor += Color.BLACK
            prefHeight = 23.3.px
            prefWidth = 20.px
            shape = "M-2059,25V0h7V25Zm-13,0V0h7V25Z"
            and(selected) {
                shape = "M8,5V76.379L64.083,40.689Z"
            }
        }

        previousButton {
            backgroundColor += Color.BLACK
            prefHeight = 23.3.px
            prefWidth = 20.px
            shape = "M-3269.667-1180.333v11.666H-3273V-1192h3.334v11.666L-3253-1192v23.333Z"
        }

        nextButton {
            backgroundColor += Color.BLACK
            prefHeight = 23.3.px
            prefWidth = 20.px
            shape = "M-3256.334-1180.333v11.666H-3253V-1192h-3.333v11.666L-3273-1192v23.333Z"
        }

        volumeButton {
            backgroundColor += Color.BLACK
            prefHeight = 23.3.px
            prefWidth = 20.px
            shape = "M-3706.883-1177.136l-8.886-5.477a.909.909,0,0,1-.433-.776V-1196.4a.912.912,0,0,1,.421-.769l8.885-5.684a.911.911,0,0,1,.929-.031.909.909,0,0,1,.476.8v24.176a.913.913,0,0,1-.47.8.9.9,0,0,1-.444.116A.911.911,0,0,1-3706.883-1177.136Zm-7.494-18.769v12.007l7.062,4.352v-20.875Zm-6.623,9.989v-7.775a3.34,3.34,0,0,1,3.554-3.064.913.913,0,0,1,.912.912.913.913,0,0,1-.912.912c-.938,0-1.729.568-1.729,1.239v7.775c0,.671.792,1.239,1.729,1.239a.913.913,0,0,1,.912.912.913.913,0,0,1-.912.912A3.341,3.341,0,0,1-3721-1185.916Zm22.657,1.33a1.315,1.315,0,0,1-.7-1.492c1.888-3.641.236-7.953.23-7.99a1.348,1.348,0,0,1,.665-1.526c.489-.165.982.242,1.106.915a34.518,34.518,0,0,1-.215,9.122c-.091.593-.469,1-.891,1A.68.68,0,0,1-3698.343-1184.586Zm-3.778-1.352a.914.914,0,0,1-.7-1.081,14.786,14.786,0,0,0,.008-5.928.911.911,0,0,1,.665-1.1.912.912,0,0,1,1.1.663,16.349,16.349,0,0,1,.008,6.747.911.911,0,0,1-.891.724A.95.95,0,0,1-3702.121-1185.938Z"
        }



        shuffleButton {
            backgroundColor += Color.BLACK
            prefHeight = 23.3.px
            prefWidth = 20.px
            shape = "M12.238,10.462,5.762,4,4,5.762l6.463,6.463ZM17.125,4l2.55,2.55L4,22.237,5.762,24,21.45,8.325,24,10.875V4Zm.412,11.762-1.762,1.763,3.912,3.912L17.125,24H24V17.125l-2.55,2.55-3.912-3.913Z"
        }
    }

}

fun fontAwesomeIcon(icon: FontAwesomeIcon, size: Int) = FontAwesomeIconView(icon).apply { glyphSize = size }
fun materialIcon(icon: MaterialIcon, size: Int) = MaterialIconView(icon).apply { glyphSize = size }