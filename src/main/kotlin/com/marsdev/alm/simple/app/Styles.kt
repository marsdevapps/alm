package com.marsdev.alm.simple.app

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val sliderProgress by cssclass()
    }

    init {
        sliderProgress {
            slider {
                backgroundColor += Color.PURPLE
                backgroundInsets = multi(box(1.px), box(0.px), box(-1.px), box(0.px), box(0.px), box(1.px))
                backgroundRadius = multi(box(2.5.px), box(2.5.px), box(1.5.px))
                padding = box(0.20833.em)
            }


            progressBar {
                bar {
                    backgroundColor += Color.RED
                    backgroundRadius = multi(box(10.px))
                }
                track {
                    backgroundColor += Color.BLUE
                    backgroundRadius = multi(box(10.px))
                }
            }
        }

        progressBar {
            bar {
                //                backgroundColor += Color.GREEN
                backgroundRadius = multi(box(10.px))
            }
            track {
                backgroundColor += Color.TRANSPARENT
                backgroundRadius = multi(box(10.px))
            }
        }

        slider {
            backgroundColor += Color.TRANSPARENT
            backgroundInsets = multi(box(1.px), box(0.px), box(-1.px), box(0.px), box(0.px), box(1.px))
            backgroundRadius = multi(box(2.5.px), box(2.5.px), box(1.5.px))
            padding = box(0.20833.em)

        }

        track {
            backgroundColor += Color.TRANSPARENT
            backgroundInsets = multi(box(1.px), box(0.px), box(-1.px), box(0.px), box(0.px), box(1.px))
            backgroundRadius = multi(box(2.5.px), box(2.5.px), box(1.5.px))
            padding = box(0.20833.em)

        }
    }


}

fun fontAwesomeIcon(user: FontAwesomeIcon, size: Int): FontAwesomeIconView {
    val iconview = FontAwesomeIconView(user)
    iconview.setGlyphSize(size)
    return iconview
}


fun materialIcon(user: MaterialIcon, size: Int): MaterialIconView {
    val iconview = MaterialIconView(user)
    iconview.setGlyphSize(size)
    return iconview
}
