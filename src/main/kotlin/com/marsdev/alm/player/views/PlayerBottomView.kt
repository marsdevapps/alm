package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.LibraryScope
import com.marsdev.alm.player.app.PlayerStyles
import com.marsdev.alm.player.controllers.Library
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.beans.property.SimpleObjectProperty
import javafx.embed.swing.SwingFXUtils
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.effect.BoxBlur
import javafx.scene.effect.GaussianBlur
import javafx.scene.image.Image
import javafx.scene.layout.Priority
import javafx.scene.shape.Rectangle
import javafx.util.Duration
import tornadofx.*

class PlayerBottomView : View("Bottom") {
    override val scope = super.scope as LibraryScope
    val library: Library by inject()
    val bottomIcon = MaterialIconView(MaterialIcon.FORMAT_LIST_BULLETED)

    val trackTitleStackPane = stackpane {
        minWidth = 220.0
        maxHeight = 120.0
        minHeight = 120.0
        prefHeight = 120.0
        addClass(PlayerStyles.bottomPlayerBarTrackTitleBackground)
        gridpaneColumnConstraints {
            hgrow = Priority.ALWAYS
            percentWidth = 27.0
        }
    }

    val trackDurationStackPane = stackpane {
        minWidth = 320.0
        prefHeight = 120.0
        minHeight = 120.0
        maxHeight = 120.0
        addClass(PlayerStyles.bottomPlayerBarTrackDuration)
        gridpaneColumnConstraints {
            hgrow = Priority.ALWAYS
            percentWidth = 23.0
        }
    }


    val trackDurationImageViewImage = SimpleObjectProperty<Image>(Image("images/empty.jpg"))
    val trackTitleImageViewImage = SimpleObjectProperty<Image>(Image("images/empty.jpg"))

    val trackDurationImageView = imageview {
        imageProperty().bind(trackDurationImageViewImage)
        effect = BoxBlur(10.0, 10.0, 3)
        fitHeight = 120.0
        fitWidth = 320.0
    }


    val trackTitleImageView = imageview {
        imageProperty().bind(trackTitleImageViewImage)
        effect = BoxBlur(10.0, 10.0, 3)
        fitHeight = 120.0
        fitWidth = 220.0
    }


    val trackTitleFiller = Rectangle(0.0, 0.0, 219.0, 119.0)
    val trackDurationFiller = Rectangle(0.0, 0.0, 219.0, 119.0)

    init {
        bottomIcon.glyphSize = 40.0
        bottomIcon.requestFocus()

        trackTitleFiller.fill = c("#083120")
        trackTitleFiller.effect = GaussianBlur()
        trackTitleFiller.opacity = 0.40

        trackDurationFiller.fill = c("#083120")
        trackDurationFiller.effect = GaussianBlur()
        trackDurationFiller.opacity = 0.40


        trackTitleStackPane.children.addAll(
                //                trackTitleImageView,
//                trackTitleFiller,
//                label(scope.currentTrack.title).setId(PlayerStyles.bottomPlayerBarTrackTitle)
        )

        trackDurationStackPane.children.addAll(
                //                trackDurationImageView,
//                trackDurationFiller,
//                label(stringBinding(scope.duration) {
//                    com.marsdev.alm.util.TimeFormatter.formatMilliseconds(value.toLong())
//                }).setId(PlayerStyles.bottomPlayerBarTrackDuration)
        )


        subscribe<AlbumsView.ScrollFinished> {
            timeline {
                keyframe(Duration.millis(50.0)) {
                    trackTitleStackPane.isVisible = false
                    trackDurationStackPane.isVisible = false
                    setOnFinished {
                        trackTitleImageViewImage.set((copyBackground(trackTitleStackPane)))
                        trackDurationImageViewImage.set((copyBackground(trackDurationStackPane)))
                    }
                }
                setOnFinished {
                    trackTitleStackPane.isVisible = true
                    trackDurationStackPane.isVisible = true
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
                    hgrow = Priority.ALWAYS
                    percentWidth = 6.0
                }
                alignment = Pos.CENTER_LEFT
            }.setId(PlayerStyles.bottomPlayerBarRightCorner)

            imageview {
                imageProperty().bind(scope.currentAlbum.image)
                fitHeight = 120.0
                fitWidth = 120.0
                gridpaneColumnConstraints {
                    hgrow = Priority.ALWAYS
                    percentWidth = 6.0
                }
            }.setId(PlayerStyles.bottomPlayerBar)

            add(trackTitleStackPane)
            add(trackDurationStackPane)

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
                    alignment = Pos.CENTER_RIGHT
                    hgrow = Priority.ALWAYS
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
            return SwingFXUtils.toFXImage(image, null)
        } catch (e: java.awt.AWTException) {
            println("The robot of doom strikes!")
            e.printStackTrace()
            return null
        }
    }
}
