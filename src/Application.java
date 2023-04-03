
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("=========== DataBase =============");
            System.out.println("1 -> Select records ");
            System.out.println("2 -> Select a records by ID ");
            System.out.println("3 -> Select record by Name ");
            System.out.println("4 -> Insert a record");
            System.out.println("5 -> Update a record by ID ");
            System.out.println("6 -> Delete a record by ID ");
            System.out.println("==================================");
            System.out.print("Choose one options : ");
            int op = scanner.nextInt();
            switch (op) {
                case 1 -> {
                    JdbcImp jdbc = new JdbcImp();
                    System.out.println("=========== Select Data ================");
                    try (Connection con = jdbc.dataSource().getConnection()) {
                        String selectUrl = "SELECT * FROM topics";
                        PreparedStatement statement = con.prepareStatement(selectUrl);
                        ResultSet resultSet = statement.executeQuery();
                        List<Topic> list = new ArrayList<>();
                        while (resultSet.next()) {
                            Integer id = resultSet.getInt("id");
                            String name = resultSet.getString("name");
                            String description = resultSet.getString("description");
                            Boolean status = resultSet.getBoolean("status");
                            list.add(new Topic(id, name, description, status));
                        }
                        list.stream().forEach(System.out::println);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                case 2 -> {
                    JdbcImp jdbc = new JdbcImp();
                    Integer SID;
                    System.out.println("============= Select a record by ID ===============");
                    System.out.print("Enter ID to search record : ");
                    SID = scanner.nextInt();
                    try (Connection con = jdbc.dataSource().getConnection()) {
                        String selectUrl = "SELECT * FROM topics where id = ?";
                        PreparedStatement statement = con.prepareCall(selectUrl);
                        statement.setInt(1,SID);
                        ResultSet resultSet = statement.executeQuery();
                        List<Topic> list = new ArrayList<>();
                        while (resultSet.next()) {
                            Integer id = resultSet.getInt("id");
                            String name = resultSet.getString("name");
                            String description = resultSet.getString("description");
                            Boolean status = resultSet.getBoolean("status");
                            list.add(new Topic(id, name, description, status));
                        }
                        list.stream().forEach(System.out::println);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 3 ->{
                    JdbcImp jdbc = new JdbcImp();
                    System.out.println("===============  Select record by Name ===================");
                    System.out.print("Enter name to search record : ");
                    scanner.nextLine();
                    String Sname = scanner.nextLine();
                    try(Connection con = jdbc.dataSource().getConnection()) {
                        String selectUrl = "SELECT * FROM topics where name = ? ";
                        PreparedStatement statement =con.prepareCall(selectUrl);
                        statement.setString(1,Sname);
                        ResultSet resultSet = statement.executeQuery();
                        List<Topic> list = new ArrayList<>();
                        while (resultSet.next()) {
                            Integer id = resultSet.getInt("id");
                            String name = resultSet.getString("name");
                            String description = resultSet.getString("description");
                            Boolean status = resultSet.getBoolean("status");
                            list.add(new Topic(id, name, description, status));
                        }
                        list.stream().forEach(System.out::println);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 4 ->{
                    Topic topic = new Topic();
                    System.out.print("Enter name : ");
                    scanner.nextLine();
                    topic.setName(scanner.nextLine());
                    System.out.print("Enter Description : ");
                    scanner.nextLine();
                    topic.setDescription(scanner.nextLine());
                    System.out.print("Enter Status : ");
                    topic.setStatus(scanner.nextBoolean());
                    List<Topic> list = new ArrayList<>();
                    list.add(topic);
                    JdbcImp jdbc = new JdbcImp();
                    try(Connection con = jdbc.dataSource().getConnection()) {
                        String insertUrl = "INSERT INTO topics (name,description,status) "+
                                "VALUES( ? , ?, ?)";
                        PreparedStatement statement = con.prepareStatement(insertUrl);
                        statement.setString(1,topic.getName());
                        statement.setString(2, topic.getDescription());
                        statement.setBoolean(3, topic.getStatus());
                        int count= statement.executeUpdate();
                        ResultSet resultSet = statement.executeQuery();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 5 ->{
                    JdbcImp jdbc = new JdbcImp();
                    Topic topic = new Topic();
                    System.out.println("=================== Update data =====================");
                    try(Connection con = jdbc.dataSource().getConnection()){
                        //Update
                        String updateUrl = "UPDATE topics set name = ?, description = ? , status= ? ,where id = ?";
                        PreparedStatement statement = con.prepareCall(updateUrl);
                        statement.setString(1,topic.getName());
                        statement.setString(2,topic.getDescription());
                        statement.setBoolean(3,topic.getStatus());
                        int resultUpdate = statement.executeUpdate();
                        System.out.println(resultUpdate);
                    }catch (SQLException sqlException){
                        sqlException.printStackTrace();
                    }
                    System.out.println(">> Updated successfully <<");
                }
                case 6 ->{
                    JdbcImp jdbc = new JdbcImp();
                    System.out.println("===============  Deleted record by Name ===================");
                    System.out.print("Enter ID  to delete record : ");
                    Integer DID = scanner.nextInt();
                    try(Connection con = jdbc.dataSource().getConnection()) {
                        String selectUrl = "DELETE FROM topics where id = ?";
                        PreparedStatement statement =con.prepareCall(selectUrl);
                        statement.setInt(1,DID);
                        int resultSet = statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }while (true) ;
    }
}
