package mainApp.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import mainApp.Model.JsonViews.View;
import mainApp.Model.Post;
import mainApp.Repo.PostRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    private PostRepo postRepo;

    //private final BiConsumer<EventType, Post> wsSender;

    /*@Autowired
    public PostController(PostRepo postRepo, WSSender wsSender){
        this.postRepo = postRepo;
        this.wsSender = wsSender.getSender(ObjectType.POST, View.IdText.class);
    }*/

    @GetMapping
    @JsonView(View.IdText.class)
    public Iterable<Post> getPosts(){
        return postRepo.findAll();
    }

    @GetMapping("{id}")
    public Post getPost(@PathVariable("id") Post post){
        return post;
    }

    @PostMapping
    public Post createPost(@RequestBody Post post){
        post.setCreationTime(LocalDateTime.now());
        Post createdPost = postRepo.save(post);
        //wsSender.accept(EventType.CREATE, post);
        return createdPost;
    }

    @PutMapping("{id}")
    public Post updatePost(@PathVariable("id") Post postFromDb, @RequestBody Post post){
        BeanUtils.copyProperties(post, postFromDb, "id");
        Post updatedPost = postRepo.save(postFromDb);
        //wsSender.accept(EventType.UPDATE, updatedPost);
        return updatedPost;
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable("id") Post post){
        //wsSender.accept(EventType.REMOVE, post);
        postRepo.delete(post);
    }

    @MessageMapping("/changePost")
    @SendTo("/topic/activity")
    public Post change(Post post){
        return postRepo.save(post);
    }
}
