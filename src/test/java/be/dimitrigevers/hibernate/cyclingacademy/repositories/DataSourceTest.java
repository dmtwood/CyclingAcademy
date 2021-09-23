package be.dimitrigevers.hibernate.cyclingacademy.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@DataJpaTest
public class DataSourceTest {

// MEMBER VARS

    private final DataSource dataSource;

// CONSTRUCTORS

    public DataSourceTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

// TEST METHODS

    @Test
    void getConnection() throws SQLException {
        var connection = dataSource.getConnection();
    }

}
