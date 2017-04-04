package com.marsdev.alm.player.app

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

        val openSansRegular: Font = PlayerStyles::class.java.getResourceAsStream("/fonts/Open_Sans/OpenSans-Regular.ttf").use {
            Font.loadFont(it, 20.0)
        }
    }

    init {
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