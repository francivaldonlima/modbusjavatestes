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

public class ModbusTCPExample {

    public static void main(String[] args) {
        try {
            // Parâmetros TCP
            TcpParameters tcpParameters = new TcpParameters();
            tcpParameters.setHost(InetAddress.getByName("192.168.1.108"));
            tcpParameters.setPort(502);
            tcpParameters.setKeepAlive(true); // Mantém a conexão ativa

            // Criando o mestre Modbus
            ModbusMaster master = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
            Modbus.setAutoIncrementTransactionId(true);

            // Abrindo a conexão
            try {
                master.connect();
                int slaveId = 3; // Endereço do slave
                int offset = 0; // Offset inicial das entradas digitais
                int quantity = 10; // Quantidade de entradas digitais a serem lidas

                // Lendo 10 entradas digitais
                boolean[] inputs = master.readDiscreteInputs(slaveId, offset, quantity);
                //System.out.println(inputs);
                for (int i = 0; i < inputs.length; i++) {
                    //System.out.println("francivaldo");
                    System.out.println("Input " + (offset + i) + ": " + inputs[i]);
                }
            } finally {
                // Fechando a conexão
                master.disconnect();
            }
        } catch (ModbusProtocolException | ModbusNumberException | ModbusIOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // Para tratar possíveis exceções de InetAddress.getByName()
            e.printStackTrace();
        }
    }
}

