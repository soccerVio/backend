package soccervio.back.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
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
}
