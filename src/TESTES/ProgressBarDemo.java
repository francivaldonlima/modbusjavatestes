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
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressBarDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Progress Bar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(50);
        progressBar.setStringPainted(true);
        
        // Mudando a cor da barra de progresso
        progressBar.setUI(new BasicProgressBarUI());
        progressBar.setForeground(new Color(255, 0, 0)); // Cor vermelha

        frame.setLayout(new GridBagLayout());
        frame.add(progressBar);
        frame.setVisible(true);
    }
}
