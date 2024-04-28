/*
 * Copyright 2024 franc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package TESTES;

/**
 *
 * @author franc
 */


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// shows multiple progress bars drawn in different colors.
public class barradeprogresso extends Application {
     public static void main(String[] args) { launch(args); }

     @Override public void start(Stage stage) {
         final VBox layout = new VBox(10);
         layout.setAlignment(Pos.CENTER);
         layout.getChildren().setAll(
             new ColoredProgressBar("red-bar",    0.2),
             new ColoredProgressBar("orange-bar", 0.4),
             new ColoredProgressBar("yellow-bar", 0.6),
             new ColoredProgressBar("green-bar",  0.8)
         );
         layout.getStylesheets().add(getClass().getResource("progress.css").toExternalForm());
         stage.setScene(new Scene(layout));
         stage.show();
    }

    class ColoredProgressBar extends ProgressBar {
        ColoredProgressBar(String styleClass, double progress) {
            super(progress);
            getStyleClass().add(styleClass);
        }
    }
}
