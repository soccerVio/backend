package soccervio.back.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private byte[] image;
    private String type;
}
