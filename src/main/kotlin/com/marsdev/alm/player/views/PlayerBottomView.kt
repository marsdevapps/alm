package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.LibraryScope
import com.marsdev.alm.player.app.PlayerStyles
import com.marsdev.alm.player.controllers.Library
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.beans.property.SimpleObjectProperty
import javafx.embed.swing.SwingFXUtils
import javafx.scene.Node
import javafx.scene.effect.BoxBlur
import javafx.scene.image.Image
import javafx.scene.layout.Priority
import javafx.util.Duration
import tornadofx.*
import java.io.File
import javax.imageio.ImageIO

class PlayerBottomView : View("Bottom") {
    override val scope = super.scope as LibraryScope
    val library: Library by inject()
    val bottomIcon = MaterialIconView(MaterialIcon.FORMAT_LIST_BULLETED)

    var count = 1

    init {
        bottomIcon.glyphSize = 40.0
        bottomIcon.requestFocus()
    }

    val trackTitleStackPane = stackpane {
        minWidth = 120.0
        maxHeight = 220.0
    }
    val trackTitleImageViewImage = SimpleObjectProperty<Image>(Image("ios-screenshot.png"))
    val trackTitleImageView = imageview {
        imageProperty().bind(trackTitleImageViewImage)
        effect = BoxBlur(10.0, 10.0, 1)
        fitHeight = 120.0
        fitWidth = 220.0
    }


    init {
        trackTitleStackPane.children.addAll(
                trackTitleImageView
//                label("hello there"),
//                label(scope.currentTrack.title).setId(PlayerStyles.bottomPlayerBarTrackTitle)
        )

        subscribe<AlbumsView.ScrollFinished> {
            timeline {
                keyframe(Duration.millis(50.0)) {
                    trackTitleStackPane.isVisible = false
                    setOnFinished {
                        trackTitleImageViewImage.set((copyBackground(trackTitleStackPane)))
                    }
                }
                setOnFinished {
                    trackTitleStackPane.isVisible = true
                }
            }.play()

        }
    }

    override val root = gridpane {
        addClass(PlayerStyles.bottomPlayerBar)
        maxHeight = 120.0
        prefHeight = 120.0
        minHeight = 120.0
        row {
            hbox {
                add(bottomIcon)
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
//                    percentWidth = 6.0
                }
            }.setId(PlayerStyles.bottomPlayerBarRightCorner)
            imageview {
                imageProperty().bind(scope.currentAlbum.image)
                fitHeight = 120.0
                fitWidth = 120.0
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
//                    percentWidth = 6.0
                }
            }.setId(PlayerStyles.bottomPlayerBar)
            button("hide").action {
                trackTitleStackPane.visibleProperty().set(false)
            }
            button("snap").action {
                trackTitleImageViewImage.set((copyBackground(trackTitleStackPane)!!))
            }
            button("show").action {
                trackTitleStackPane.visibleProperty().set(true)
            }
            add(trackTitleStackPane)

//            stackpane {
//                region {
//                    setId(PlayerStyles.bottomPlayerBarTrackTitleBackground)
//                    effect = BoxBlur(60.0, 60.0, 3)
//                }
//                label(scope.currentTrack.title).setId(PlayerStyles.bottomPlayerBarTrackTitle)
//                gridpaneColumnConstraints {
//                    hgrow = Priority.SOMETIMES
//                    percentWidth = 17.0
//                }
//            }
            label(stringBinding(scope.duration) {
                com.marsdev.alm.util.TimeFormatter.formatMilliseconds(value.toLong())
            }) {
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
//                    percentWidth = 23.0
                }
            }.setId(PlayerStyles.bottomPlayerBarTrackDuration)

            hbox {
                button(graphic = PlayerStyles.playIcon()) {
                    addClass(PlayerStyles.clearButton)
                    setOnAction { library.play() }
                }
                button(graphic = PlayerStyles.pauseIcon()) {
                    addClass(PlayerStyles.clearButton)
                    setOnAction { library.pause() }
                }

                button(graphic = PlayerStyles.stopIcon()) {
                    addClass(PlayerStyles.clearButton)
                    setOnAction { library.stop() }
                }
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
//                    percentWidth = 38.0
                }
            }.setId(PlayerStyles.bottomPlayerBarTrackControls)

        }

    }

    private fun copyBackground(node: Node): Image? {
        val bounds = node.boundsInLocal
        val screenBounds = node.localToScreen(bounds)
        val X = screenBounds.minX.toInt()
        val Y = screenBounds.minY.toInt()
        val W = screenBounds.width.toInt()
        val H = screenBounds.height.toInt()

        try {
            val robot = java.awt.Robot()
            val image = robot.createScreenCapture(java.awt.Rectangle(X, Y, W, H))
            ImageIO.write(image, "png", File("C:\\temp\\image-${count++}.png"))
            return SwingFXUtils.toFXImage(image, null)
        } catch (e: java.awt.AWTException) {
            println("The robot of doom strikes!")
            e.printStackTrace()
            return null
        }
    }
}
