public class Topic {
    // entity or field
    private Integer id;
    private String name;
    private String description ;
    private Boolean status;

    // constructor
    public Topic() {}

    public Topic(Integer id, String name, String description, Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    // method setter and getter
    public void setId(Integer id){
        this.id=id;
    }
    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    // method toString

    @Override
    public String toString() {
        return "Topics{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
