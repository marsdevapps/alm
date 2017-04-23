package com.marsdev.alm.player.app

import javafx.geometry.Pos
import javafx.scene.effect.DropShadow
import javafx.scene.effect.GaussianBlur
import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import javafx.scene.text.Font
import tornadofx.*

class PlayerStyles : Stylesheet() {

    companion object {
        val backGroundColor = c("#292E33", 1.0)
        val greenTextColor = c("#40F1A6", 1.0)
        val whiteTextColor = c("#F4F5F9", 1.0)
        val altWhiteTextColor = c("#DDDFE6", 1.0)

        val title by cssid()
        val subTitle by cssid()
        val contentPane by cssclass()
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

        val sideBar by cssid()
        val bottomPlayerBar by cssid()

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


    }

    init {

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
            backgroundColor += backGroundColor
        }

        innerContentPane {
            padding = box(40.0.px)
//            spacing = 10.px
//            backgroundColor += Color.RED
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
            backgroundColor += greenTextColor
            padding = box(40.px)
            spacing = 10.px
            alignment = Pos.CENTER_LEFT
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

        albumDataGrid {
            scrollBar contains star {
                backgroundColor += backGroundColor
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
            backgroundColor += backGroundColor
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

    }
}