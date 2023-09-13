package pojo;

import java.util.List;

public class UserDetails {

    private String name;
    private String job;
    private List<CityRequestBody> city;
    private String id;
    private String createdAt;
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public List<CityRequestBody> getCity() {
        return city;
    }

    public void setCity(List<CityRequestBody> city) {
        this.city = city;
    }
}
