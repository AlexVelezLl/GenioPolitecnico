/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akinator;

import TDA.ArbolDecision;
import java.applet.AudioClip;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author Alex Velez
 */
public class VistaJuego {

    private final Pane root;
    private StackPane stSi;
    private StackPane stNo;
    private final Font theFont = Font.font("Comic Sans MS", 36);
    private Pane paneRespt;
    private ArbolDecision<String> tree; // Declarar el arbol de decision
    private ImageView imgCartel;

    public VistaJuego(final Pane root) {
        this.root = root;
        createRoot();
    }

    private void createRoot() {
        final ImageView imgResp = new ImageView(new Image("/Resources/Options.png"));
        imgCartel = new ImageView(new Image("/Resources/CartelVacio.png"));
        final ImageView imgSelected = new ImageView(new Image("/Resources/Selected.png"));

        imgCartel.setTranslateX(490);
        imgCartel.setTranslateY(50);
        imgCartel.setOpacity(0);

        imgSelected.setTranslateX(1);
        imgSelected.setTranslateY(25);
        imgSelected.setOpacity(0);
        final VBox vbResp = new VBox();

        final FadeTransition selectFadein = new FadeTransition(Duration.millis(200), imgSelected);
        selectFadein.setToValue(1);

        final FadeTransition selectFadeout = new FadeTransition(Duration.millis(200), imgSelected);
        selectFadeout.setToValue(0);

        vbResp.setOnMouseEntered(e -> {
            root.getScene().setCursor(Cursor.HAND);
            selectFadein.play();
        });
        vbResp.setOnMouseExited(e -> {
            root.getScene().setCursor(Cursor.DEFAULT);
            selectFadeout.play();
        });
        stSi = new StackPane();
        stNo = new StackPane();
        stSi.setMinWidth(215);
        stSi.setMinHeight(60);
        final TranslateTransition transSi = new TranslateTransition(Duration.millis(200), imgSelected);
        transSi.setToY(25);
        stSi.setOnMouseEntered(e -> transSi.play());
        final Label lblSi = new Label("Si");
        lblSi.setTextFill(Color.WHITE);
        lblSi.setFont(theFont);
        stSi.getChildren().add(lblSi);

        stNo.setMinWidth(215);
        stNo.setMinHeight(60);
        final TranslateTransition transNo = new TranslateTransition(Duration.millis(200), imgSelected);
        transNo.setToY(85);
        transNo.play();

        stNo.setOnMouseEntered(e -> transNo.play());

        final Label lblNo = new Label("No");
        lblNo.setTextFill(Color.WHITE);
        lblNo.setFont(theFont);
        stNo.getChildren().add(lblNo);

        vbResp.getChildren().addAll(stSi, stNo);
        vbResp.setLayoutY(25);
        paneRespt = new Pane();
        paneRespt.getChildren().addAll(imgResp, imgSelected, vbResp);
        paneRespt.setTranslateX(1400);
        paneRespt.setTranslateY(360);

        final TranslateTransition transPaneRespt = new TranslateTransition(Duration.millis(300), paneRespt);
        transPaneRespt.setToX(700);
        final FadeTransition fadein = new FadeTransition(Duration.millis(400), imgCartel);
        fadein.setToValue(1);
        final Timeline t = new Timeline();
        t.setCycleCount(1);
        t.getKeyFrames().add(new KeyFrame(Duration.millis(400), e -> {
            fadein.play();
            transPaneRespt.play();
        }));
        t.play();
        root.getChildren().addAll(imgCartel, paneRespt);
    }

    public void iniciarJuego() {
        tree = ArbolDecision.cargarArbol(); // Metodo cargar arbol que lea el archivo de texto y devuelva el arbol
                                            // armado
        iniciarJuego(tree.getRoot());
    }

    private void iniciarJuego(final String pregunta) {
        if (pregunta != null) {
            final String pregArreg = arreglarTexto(pregunta, 21);
            final Label lbl = new Label(pregArreg);
            lbl.setTextFill(Color.WHITE);
            lbl.setFont(theFont);
            lbl.setTranslateX(600);
            lbl.setTranslateY(70);
            if (pregunta.equals(tree.getRoot())) {
                lbl.setOpacity(0);
                final FadeTransition fadein = new FadeTransition(Duration.millis(400), lbl);
                fadein.setToValue(1);
                final Timeline t = new Timeline();
                t.setCycleCount(1);
                t.getKeyFrames().add(new KeyFrame(Duration.millis(400), e -> {
                    fadein.play();
                }));
                t.play();
            }

            root.getChildren().add(lbl);

            stSi.setOnMouseClicked(e -> {
                final AudioClip sonido = java.applet.Applet
                        .newAudioClip(getClass().getResource("/Resources/Click.wav"));
                sonido.play();
                root.getChildren().remove(root.getChildren().size() - 1);
                final String answer = tree.getAnswer(pregunta, true);
                if (answer == null) {
                    terminarJuego(pregunta, true);
                } else {
                    iniciarJuego(answer);
                }
            });
            stNo.setOnMouseClicked(e -> {
                final AudioClip sonido = java.applet.Applet
                        .newAudioClip(getClass().getResource("/Resources/Click.wav"));
                sonido.play();
                root.getChildren().remove(root.getChildren().size() - 1);
                final String answer = tree.getAnswer(pregunta, false);
                if (answer == null) {
                    terminarJuego(pregunta, false);
                } else {
                    iniciarJuego(answer);
                }
            });
        }
    }

    private void terminarJuego(final String pregunta, final boolean acerto) {
        final TranslateTransition transResp = new TranslateTransition(Duration.millis(300), paneRespt);
        transResp.setToX(1400);
        transResp.play();

        final Pane pContinuar = new Pane();
        pContinuar.getChildren().add(new ImageView(new Image("/Resources/Continuar.png")));
        pContinuar.setTranslateX(1400);
        pContinuar.setTranslateY(400);
        pContinuar.setOnMouseEntered(e3 -> root.getScene().setCursor(Cursor.HAND));
        pContinuar.setOnMouseExited(e3 -> root.getScene().setCursor(Cursor.DEFAULT));
        final TranslateTransition transConti = new TranslateTransition(Duration.millis(500), pContinuar);
        transConti.setToX(790);
        root.getChildren().add(pContinuar);
        if (acerto) {
            final ImageView imaTortGan = new ImageView(new Image("/Resources/TortugaGanadora.png"));
            imaTortGan.setTranslateX(165);
            imaTortGan.setTranslateY(67);
            imaTortGan.setOpacity(0);

            final Label lbl = new Label("     ¡Gane! Eso fue\n    realmente facil.");
            lbl.setTextFill(Color.WHITE);
            lbl.setFont(theFont);
            lbl.setTranslateX(600);
            lbl.setTranslateY(80);

            final FadeTransition fdTort = new FadeTransition(Duration.millis(300), root.getChildren().get(1));
            fdTort.setToValue(0);
            final FadeTransition fdTortGan = new FadeTransition(Duration.millis(300), imaTortGan);
            fdTortGan.setToValue(1);
            final Group gp = new Group(imaTortGan);
            gp.getChildren().addAll(imgCartel, lbl);
            fdTortGan.setOnFinished(e -> {
                final TranslateTransition transGp = new TranslateTransition(Duration.millis(600), gp);
                transGp.setToX(175);
                transGp.play();
                transGp.setOnFinished(e2 -> {
                    final RotateTransition rtTort = new RotateTransition(Duration.millis(150), imaTortGan);
                    rtTort.setCycleCount(4);
                    rtTort.setByAngle(-10);
                    rtTort.setAutoReverse(true);

                    final Timeline t = new Timeline();
                    t.getKeyFrames().add(new KeyFrame(Duration.millis(1000), e3 -> rtTort.play()));

                    t.setCycleCount(-1);
                    t.play();
                    pContinuar.setOnMouseClicked(e3 -> {
                        final AudioClip sonido = java.applet.Applet
                                .newAudioClip(getClass().getResource("/Resources/Click.wav"));
                        sonido.play();
                        finalizarJuego(imgCartel, pContinuar, imaTortGan, lbl, true);
                    });

                    transConti.play();

                });
            });
            root.getChildren().addAll(gp);
            fdTort.play();
            fdTortGan.play();
        } else {
            final Label lbl = new Label("    ¡Vaya! Me lo has\n     puesto dificil.");
            lbl.setTextFill(Color.WHITE);
            lbl.setFont(theFont);
            lbl.setTranslateX(600);
            lbl.setTranslateY(80);

            final VBox preguntas = new VBox();
            preguntas.setTranslateX(600);
            preguntas.setTranslateY(250);
            preguntas.setSpacing(20);
            final Label pregAnim = new Label("¿En que animal estabas pensando?");
            pregAnim.setFont(theFont);
            pregAnim.setTextFill(Color.WHITE);
            final StackPane stPreg = new StackPane(pregAnim);

            stPreg.setStyle("-fx-background-color: #d58029;" + "-fx-padding: 10px;" + "-fx-background-radius: 10;");
            final TextField txtFdAnim = new TextField();
            final Font pregFont = Font.font("Comic Sans MZ", 30);
            txtFdAnim.setFont(pregFont);
            txtFdAnim.setStyle("-fx-background-radius: 10;");
            final HBox hbEnv = new HBox();
            hbEnv.setSpacing(10);

            final HBox hbEnv2 = new HBox();
            final StackPane stPreg2 = new StackPane();
            final String newAnimal = "";
            final Pane paneEnv = new Pane(new ImageView(new Image("Resources/Next.png")));
            paneEnv.setTranslateY(5);
            paneEnv.setOnMouseEntered(e -> root.getScene().setCursor(Cursor.HAND));
            paneEnv.setOnMouseExited(e -> root.getScene().setCursor(Cursor.DEFAULT));
            paneEnv.setOnMouseClicked(new Handle(txtFdAnim, newAnimal, paneEnv, stPreg2, pregunta, hbEnv2));

            hbEnv.getChildren().addAll(txtFdAnim, paneEnv);

            final TextField txtFd2 = new TextField();
            txtFd2.setFont(pregFont);
            txtFd2.setStyle("-fx-background-radius: 10;");
            final StackPane stPreg3 = new StackPane();
            final Pane paneEnv2 = new Pane(new ImageView(new Image("Resources/Next.png")));
            paneEnv2.setTranslateY(5);
            paneEnv2.setOnMouseEntered(e -> root.getScene().setCursor(Cursor.HAND));
            paneEnv2.setOnMouseExited(e -> root.getScene().setCursor(Cursor.DEFAULT));
            paneEnv2.setOnMouseClicked(e -> {
                if (!txtFd2.getText().equals("")) {
                    // Implementar guardar en el arbol aqui
                    final AudioClip sonido2 = java.applet.Applet
                            .newAudioClip(getClass().getResource("/Resources/Click.wav"));
                    sonido2.play();
                    String preg2 = "Para " + txtFdAnim.getText() + ", la respuesta a " + "la pregunta "
                            + txtFd2.getText() + ", es:";

                    preg2 = arreglarTexto(preg2, 35);
                    final Label lblPreg2 = new Label(preg2);
                    lblPreg2.setFont(pregFont);
                    lblPreg2.setTextFill(Color.WHITE);
                    stPreg3.getChildren().add(lblPreg2);
                    stPreg3.setStyle(
                            "-fx-background-color: #d58029;" + "-fx-padding: 10px;" + "-fx-background-radius: 10;");

                    final TranslateTransition transPreg = new TranslateTransition(Duration.millis(300), preguntas);
                    transPreg
                            .setToY(preguntas.getTranslateY() - ((StackPane) preguntas.getChildren().get(2)).getHeight()
                                    - ((HBox) preguntas.getChildren().get(3)).getHeight() - 2 * preguntas.getSpacing());
                    final FadeTransition fadePreg3 = new FadeTransition(Duration.millis(300),
                            preguntas.getChildren().get(2));
                    final FadeTransition fadePreg4 = new FadeTransition(Duration.millis(300),
                            preguntas.getChildren().get(3));
                    fadePreg3.setToValue(0);
                    fadePreg4.setToValue(0);
                    fadePreg3.play();
                    fadePreg4.play();
                    transPreg.play();
                    fadePreg3.setOnFinished(e2 -> {
                        final Label lblFinal = new Label("Muchas Gracias! He \naprendido algo nuevo.");
                        lblFinal.setFont(theFont);
                        lblFinal.setTextFill(Color.WHITE);
                        lblFinal.setTranslateX(600);
                        lblFinal.setTranslateY(80);
                        lblFinal.setOpacity(0);
                        root.getChildren().add(lblFinal);
                        pContinuar.setOnMouseClicked(e3 -> {
                            final AudioClip sonido = java.applet.Applet
                                    .newAudioClip(getClass().getResource("/Resources/Click.wav"));
                            sonido.play();
                            finalizarJuego(imgCartel, pContinuar, null, lblFinal, false);
                        });
                        final FadeTransition fdLblFinal = new FadeTransition(Duration.millis(300), lblFinal);
                        fdLblFinal.setToValue(1);
                        final TranslateTransition transPreg3 = new TranslateTransition(Duration.millis(200), stPreg3);
                        transPreg3.setToX(1400);
                        final TranslateTransition transCartel = new TranslateTransition(Duration.millis(300),
                                paneRespt);
                        transCartel.setToX(1400);

                        stSi.setOnMouseClicked(e3 -> {
                            tree.replaceData(pregunta, txtFd2.getText());
                            tree.add(txtFdAnim.getText(), txtFd2.getText(), true);
                            tree.add(pregunta, txtFd2.getText(), false);

                            final AudioClip sonido = java.applet.Applet
                                    .newAudioClip(getClass().getResource("/Resources/Click.wav"));
                            sonido.play();
                            lbl.setOpacity(0);
                            transPreg3.play();
                            fdLblFinal.play();
                            transCartel.play();
                            transConti.play();
                        });

                        stNo.setOnMouseClicked(e3 -> {
                            tree.replaceData(pregunta, txtFd2.getText());
                            tree.add(txtFdAnim.getText(), txtFd2.getText(), false);
                            tree.add(pregunta, txtFd2.getText(), true);

                            final AudioClip sonido = java.applet.Applet
                                    .newAudioClip(getClass().getResource("/Resources/Click.wav"));
                            sonido.play();
                            transPreg3.play();
                            lbl.setOpacity(0);
                            fdLblFinal.play();
                            transCartel.play();
                            transConti.play();
                        });
                        final TranslateTransition transCartResp = new TranslateTransition(Duration.millis(300),
                                paneRespt);
                        transCartResp.setToX(700);
                        transCartResp.setToY(paneRespt.getTranslateY() + 50);
                        transCartResp.play();
                    });
                    txtFd2.setDisable(true);
                    paneEnv2.setDisable(true);
                }
            });
            hbEnv2.setSpacing(10);
            hbEnv2.getChildren().addAll(txtFd2, paneEnv2);
            hbEnv2.setOpacity(0);
            preguntas.getChildren().addAll(stPreg, hbEnv, stPreg2, hbEnv2, stPreg3);
            root.getChildren().addAll(lbl, preguntas);

        }
    }

    private void finalizarJuego(final ImageView cartel, final Pane continuar, final ImageView tort, final Label letras,
            final boolean gano) {
        tree.guardarArbol();
        final TranslateTransition transCartel = new TranslateTransition(Duration.millis(300), cartel);
        final TranslateTransition transLetras = new TranslateTransition(Duration.millis(300), letras);
        final TranslateTransition transContinuar = new TranslateTransition(Duration.millis(300), continuar);
        if (gano) {
            final TranslateTransition transtort = new TranslateTransition(Duration.millis(300), tort);
            transtort.setToX(-600);
            transtort.play();
        } else {
            final FadeTransition fadeTort = new FadeTransition(Duration.millis(300), root.getChildren().get(1));
            fadeTort.setToValue(0);
            fadeTort.play();
        }

        transCartel.setToY(-500);
        transLetras.setToY(-500);
        transContinuar.setToX(1400);

        transCartel.play();
        transLetras.play();
        transContinuar.play();
        transContinuar.setOnFinished(e -> {
            VistaIndex.getTimeline().play();
            final Timeline t = new Timeline();
            t.getKeyFrames().add(new KeyFrame(Duration.millis(1080), e1 -> root.getChildren().get(1).setOpacity(1)));
            t.play();
        });

    }

    public static String arreglarTexto(final String texto, final int caract) {
        final String[] txtSplit = texto.split(" ");
        String txt = "";
        int acum = 0;
        for (final String s : txtSplit) {
            if (acum + 1 + s.length() < caract) {
                acum += s.length() + 1;
                txt = txt + " " + s;
            } else {
                txt = txt + "\n" + s;
                acum = s.length();
            }

        }
        txt = txt.substring(1);

        if (txtSplit.length == 1) {
            if (!txtSplit[0].contains("¿")) {
                txt = "¿" + txt;
            }
            if (!txtSplit[0].contains("?")) {
                txt = txt + "?";
            }
        } else {
            if (!txtSplit[1].contains("¿")) {
                txt = "¿" + txt;
            }
            if (!txtSplit[txtSplit.length - 1].contains("?")) {
                txt = txt + "?";
            }

        }
        return txt;
    }
}

class Handle implements EventHandler<Event> {

    private final TextField txtFd;
    private String stringAsig;
    private final Pane pane;
    private final StackPane pane2;
    private final HBox hb;
    private final String preg;

    public Handle(final TextField txtFd, final String stringAsig, final Pane pane, final StackPane pane2,
            final String preg, final HBox hb) {
        this.txtFd = txtFd;
        this.pane = pane;
        this.pane2 = pane2;
        this.hb = hb;
        this.preg = preg;
    }

    @Override
    public void handle(final Event event) {
        if (!txtFd.getText().equals("")) {
            final AudioClip sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Resources/Click.wav"));
            sonido.play();
            stringAsig = txtFd.getText();
            txtFd.setDisable(true);
            pane.setDisable(true);
            final String pregunta = "Escribe una pregunta que me permita diferenciar entre " + preg + " y " + stringAsig
                    + ": ";
            String preguntaArreg = VistaJuego.arreglarTexto(pregunta, 35);
            preguntaArreg = preguntaArreg.substring(1, preguntaArreg.length() - 2);
            final Label lbl = new Label(preguntaArreg);
            final Font fuente = Font.font("Comic Sans MZ", 30);
            lbl.setFont(fuente);
            lbl.setTextFill(Color.WHITE);
            pane2.getChildren().add(lbl);
            pane2.setStyle("-fx-background-color: #d58029;" + "-fx-background-radius: 10;");
            final VBox vb = (VBox) pane2.getParent();
            final TranslateTransition transVb = new TranslateTransition(Duration.millis(400), vb);
            transVb.setToY(vb.getTranslateY() - ((StackPane) vb.getChildren().get(0)).getHeight()
                    - ((HBox) vb.getChildren().get(1)).getHeight() - 2 * vb.getSpacing());
            final FadeTransition fdVb = new FadeTransition(Duration.millis(300), vb.getChildren().get(0));
            fdVb.setToValue(0);
            final FadeTransition fdVb2 = new FadeTransition(Duration.millis(300), vb.getChildren().get(1));
            fdVb2.setToValue(0);
            final FadeTransition fdVb3 = new FadeTransition(Duration.millis(300), hb);
            fdVb3.setToValue(1);

            fdVb.play();
            fdVb2.play();
            fdVb3.play();
            transVb.play();
        }

    }

}
