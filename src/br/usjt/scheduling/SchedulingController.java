package br.usjt.scheduling;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SchedulingController implements Initializable {

    private int imageNumber, lastFrame;
    private Timer timer;
    private Path algorithmPath, resourcePath;
    private String folderImageUri;

    @FXML
    private ComboBox<String> cbEscolha;
    @FXML
    private ImageView ivAlgorithm;
    @FXML
    private Button btnRepeat;

    private void proximaImagem() {
        ivAlgorithm.setImage(new Image(String.format("%s/%04d.jpg", this.folderImageUri, this.imageNumber)));
        this.imageNumber++;
        if (this.imageNumber > this.lastFrame)
            this.timer.cancel();
    }

    @FXML
    private void changeAlgorithm() {
        this.btnRepeat.setVisible(true);
        this.algorithmPath = resourcePath.resolve(cbEscolha.getSelectionModel().getSelectedItem());
        this.folderImageUri = this.algorithmPath.toFile().toURI().toString();
        this.imageNumber = 0;

        if (this.timer != null) {
            this.timer.cancel();
        }

        this.timer = new Timer();
        this.lastFrame = this.algorithmPath.toFile().list().length;
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                proximaImagem();
            }
        }, 0L, 70L);
    }

    @FXML
    private void repeatAlgorithm() {
        changeAlgorithm();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resourcePath = Paths.get("resources");
        this.btnRepeat.setVisible(false);
        cbEscolha.setItems(FXCollections.observableArrayList(Arrays.asList(this.resourcePath.toFile().list())));
    }
}