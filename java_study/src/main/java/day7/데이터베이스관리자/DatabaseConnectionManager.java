package day7.데이터베이스관리자;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
데이터베이스 연결 관리자 -> double -checkedLocking 싱글톤 패턴
장점
1. 지연 초기화 -> 실제로 쓰기전 까지 인스턴스를 생성하지 않기때문에 메모리 효율적
2. 멀티 스레드 환경에서 안전하게 작동
3. getInstance()호출 후에 첫번쨰 검사에서 이미 인스턴스가 있으면 동기화 블록을 실행하지 않기 떄문에 성능 최적화

단점
1. 자바 1.5 이전 버전에서는 volatile 키워드가 작동하지 않아서 사용이 불가능
2. 코드가 다소 복잡하다
 */
public class DatabaseConnectionManager {
    //volatile -> 멀티 스레드 환경에서 변수의 가시성 보장
    private static DatabaseConnectionManager instance;

    //DB 연결 객체
    private Connection connection;

    //DB접속 정보
    private String url;
    private String username;
    private String password;

    //private -> 외부에서 접근 불가
    private DatabaseConnectionManager() {
        //기번 데이터베이스 정보
        this.url = "jdbc:mysql://localhost:3306/db";
        this.username = "user";
        this.password = "password";
    }

    DatabaseConnectionManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DatabaseConnectionManager getInstance() {
        //첫번째 검사
        if (instance == null) {
            //여러 스레드가 동시에 이블록에 접근하는것을 방지
            synchronized (DatabaseConnectionManager.class){
                if (instance == null) {
                    //두번째 검사
                    instance = new DatabaseConnectionManager();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    public Connection closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
        return connection;
    }

    //
    public static void  reset(){
        synchronized (DatabaseConnectionManager.class){
            if (instance != null) {
                try{
                    instance.closeConnection();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                instance = null;
            }
        }
    }
}
