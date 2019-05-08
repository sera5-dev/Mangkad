package mangkad.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class QueryBuilder {
    Connection conn;
    PreparedStatement ps;
    
    public QueryBuilder(Connection conn) {
        this.conn = conn;
        int i = 0;
    
    }
    
    
}
