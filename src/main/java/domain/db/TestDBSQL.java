package domain.db;

import domain.model.Contact;
import domain.model.DomainException;
import domain.model.Test;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDBSQL implements TestDB {
    private Connection connection;
    private String schema;

    public TestDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    @Override
    public void add(Test test) {
        if (test == null) throw new DbException("Nothing to add");
        String sql = String.format("INSERT INTO %s.positivetest (user_id, testdate) VALUES (?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, test.getUserid());
            statementSQL.setDate(2, test.getDate());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException("You already registered a test at this date.");
        }
    }

    @Override
    public List<Test> allTests() {
        List<Test> tests = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.positivetest ORDER BY testdate ", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("user_id");
                Date date = result.getDate("testdate");
                Test test = new Test(userid, date);
                tests.add(test);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return tests;
    }

}
