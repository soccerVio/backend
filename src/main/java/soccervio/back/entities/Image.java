package soccervio.back.entities;

import javax.persistence.*;

@Entity(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private byte[] content;
    private String type;

    public Image(byte[] content, String type) {
        this.content = content;
        this.type = type;
    }

    public Image() {
    }

    public Image(long id, byte[] content, String type) {
        this.id = id;
        this.content = content;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
