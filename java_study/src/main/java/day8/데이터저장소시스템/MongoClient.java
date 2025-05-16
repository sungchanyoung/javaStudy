package day8.데이터저장소시스템;

public class MongoClient {

    public void save(String data){
        System.out.println("mongo save data: " + data + " success");
    }
    public String findById(String id){
        System.out.println("Data found in mongoDB"+id);
        return "Data found in mongoDB " + id;
    }
}
