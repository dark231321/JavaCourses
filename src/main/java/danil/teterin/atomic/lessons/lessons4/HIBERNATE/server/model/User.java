package danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user", schema = "chat")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "login", unique = true, nullable = false, length = 20)
    private String login;
}
