/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.connection;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.Connection;
import java.sql.SQLException;

public class BoneCPImpl implements ConnectionInterface {

    private BoneCP connectionPool = null;

    @Override
    public Connection newConnection() {
        Connection c = null;
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/usuariodb");
        config.setUsername("root");
        config.setPassword("bitnami");
        config.setMinConnectionsPerPartition(1);
        config.setMaxConnectionsPerPartition(3);
        config.setPartitionCount(1);
        try {
            connectionPool = new BoneCP(config);
        } catch (SQLException ex) {
        }
        try {
            c = connectionPool.getConnection();
        } catch (SQLException ex) {
        }
        return c;
    }

    @Override
    public void disposeConnection() {
        if (connectionPool != null) {
            connectionPool.close();
        }
    }
}
