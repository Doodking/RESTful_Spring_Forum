package mainApp.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import mainApp.Model.JsonViews.View;
import mainApp.Model.Post;
import mainApp.Model.User;
import mainApp.Repo.PostRepo;
import mainApp.dto.MetaDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("posts")
public class PostController {
    private static String URL_PATTERN = "https?:\\/\\/?[\\w\\d\\._\\-%\\/\\?=&#]+";
    private static String IMAGE_PATTERN = "\\.(jpeg|jpg|gif|png)$";

    private static Pattern URL_REGEX = Pattern.compile(URL_PATTERN, Pattern.CASE_INSENSITIVE);
    private static Pattern IMG_REGEX = Pattern.compile(IMAGE_PATTERN, Pattern.CASE_INSENSITIVE);

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
    public Post createPost(@RequestBody Post post, @AuthenticationPrincipal User user) throws IOException {
        post.setCreationTime(LocalDateTime.now());
        fillMeta(post);
        Post createdPost = postRepo.save(post);
        //wsSender.accept(EventType.CREATE, post);
        return createdPost;
    }

    @PutMapping("{id}")
    public Post updatePost(@PathVariable("id") Post postFromDb, @RequestBody Post post) throws IOException {
        BeanUtils.copyProperties(post, postFromDb, "id");
        fillMeta(postFromDb);
        Post updatedPost = postRepo.save(postFromDb);
        //wsSender.accept(EventType.UPDATE, updatedPost);
        return updatedPost;
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable("id") Post post){
        //wsSender.accept(EventType.REMOVE, post);
        postRepo.delete(post);
    }

    public void fillMeta(Post post) throws IOException {
        String text = post.getText();
        Matcher matcher = URL_REGEX.matcher(text);

        if(matcher.find()){
            String url = text.substring(matcher.start(), matcher.end());

            matcher = IMG_REGEX.matcher(url);
            post.setLink(url);
            if(matcher.find()){
                post.setLinkCover(url);
            }else if(!url.contains("youtu")){
                MetaDto meta = getMeta(url);

                post.setLinkCover(meta.getCover());
                post.setLinkTitle(meta.getTitle());
                post.setLinkDescription(meta.getDescription());
            }
        }

    }

    public MetaDto getMeta(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        Elements title = doc.select("meta[name$=title], meta[property$=title]");
        Elements description = doc.select("meta[name$=description], meta[property$=description]");
        Elements cover = doc.select("meta[name$=image], meta[property$=image]");;

        return new MetaDto(getContent(title.first()), getContent(description.first()), getContent(cover.first()));
    }

    public String getContent(Element element){
        return element == null ? "" : element.attr("content");
    }

    @MessageMapping("/changePost")
    @SendTo("/topic/activity")
    public Post change(Post post){
        return postRepo.save(post);
    }
}
