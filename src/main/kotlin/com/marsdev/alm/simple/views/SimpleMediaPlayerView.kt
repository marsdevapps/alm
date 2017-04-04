package com.marsdev.alm.simple.views

import com.marsdev.alm.simple.app.Styles
import com.marsdev.alm.simple.controllers.SimpleMediaPlayerController
import com.marsdev.alm.util.TimeFormatter
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.stage.FileChooser
import tornadofx.*

class SimpleMediaPlayerView : View("Simple Media Player") {
    val controller: SimpleMediaPlayerController by inject()

    override val root = borderpane {
        top {
            hbox {
                label(stringBinding(controller.duration) {
                    TimeFormatter.formatMilliseconds(value.toLong())
                })
                label(stringBinding(controller.maxDuration) {
                    TimeFormatter.formatMilliseconds(value.toLong())
                })
            }
        }
        center {
            hbox {
                button("Open File") {
                    action {
                        val result = chooseFile("Choose File", arrayOf(FileChooser.ExtensionFilter("*.FLAC", "*.flac"), FileChooser.ExtensionFilter("*.MP3", "*.mp3"), FileChooser.ExtensionFilter("*.WAV", "*.wav")))
                        if (result.isNotEmpty())
                            controller.setMedia(result.first().toString())
                    }
                }
                button("Play") {
                    action {
                        controller.play()
                    }
                }
                button("Pause") {
                    action {
                        controller.pause()
                    }
                }
                button("Stop") {
                    action {
                        controller.stop()
                    }
                }
            }
        }
        bottom {
            stackpane {
                //                addClass(Styles.sliderProgress)
                progressbar(0.0) {
                    progressProperty().bind(controller.progress)
                    maxWidth = 1.7976931348623157E308
                    prefWidth = -1.0

                    prefHeight = 10.0
                    minHeight = 10.0
                    style {
                        backgroundColor += Color.TRANSPARENT
                        backgroundRadius = multi(box(10.px))
                    }

                    stackpaneConstraints {
                        margin = insets(left = 6.0, right = 6.0)
                    }
                }
                slider {
                    addClass(Styles.sliderProgress)

                    addEventHandler(MouseEvent.MOUSE_RELEASED, {
                        controller.setPlayerSkipTo(value)
                    })

                    valueProperty().bindBidirectional(controller.duration)
                    maxProperty().bind(controller.maxDuration)
                    showTickLabelsProperty().set(false)
                    showTickMarksProperty().set(false)
                    snapToTicksProperty().set(false)
                    stackpaneConstraints {
                        margin = insets(0)
                    }
                }
            }

        }
    }

    override fun onUndock() {
        println("closed")
        super.onUndock()
    }
}
