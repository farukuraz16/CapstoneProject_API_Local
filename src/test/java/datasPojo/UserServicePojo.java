package datasPojo;

public class UserServicePojo {
    /*
    {
    "id": 94,
    "name": "Enes Beraaaaaa",
    "lastname": "Urazzzzzz",
    "username": "Enesssssss",
    "email": "enesberauraz@example.com",
    "phone": "+1 123 123 4567",
    "address": "Mudanya",
    "country_id": "TR"
}
     */
    private String id;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String country_id;


    public UserServicePojo(String id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserServicePojo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", country_id='" + country_id + '\''+
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }



    public UserServicePojo(String id, String name, String lastname, String username, String email, String phone, String address, String country_id) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.country_id = country_id;

    }
}
