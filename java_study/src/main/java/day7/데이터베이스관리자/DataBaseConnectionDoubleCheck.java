package day7.데이터베이스관리자;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * double CheckedLocing 싱글톤 패턴
 *  1. 지연 초기화 - 실제로 쓰기전까지 인스턴스를 생성하지 않기 떄문에 메모리 호율적
 *  2. 멀티 스레드 환경에서 안전하게 작동
 *  3. getInstance() 호출 후에 첫번쨰 검사에서 이미 인스턴스가 있으면 동기화 블록을 실행하지 않기
 *  떄문에 성능 최적화
 *
 */
public class DataBaseConnectionDoubleCheck {
    //volatile -멀티스레그 환경에서 가시성 보장
    //데이터 인스턴스 생성
    private static volatile DataBaseConnectionDoubleCheck instance;

    //데이터베이스 연결 객체
    private Connection connection;

    private final String url;
    private final String username;
    private final String password;

    //생성자가 private으로 설정
    private DataBaseConnectionDoubleCheck() {
        this.url = "jdbc:mysql://localhost:3306/db";
        this.username = "user";
        this.password = "password";
    }

    //싱글톤 패턴의 getInstance 메소드
     DataBaseConnectionDoubleCheck(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    //싱글톤 패턴의 getInstance 메소드
    public static DataBaseConnectionDoubleCheck getInstance() {
        if (instance == null) {
            //여러 스레드가 동시에 이블록에 접근하는것을 방지
            synchronized (DataBaseConnectionDoubleCheck.class) {
                if (instance == null) {
                    instance = new DataBaseConnectionDoubleCheck();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException{
        if(connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
    
    public static void reset(){
        synchronized (DataBaseConnectionDoubleCheck.class){
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

    private void closeConnection()throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }
}
