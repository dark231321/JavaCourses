package danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "message", schema = "chat")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "time")
    private Date time;

    @NotBlank
    @Column(name = "value", length = 140)
    private String value;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"user_from\"")
    private User userTo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"user_to\"")
    private User userFrom;
}
