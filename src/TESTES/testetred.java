/*
 * Copyright 2024 francivaldo.
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

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import java.awt.Color;
import java.net.InetAddress;
import java.util.Arrays;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.text.DecimalFormat;

/**
 *
 * @author francivaldo
 */
public class testetred extends javax.swing.JFrame {

    /**
     * Creates new form testetred
     */
    public testetred() {
        

        initComponents();
        jProgressBar.setUI(new BasicProgressBarUI());
        jProgressBar.setForeground(new Color(0,51,204)); // Cor vermelha

        

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("connected read");

                while (true) {
                    try {

                        //System.out.println("teste teste");
                        valdo();

                    } catch (Exception e) {
                    }
                }

            }
        };
        thread.start();
        
        
        
        
        
        
        
        
        
        
        

    }

    private void valdo() {
        while (true){
            try {
            // Definindo os parâmetros TCP para conexão Modbus
            TcpParameters tcpParameters = new TcpParameters();
            tcpParameters.setHost(InetAddress.getByName("192.168.1.40")); // IP do CLP
            tcpParameters.setPort(502); // Porta padrão do Modbus TCP
            tcpParameters.setKeepAlive(true); // Manter a conexão ativa

            // Criando o mestre Modbus TCP
            ModbusMaster master = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
            Modbus.setAutoIncrementTransactionId(true);

            try {
                master.connect(); // Conectando ao servidor Modbus

                int slaveId = 1; // ID do dispositivo escravo (CLP)
                int offset = 1; // Endereço do primeiro registro de holding que queremos ler
                int quantity = 1; // Número de registros para ler

                // Lendo o primeiro registro de holding
                int[] registerValues = master.readHoldingRegisters(slaveId, offset, quantity);
                if (registerValues.length > 0) {
                    //System.out.println("Valor do primeiro registro de holding: " + registerValues[0]);
                    
                    //System.out.println(registerValues[0]);
                    
                    
                    int x = registerValues[0];
                    String valor = Arrays.toString(registerValues);
                    int ajuste = 2;
                    double aj = 0.02;
                    int per = ((x*100)/4000)+ajuste;
                    
                    String meuvalor = Integer.toString(x);
                    String pervalor = Integer.toString(per);
                    jLabel_VALOR.setText(meuvalor);
                    
                    jLabel_porcento.setText(pervalor+"%");
                    jLabel_porcento1.setText(pervalor+"%");
                    Double xd = Double.valueOf(x);
                    System.out.println(xd);
                    Double TX =((xd*10.01)/3940);
                    
                    double a1 = TX;
                    Thread.sleep(300);
                    double a2 = TX;
                    Thread.sleep(300);
                    double a3 = TX;
                    Thread.sleep(300);
                    double a4 = TX;
                    Thread.sleep(300);
                    double a5 = TX;
                    Thread.sleep(300);
                    double a6 = TX;
                    
                    double vx =(a1+a2+a3+a4+a5+a6)/6;
                    
                    String TS = String.valueOf(vx);
                    
                    DecimalFormat df = new DecimalFormat("#.##");
                    // Formatando o número
                    String formattedNumber = df.format(TX);
                    jLabel_TENSAO.setText(formattedNumber+"V");
                    
                    jProgressBar.setValue(per);
                    
                 
                } else {
                    System.out.println("Nenhum dado foi lido.");
                }
            } finally {
                master.disconnect(); // Desconectando do servidor Modbus
            }
        } catch (ModbusProtocolException | ModbusNumberException | ModbusIOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // Tratamento de exceções genéricas
            e.printStackTrace();
        }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_VALOR = new javax.swing.JLabel();
        jLabel_porcento1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar = new javax.swing.JProgressBar();
        jLabel_porcento = new javax.swing.JLabel();
        jLabel_TENSAO = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TESTE MODBUS");

        jLabel_VALOR.setFont(new java.awt.Font("Liberation Sans", 1, 80)); // NOI18N
        jLabel_VALOR.setText("valor");

        jLabel_porcento1.setFont(new java.awt.Font("Liberation Sans", 1, 80)); // NOI18N
        jLabel_porcento1.setText("%");

        jProgressBar.setBackground(new java.awt.Color(204, 204, 204));
        jProgressBar.setOrientation(1);
        jProgressBar.setToolTipText("");
        jProgressBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel_porcento.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel_porcento.setText("%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jLabel_porcento)
                .addGap(32, 32, 32))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(jLabel_porcento)
                .addContainerGap(228, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
        );

        jLabel_TENSAO.setFont(new java.awt.Font("Liberation Sans", 1, 80)); // NOI18N
        jLabel_TENSAO.setText("valor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel_VALOR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 395, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(272, 272, 272))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(205, 205, 205)
                    .addComponent(jLabel_porcento1)
                    .addContainerGap(788, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(83, 83, 83)
                    .addComponent(jLabel_TENSAO)
                    .addContainerGap(788, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(302, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_VALOR)
                .addGap(345, 345, 345))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(255, 255, 255)
                    .addComponent(jLabel_porcento1)
                    .addContainerGap(560, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(100, 100, 100)
                    .addComponent(jLabel_TENSAO)
                    .addContainerGap(715, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
               // if ("Nimbus".equals(info.getName())) {
                //if ("Nimbus".equals(info.getName())) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(testetred.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testetred.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testetred.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testetred.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testetred().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_TENSAO;
    private javax.swing.JLabel jLabel_VALOR;
    private javax.swing.JLabel jLabel_porcento;
    private javax.swing.JLabel jLabel_porcento1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar;
    // End of variables declaration//GEN-END:variables
}
