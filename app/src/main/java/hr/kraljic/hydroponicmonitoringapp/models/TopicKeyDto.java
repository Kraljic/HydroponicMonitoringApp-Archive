package hr.kraljic.hydroponicmonitoringapp.models;

public class TopicKeyDto {
    private Integer id;
    private String name;
    private String topicKey;

    public TopicKeyDto() {
    }

    public TopicKeyDto(Integer id, String name, String topicKey) {
        this.id = id;
        this.name = name;
        this.topicKey = topicKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopicKey() {
        return topicKey;
    }

    public void setTopicKey(String topicKey) {
        this.topicKey = topicKey;
    }
}
