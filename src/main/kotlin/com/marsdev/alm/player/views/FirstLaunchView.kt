package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.PlayerStyles
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class FirstLaunchView : View("Kida Music") {

    override val root = borderpane {
        prefWidth = 1920.0
        prefHeight = 1080.0
        addClass(PlayerStyles.firstLaunchView)
        center {
            stackpane {
                imageview("images/daft-punk-cover.png") {
                    fitWidthProperty().bind(primaryStage.widthProperty())
                    fitHeightProperty().bind(primaryStage.heightProperty())
                    opacity = 1.0
                }

                pane {
                    prefWidthProperty().bind(primaryStage.widthProperty())
                    prefHeightProperty().bind(primaryStage.heightProperty())
                }.setId(PlayerStyles.firstLaunchViewOverlay)

                vbox {
                    alignment = Pos.CENTER
                    svgpath("M130.6,141.044H90.385a1.318,1.318,0,0,1-1.055-.523L49.5,89.425l28.234-16.3a.169.169,0,0,0,.048.066l53.856,65.661A1.345,1.345,0,0,1,130.6,141.044ZM77.788,71.468a.943.943,0,0,0-.171.275L50.969,56.352,86.48,12.325a1.329,1.329,0,0,1,1.045-.494h38.247a1.34,1.34,0,0,1,1.017,2.214Zm-33.1,69.576H13.168a1.338,1.338,0,0,1-1.339-1.34V111.173l34.2-19.743V139.7A1.338,1.338,0,0,1,44.689,141.044ZM11.829,13.17a1.338,1.338,0,0,1,1.339-1.339H44.376a1.338,1.338,0,0,1,1.339,1.339l.142,40.236L11.829,33.759Z") {
                        stroke = Color.WHITE
                        fill = Color.WHITE
                    }
                    label("Welcome to Kida Music").setId(PlayerStyles.firstLaunchViewTitle)
                    label("Stream millions of songs from your device").setId(PlayerStyles.firstLaunchViewSubTitle)
                }
            }.setOnMouseClicked {
                replaceWith(PlayerView::class, ViewTransition.FadeThrough(1.0.seconds, Color.BLACK))
            }
        }
    }
}
