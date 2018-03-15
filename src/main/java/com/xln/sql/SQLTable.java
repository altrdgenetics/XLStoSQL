/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xln.sql;

import com.xln.model.DatabaseTableModel;
import com.xln.util.Global;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author User
 */
public class SQLTable {
    
    public static void batchUpdateDatabaseTable(List<DatabaseTableModel> list) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.connectToDB();
            String sql = "UPDATE Activity SET "
                    + "action           = ? "  //01
                    + "WHERE id         = ? "  //02
                    + "AND   casetype   = ? "  //03
                    + "AND   casenumber = ? "  //04
                    + "AND   action     = ? "; //05
            ps = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            
            for (DatabaseTableModel item : list){
                ps.setString(1, item.getColumnG());
                ps.setString(2, item.getColumnA());
                ps.setString(3, item.getColumnC());
                ps.setString(4, item.getColumnE());
                ps.setString(5, item.getColumnF());

                ps.addBatch();
                if (++count % Global.getBATCH_SIZE() == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch();
            conn.commit();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex1.toString());
            }
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }
    
}
