package com.marsdev.alm.player.views

import com.marsdev.alm.player.app.LibraryScope
import com.marsdev.alm.player.app.PlayerStyles
import com.marsdev.alm.player.controllers.Library
import com.marsdev.alm.player.models.AlbumModel
import com.marsdev.alm.player.models.TrackModel
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.application.Platform
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import javafx.scene.SnapshotParameters
import javafx.scene.effect.BoxBlur
import javafx.scene.image.Image
import javafx.scene.image.WritableImage
import javafx.scene.layout.Priority
import tornadofx.*

// todo clean up all styling and move to style sheet
// todo add frosted pane to track progress area
class PlayerBottomView : View("Bottom") {
    override val scope = super.scope as LibraryScope
    val library: Library by inject()
    val currentAlbum: AlbumModel by inject()
    val currentTrack: TrackModel by inject()

    val bottomIcon = MaterialIconView(MaterialIcon.FORMAT_LIST_BULLETED)
    val trackTitleImageViewImage = SimpleObjectProperty<Image>()

    val bottomHeight = 100.0

    init {
        bottomIcon.glyphSize = 40.0
        subscribe<AlbumsView.AlbumsScroll> {

            Platform.runLater {
                val node = find(AlbumsView::class).root
                val snapParm = SnapshotParameters()
                snapParm.fill = PlayerStyles.backGroundColor
                val image = node.snapshot(snapParm, null)
                if (image.requestedHeight > 1 && image.requestedWidth > 1) {
                    val bounds = root.scene.lookup("#bottom-player-bar-track-duration").boundsInLocal
                    val screenBounds = root.scene.lookup("#bottom-player-bar-track-duration").localToScene(bounds)
                    val X = screenBounds.minX.toInt()
                    val Y = screenBounds.minY.toInt()
                    val W = screenBounds.width.toInt()
                    val H = screenBounds.height.toInt()
                    // todo remove out hard coding...
                    val t = Y - H + 30

                    // todo remove out hard coding...
                    val imageCrop = WritableImage(image.pixelReader, 120, t, W, H)
                    trackTitleImageViewImage.set(imageCrop)
//                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", File("C:\\temp\\image.png"))
//                ImageIO.write(SwingFXUtils.fromFXImage(imageCrop, null), "png", File("C:\\temp\\imageCrop.png"))

                }
            }


        }

    }

    override val root = gridpane {
        addClass(PlayerStyles.bottomPlayerBar)
        maxHeight = bottomHeight
        prefHeight = bottomHeight
        minHeight = bottomHeight
        row {
            hbox {
                prefWidth = 120.0
                alignment = Pos.CENTER
                add(bottomIcon)
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                }
            }.setId(PlayerStyles.bottomPlayerBarRightCorner)

            imageview {
                imageProperty().bind(currentAlbum.image)
                fitHeight = bottomHeight
                fitWidth = 120.0
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 6.0
                }
            }.setId(PlayerStyles.bottomPlayerBar)

            stackpane {
                prefHeight = bottomHeight
                prefWidth = 767.0
                pane {
                    imageview {
                        fitWidthProperty().bind(this@stackpane.widthProperty())
                        fitHeightProperty().bind(this@stackpane.heightProperty())
                        effect = BoxBlur(2.0, 2.0, 3)
                        imageProperty().bind(trackTitleImageViewImage)
                    }


                }
                progressbar(scope.progress) {
                    hgrow = Priority.ALWAYS
                    addClass(PlayerStyles.progressBar)
                }
                hbox {
                    label(currentTrack.title) {
                        setId(PlayerStyles.bottomPlayerBarTrackTitleLabel)
                        hgrow = Priority.ALWAYS
                    }

                    label(stringBinding(scope.duration) {
                        com.marsdev.alm.util.TimeFormatter.formatMilliseconds(value.toLong())
                    }) {
                        setId(PlayerStyles.bottomPlayerBarTrackDurationLabel)
                        hgrow = Priority.ALWAYS
                    }
                }
                gridpaneColumnConstraints {
                    hgrow = Priority.SOMETIMES
                    percentWidth = 40.0
                }
            }.setId(PlayerStyles.bottomPlayerBarTrackDuration)
            hbox {
                button {
                    addClass(PlayerStyles.previousButton)
                    isPickOnBounds = true
                }

                togglebutton {
                    selectedProperty().bindBidirectional(scope.trackPlaying)
                    addClass(PlayerStyles.playButton)
                    isPickOnBounds = true
                    setOnAction {
                        if (isSelected) {
                            library.play()
                        } else {
                            library.pause()
                        }
                    }
                }

                button {
                    addClass(PlayerStyles.nextButton)
                    isPickOnBounds = true
                }

                button {
                    addClass(PlayerStyles.volumeButton)
                    isPickOnBounds = true
                }



                gridpaneColumnConstraints {
                    hgrow = Priority.ALWAYS
//                    percentWidth = 38.0
                    alignment = Pos.CENTER_RIGHT
                }
            }.setId(PlayerStyles.bottomPlayerBarTrackControls)

        }

    }


}
