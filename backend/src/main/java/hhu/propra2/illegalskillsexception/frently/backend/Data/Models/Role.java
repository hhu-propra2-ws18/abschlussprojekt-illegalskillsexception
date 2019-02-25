package hhu.propra2.illegalskillsexception.frently.backend.Data.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role {

    // JETZT AUCH IN IHREM REPO
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String description;
}
