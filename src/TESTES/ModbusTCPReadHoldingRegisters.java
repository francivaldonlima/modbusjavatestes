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

/**
 *
 * @author francivaldo
 */
import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;

import java.net.InetAddress;

public class ModbusTCPReadHoldingRegisters {

    public static void main(String[] args) {
        try {
            // Definindo os parâmetros TCP para conexão Modbus
            TcpParameters tcpParameters = new TcpParameters();
            tcpParameters.setHost(InetAddress.getByName("192.168.1.108")); // IP do CLP
            tcpParameters.setPort(502); // Porta padrão do Modbus TCP
            tcpParameters.setKeepAlive(true); // Manter a conexão ativa

            // Criando o mestre Modbus TCP
            ModbusMaster master = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
            Modbus.setAutoIncrementTransactionId(true);

            try {
                master.connect(); // Conectando ao servidor Modbus

                int slaveId = 3; // ID do dispositivo escravo (CLP)
                int offset = 0; // Endereço do primeiro registro de holding que queremos ler
                int quantity = 1; // Número de registros para ler

                // Lendo o primeiro registro de holding
                int[] registerValues = master.readHoldingRegisters(slaveId, offset, quantity);
                if (registerValues.length > 0) {
                    System.out.println("Valor do primeiro registro de holding: " + registerValues[0]);
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

