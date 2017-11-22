/*
 * Copyright (c) 2017 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * trolleyes-server3: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/trolleyes-server3
 * 
 * trolleyes-server3 is distributed under the MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package eu.rafaelaznar.connection.specificimplementation;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import eu.rafaelaznar.connection.publicinterface.ConnectionInterface;
import eu.rafaelaznar.helper.ConnectionHelper;
import eu.rafaelaznar.helper.Log4jHelper;
import java.sql.Connection;
import java.sql.SQLException;

public class BoneCPConnection implements ConnectionInterface {

    private BoneCP connectionPool = null;
    private Connection oConnection = null;

    @Override
    public Connection newConnection() throws Exception {
        try {
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(ConnectionHelper.getConnectionChain());
            config.setUsername(ConnectionHelper.getDatabaseLogin());
            config.setPassword(ConnectionHelper.getDatabasePassword());
            config.setMinConnectionsPerPartition(1);
            config.setMaxConnectionsPerPartition(3);
            config.setPartitionCount(1);
            connectionPool = new BoneCP(config);
            oConnection = connectionPool.getConnection();
        } catch (SQLException ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        }
        return oConnection;
    }

    @Override
    public void disposeConnection() throws Exception {
        try {
            if (oConnection != null) {
                oConnection.close();
            }
            if (connectionPool != null) {
                connectionPool.close();
            }
        } catch (SQLException ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        }
    }
}
