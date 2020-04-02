package mainApp.Model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usr")
@Data
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String userpic;
    private String email;
    private String sex;
    private String locale;
    private LocalDateTime lastVisit;
}
