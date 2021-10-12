package orm.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pinguin {
    @Id
    Integer id;

    @Basic
    String name;
}
