package day8.데이터저장소시스템;

import javax.sql.DataSource;

public class MySQLUserRepository implements UserRepository{
    private final DataSource dataSource;

    public MySQLUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String findById(String id){
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return id;
    }

    @Override
    public void save(User user){
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }

    }

}

