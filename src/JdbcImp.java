import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;

public class JdbcImp {
    // Connection using data source
    public DataSource dataSource(){
        PGSimpleDataSource simpleDataSource = new PGSimpleDataSource();
        simpleDataSource.setUser("postgres");
        simpleDataSource.setPassword("12345");
        simpleDataSource.setDatabaseName("postgres");
        return simpleDataSource;
    }
}
