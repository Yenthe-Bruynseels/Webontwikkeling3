package domain.db;

import domain.model.DomainException;
import domain.model.Test;
import util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDBSQL implements TestDB {
    private Connection connection;
    private String schema;

    public TestDBSQL(){
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    @Override
    public void add(Test test) {
        if (test == null) throw new DbException("Nothing to add");
        String sql = String.format("INSERT INTO %s.positivetest (user_id, date) VALUES (?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, test.getUserid());
            statementSQL.setDate(2, test.getDate());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }


}
