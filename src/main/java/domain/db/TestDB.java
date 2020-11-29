package domain.db;

import domain.model.Test;

import java.util.List;

public interface TestDB {
    void add(Test test);

    List<Test> allTests();
}
