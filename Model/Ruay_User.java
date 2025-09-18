package RuayRuayMain.Model;
public class Ruay_User {
    private int id;
    private String username;
    private String password;
    private String privilege;
    private String name;
    private int cus_id;

    public Ruay_User(int ID, String username, String password,String privilege, String name , int cus_id) {
        this.id = ID;
        this.username = username;
        this.password = password;
        this.privilege = privilege;
        this.name = name;
        this.cus_id = cus_id;
    }

    // Getters and Setters
    public int getcusId() {
        return cus_id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

