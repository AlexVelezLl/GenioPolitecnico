/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akinator;

import java.applet.AudioClip;
import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Alex Velez
 */
public class VistaIndex {

    private Pane root;
    private Group intro;
    private TranslateTransition transCartel;
    private TranslateTransition transTitle;
    private TranslateTransition transPlay;
    private TranslateTransition transSalir;
    private static Timeline t;
    private AudioClip son;
    private AudioClip son2;
    private AudioClip son4;
    private MediaPlayer mediaPlayer;

    public VistaIndex() {
        root = new Pane();
        getTimeLineIntro();
        createRoot();

        t.play();
    }

    private void createRoot() {
        Media musicFile = new Media(new File("src/Resources/Game1.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(musicFile);

        son = java.applet.Applet.newAudioClip(getClass().getResource("/Resources/Metal1.wav"));
        son2 = java.applet.Applet.newAudioClip(getClass().getResource("/Resources/Metal1.wav"));
        AudioClip son3 = java.applet.Applet.newAudioClip(getClass().getResource("/Resources/Prueba.wav"));
        son3.play();
        son4 = java.applet.Applet.newAudioClip(getClass().getResource("/Resources/Explo2.wav"));

        /* Imagenes que se utilizaran en las animaciones */
        ImageView backGround = new ImageView(new Image("/Resources/BackGround.png"));
        ImageView cartel = new ImageView(new Image("/Resources/Cartel.png"));
        ImageView title = new ImageView(new Image("/Resources/GenioPolitecnico.png"));
        ImageView play = new ImageView(new Image("/Resources/Jugar.png"));
        ImageView salir = new ImageView(new Image("/Resources/Salir.png"));
        Pane pSalir = new Pane();
        pSalir.getChildren().add(salir);

        pSalir.setTranslateX(-100);
        pSalir.setTranslateY(690);
        pSalir.setOnMouseClicked(e -> {
            Platform.exit();
        });
        pSalir.setOnMouseEntered(e -> root.getScene().setCursor(Cursor.HAND));
        pSalir.setOnMouseExited(e -> root.getScene().setCursor(Cursor.DEFAULT));

        /*
         * Stop[] stops = new Stop[] { new Stop(0, Color.BLACK), new Stop(1,
         * Color.TRANSPARENT)}; LinearGradient lg1 = new LinearGradient(0, 0, 1, 0,
         * true, CycleMethod.NO_CYCLE, stops); Rectangle r = new Rectangle(100,100);
         * r.setFill(lg1);
         */
        transSalir = new TranslateTransition(Duration.millis(300), pSalir);
        transSalir.setToX(10);

        title.setTranslateX(1400);
        title.setLayoutY(250);
        transTitle = new TranslateTransition(Duration.millis(300), title);
        transTitle.setToX(680);

        Pane pPlay = new Pane();
        pPlay.getChildren().add(play);
        pPlay.setTranslateX(1400);
        pPlay.setLayoutY(570);
        pPlay.setOnMouseEntered(e -> root.getScene().setCursor(Cursor.HAND));
        pPlay.setOnMouseExited(e -> root.getScene().setCursor(Cursor.DEFAULT));
        pPlay.setOnMouseClicked(e -> {
            AudioClip sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Resources/Click.wav"));
            sonido.play();
            TranslateTransition transPlay = new TranslateTransition(Duration.millis(300), pPlay);
            transPlay.setToX(1400);

            TranslateTransition transTitle = new TranslateTransition(Duration.millis(300), title);
            transTitle.setToX(1400);

            TranslateTransition transCartel = new TranslateTransition(Duration.millis(200), cartel);
            transCartel.setToX(-300);

            Timeline t = new Timeline();
            t.setCycleCount(1);
            t.getKeyFrames().add(new KeyFrame(Duration.millis(100), e1 -> transTitle.play()));

            transPlay.play();
            transCartel.play();
            t.play();
            VistaJuego vj = new VistaJuego(root);
            vj.iniciarJuego();
        });

        transPlay = new TranslateTransition(Duration.millis(300), pPlay);
        transPlay.setToX(820);

        cartel.setLayoutX(-500);
        transCartel = new TranslateTransition(Duration.millis(350), cartel);
        transCartel.setToX(990);

        root.getChildren().addAll(backGround, intro, cartel, title, pPlay, pSalir);

    }

    public Pane getRoot() {
        return root;
    }

    private void getTimeLineIntro() {
        ImageView intro00 = new ImageView(new Image("/Resources/AnimationIntro/Animacion000.png"));
        ImageView intro01 = new ImageView(new Image("/Resources/AnimationIntro/Animacion001.png"));
        ImageView intro02 = new ImageView(new Image("/Resources/AnimationIntro/Animacion002.png"));
        ImageView intro03 = new ImageView(new Image("/Resources/AnimationIntro/Animacion003.png"));
        ImageView intro04 = new ImageView(new Image("/Resources/AnimationIntro/Animacion004.png"));
        ImageView intro05 = new ImageView(new Image("/Resources/AnimationIntro/Animacion005.png"));
        ImageView intro06 = new ImageView(new Image("/Resources/AnimationIntro/Animacion006.png"));
        ImageView intro07 = new ImageView(new Image("/Resources/AnimationIntro/Animacion007.png"));
        ImageView intro08 = new ImageView(new Image("/Resources/AnimationIntro/Animacion008.png"));
        ImageView intro09 = new ImageView(new Image("/Resources/AnimationIntro/Animacion009.png"));
        ImageView intro10 = new ImageView(new Image("/Resources/AnimationIntro/Animacion010.png"));
        ImageView intro11 = new ImageView(new Image("/Resources/AnimationIntro/Animacion011.png"));
        ImageView intro12 = new ImageView(new Image("/Resources/AnimationIntro/Animacion012.png"));
        ImageView intro13 = new ImageView(new Image("/Resources/AnimationIntro/Animacion013.png"));
        ImageView intro14 = new ImageView(new Image("/Resources/AnimationIntro/Animacion014.png"));
        ImageView intro15 = new ImageView(new Image("/Resources/AnimationIntro/Animacion015.png"));
        ImageView intro16 = new ImageView(new Image("/Resources/AnimationIntro/Animacion016.png"));
        ImageView intro17 = new ImageView(new Image("/Resources/AnimationIntro/Animacion017.png"));
        ImageView intro18 = new ImageView(new Image("/Resources/AnimationIntro/Animacion018.png"));
        ImageView intro19 = new ImageView(new Image("/Resources/AnimationIntro/Animacion019.png"));
        ImageView intro20 = new ImageView(new Image("/Resources/AnimationIntro/Animacion020.png"));
        ImageView intro21 = new ImageView(new Image("/Resources/AnimationIntro/Animacion021.png"));
        ImageView intro22 = new ImageView(new Image("/Resources/AnimationIntro/Animacion022.png"));
        ImageView intro23 = new ImageView(new Image("/Resources/AnimationIntro/Animacion023.png"));
        ImageView intro24 = new ImageView(new Image("/Resources/AnimationIntro/Animacion024.png"));
        ImageView intro25 = new ImageView(new Image("/Resources/AnimationIntro/Animacion025.png"));
        ImageView intro26 = new ImageView(new Image("/Resources/AnimationIntro/Animacion026.png"));
        ImageView intro27 = new ImageView(new Image("/Resources/AnimationIntro/Animacion027.png"));
        ImageView intro28 = new ImageView(new Image("/Resources/AnimationIntro/Animacion028.png"));
        ImageView intro29 = new ImageView(new Image("/Resources/AnimationIntro/Animacion029.png"));
        ImageView intro30 = new ImageView(new Image("/Resources/AnimationIntro/Animacion030.png"));
        ImageView intro31 = new ImageView(new Image("/Resources/AnimationIntro/Animacion031.png"));
        ImageView intro32 = new ImageView(new Image("/Resources/AnimationIntro/Animacion032.png"));
        ImageView intro33 = new ImageView(new Image("/Resources/AnimationIntro/Animacion033.png"));
        ImageView intro34 = new ImageView(new Image("/Resources/AnimationIntro/Animacion034.png"));
        ImageView intro35 = new ImageView(new Image("/Resources/AnimationIntro/Animacion035.png"));
        ImageView intro36 = new ImageView(new Image("/Resources/AnimationIntro/Animacion036.png"));
        ImageView intro37 = new ImageView(new Image("/Resources/AnimationIntro/Animacion037.png"));
        ImageView intro38 = new ImageView(new Image("/Resources/AnimationIntro/Animacion038.png"));
        ImageView intro39 = new ImageView(new Image("/Resources/AnimationIntro/Animacion039.png"));
        ImageView intro40 = new ImageView(new Image("/Resources/AnimationIntro/Animacion040.png"));
        ImageView intro41 = new ImageView(new Image("/Resources/AnimationIntro/Animacion041.png"));
        ImageView intro42 = new ImageView(new Image("/Resources/AnimationIntro/Animacion042.png"));
        ImageView intro43 = new ImageView(new Image("/Resources/AnimationIntro/Animacion043.png"));
        ImageView intro44 = new ImageView(new Image("/Resources/AnimationIntro/Animacion044.png"));
        ImageView intro45 = new ImageView(new Image("/Resources/AnimationIntro/Animacion045.png"));
        ImageView intro46 = new ImageView(new Image("/Resources/AnimationIntro/Animacion046.png"));
        ImageView intro47 = new ImageView(new Image("/Resources/AnimationIntro/Animacion047.png"));
        ImageView intro48 = new ImageView(new Image("/Resources/AnimationIntro/Animacion048.png"));
        ImageView intro49 = new ImageView(new Image("/Resources/AnimationIntro/Animacion049.png"));
        ImageView intro50 = new ImageView(new Image("/Resources/AnimationIntro/Animacion050.png"));
        ImageView intro51 = new ImageView(new Image("/Resources/AnimationIntro/Animacion051.png"));
        ImageView intro52 = new ImageView(new Image("/Resources/AnimationIntro/Animacion052.png"));
        ImageView intro53 = new ImageView(new Image("/Resources/AnimationIntro/Animacion053.png"));
        ImageView intro54 = new ImageView(new Image("/Resources/AnimationIntro/Animacion054.png"));
        ImageView intro55 = new ImageView(new Image("/Resources/AnimationIntro/Animacion055.png"));
        ImageView intro56 = new ImageView(new Image("/Resources/AnimationIntro/Animacion056.png"));
        ImageView intro57 = new ImageView(new Image("/Resources/AnimationIntro/Animacion057.png"));
        ImageView intro58 = new ImageView(new Image("/Resources/AnimationIntro/Animacion058.png"));
        ImageView intro59 = new ImageView(new Image("/Resources/AnimationIntro/Animacion059.png"));
        ImageView intro60 = new ImageView(new Image("/Resources/AnimationIntro/Animacion060.png"));
        ImageView intro61 = new ImageView(new Image("/Resources/AnimationIntro/Animacion061.png"));
        ImageView intro62 = new ImageView(new Image("/Resources/AnimationIntro/Animacion062.png"));
        ImageView intro63 = new ImageView(new Image("/Resources/AnimationIntro/Animacion063.png"));
        ImageView intro64 = new ImageView(new Image("/Resources/AnimationIntro/Animacion064.png"));
        ImageView intro65 = new ImageView(new Image("/Resources/AnimationIntro/Animacion065.png"));
        ImageView intro66 = new ImageView(new Image("/Resources/AnimationIntro/Animacion066.png"));
        ImageView intro67 = new ImageView(new Image("/Resources/AnimationIntro/Animacion067.png"));
        ImageView intro68 = new ImageView(new Image("/Resources/AnimationIntro/Animacion068.png"));

        intro = new Group(intro00);

        intro.setTranslateX(-10);
        intro.setTranslateY(115);

        t = new Timeline();
        t.setCycleCount(1);

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1068), (ActionEvent event) -> intro.getChildren().setAll(intro01)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1102), (ActionEvent event) -> intro.getChildren().setAll(intro02)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1136), (ActionEvent event) -> intro.getChildren().setAll(intro03)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1170), (ActionEvent event) -> intro.getChildren().setAll(intro04)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1204), (ActionEvent event) -> intro.getChildren().setAll(intro05)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1238), (ActionEvent event) -> intro.getChildren().setAll(intro06)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1272), (ActionEvent event) -> intro.getChildren().setAll(intro07)));

        t.getKeyFrames().add(new KeyFrame(Duration.millis(1306), (ActionEvent event) -> {
            intro.getChildren().setAll(intro08);

            son2.play();
        }));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1340), (ActionEvent event) -> intro.getChildren().setAll(intro09)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1374), (ActionEvent event) -> intro.getChildren().setAll(intro10)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1408), (ActionEvent event) -> intro.getChildren().setAll(intro11)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1442), (ActionEvent event) -> intro.getChildren().setAll(intro12)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1476), (ActionEvent event) -> intro.getChildren().setAll(intro13)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1510), (ActionEvent event) -> intro.getChildren().setAll(intro14)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1544), (ActionEvent event) -> intro.getChildren().setAll(intro15)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1578), (ActionEvent event) -> intro.getChildren().setAll(intro16)));

        t.getKeyFrames().add(new KeyFrame(Duration.millis(1612), (ActionEvent event) -> {
            intro.getChildren().setAll(intro17);

            son.play();
        }));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1646), (ActionEvent event) -> intro.getChildren().setAll(intro18)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1680), (ActionEvent event) -> intro.getChildren().setAll(intro19)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1714), (ActionEvent event) -> intro.getChildren().setAll(intro20)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1748), (ActionEvent event) -> intro.getChildren().setAll(intro21)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1782), (ActionEvent event) -> intro.getChildren().setAll(intro22)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1816), (ActionEvent event) -> intro.getChildren().setAll(intro23)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1850), (ActionEvent event) -> intro.getChildren().setAll(intro24)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1884), (ActionEvent event) -> intro.getChildren().setAll(intro25)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1918), (ActionEvent event) -> intro.getChildren().setAll(intro26)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1952), (ActionEvent event) -> intro.getChildren().setAll(intro27)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1986), (ActionEvent event) -> intro.getChildren().setAll(intro28)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2020), (ActionEvent event) -> intro.getChildren().setAll(intro29)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2054), (ActionEvent event) -> intro.getChildren().setAll(intro30)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2088), (ActionEvent event) -> intro.getChildren().setAll(intro31)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2122), (ActionEvent event) -> intro.getChildren().setAll(intro32)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2156), (ActionEvent event) -> intro.getChildren().setAll(intro33)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2190), (ActionEvent event) -> intro.getChildren().setAll(intro34)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2224), (ActionEvent event) -> intro.getChildren().setAll(intro35)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2258), (ActionEvent event) -> intro.getChildren().setAll(intro36)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2292), (ActionEvent event) -> intro.getChildren().setAll(intro37)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2326), (ActionEvent event) -> intro.getChildren().setAll(intro38)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2360), (ActionEvent event) -> intro.getChildren().setAll(intro39)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2394), (ActionEvent event) -> intro.getChildren().setAll(intro40)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2428), (ActionEvent event) -> intro.getChildren().setAll(intro41)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2462), (ActionEvent event) -> intro.getChildren().setAll(intro42)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2496), (ActionEvent event) -> intro.getChildren().setAll(intro43)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2530), (ActionEvent event) -> intro.getChildren().setAll(intro44)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2564), (ActionEvent event) -> intro.getChildren().setAll(intro45)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2598), (ActionEvent event) -> intro.getChildren().setAll(intro46)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2632), (ActionEvent event) -> intro.getChildren().setAll(intro47)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2666), (ActionEvent event) -> intro.getChildren().setAll(intro48)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2700), (ActionEvent event) -> intro.getChildren().setAll(intro49)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2734), (ActionEvent event) -> intro.getChildren().setAll(intro50)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2768), (ActionEvent event) -> intro.getChildren().setAll(intro51)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2802), (ActionEvent event) -> intro.getChildren().setAll(intro52)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2836), (ActionEvent event) -> intro.getChildren().setAll(intro53)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2870), (ActionEvent event) -> intro.getChildren().setAll(intro54)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2904), (ActionEvent event) -> intro.getChildren().setAll(intro55)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2938), (ActionEvent event) -> intro.getChildren().setAll(intro56)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(2972), (ActionEvent event) -> intro.getChildren().setAll(intro57)));

        t.getKeyFrames().add(new KeyFrame(Duration.millis(3006), (ActionEvent event) -> {
            intro.getChildren().setAll(intro58);
            son4.play();
        }));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(3040), (ActionEvent event) -> intro.getChildren().setAll(intro59)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(3074), (ActionEvent event) -> intro.getChildren().setAll(intro60)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(3108), (ActionEvent event) -> intro.getChildren().setAll(intro61)));

        t.getKeyFrames().add(new KeyFrame(Duration.millis(3142), (ActionEvent event) -> {
            intro.getChildren().setAll(intro62);
            mediaPlayer.play();
            mediaPlayer.setCycleCount(-1);
        }));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(3176), (ActionEvent event) -> intro.getChildren().setAll(intro63)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(3210), (ActionEvent event) -> intro.getChildren().setAll(intro64)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(3244), (ActionEvent event) -> intro.getChildren().setAll(intro65)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(3278), (ActionEvent event) -> intro.getChildren().setAll(intro66)));

        t.getKeyFrames()
                .add(new KeyFrame(Duration.millis(3312), (ActionEvent event) -> intro.getChildren().setAll(intro67)));

        t.getKeyFrames().add(new KeyFrame(Duration.millis(3346), (ActionEvent event) -> {
            intro.getChildren().setAll(intro68);
            transCartel.play();
            transTitle.play();
        }));

        t.getKeyFrames().add(new KeyFrame(Duration.millis(3450), (ActionEvent event) -> {
            transPlay.play();
            transSalir.play();
        }));

    }

    public static Timeline getTimeline() {
        return t;
    }
}
