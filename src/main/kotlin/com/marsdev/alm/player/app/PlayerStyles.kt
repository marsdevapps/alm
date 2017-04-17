package com.marsdev.alm.player.app

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
        val firstLaunchView by cssclass()
        val firstLaunchViewTitle by cssid()
        val firstLaunchViewSubTitle by cssid()
        val firstLaunchViewOverlay by cssid()

        val openSansRegular: Font = PlayerStyles::class.java.getResourceAsStream("/fonts/Open_Sans/OpenSans-Regular.ttf").use {
            Font.loadFont(it, 20.0)
        }

        val openSansLight: Font = PlayerStyles::class.java.getResourceAsStream("/fonts/Open_Sans/OpenSans-Light.ttf").use {
            Font.loadFont(it, 20.0)
        }

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

        title {
            textFill = greenTextColor
            font = openSansRegular
        }

        subTitle {
            fontSize = 20.px
            textFill = whiteTextColor
            fontFamily = "Open Sans"
        }

        label {
            textFill = altWhiteTextColor
        }

    }
}