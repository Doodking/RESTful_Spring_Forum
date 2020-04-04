package mainApp.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import mainApp.Model.JsonViews.View;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "posts")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.IdText.class)
    private Integer id;
    @JsonView(View.IdText.class)
    private String text;

    @Column(name = "date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    @JsonView(View.Full.class)
    private LocalDateTime creationTime;

    @JsonView(View.Full.class)
    private String link;

    @JsonView(View.Full.class)
    private String linkTitle;

    @JsonView(View.Full.class)
    private String linkDescription;

    @JsonView(View.Full.class)
    private String linkCover;

    public Post(){}
    public Post(Integer id, String text){
        this.id = id;
        this.text = text;
    }

    public static Post getById(Integer id, List<Post> posts){
        for(Post post : posts){
            if(post.getId() == id){
                return post;
            }
        }
        return new Post();
    }
}
