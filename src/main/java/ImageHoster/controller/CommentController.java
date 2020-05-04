package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.ImageService;
import ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComments(@RequestParam(name = "comment")String comment, @PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, Model model, HttpSession session) throws IOException {
        Comment comment1 = new Comment();

        Image image = imageService.getImage(imageId);
        comment1.setImage(image);
        User user = (User) session.getAttribute("loggeduser");
        comment1.setUser(user);
        comment1.setText(comment);
        comment1.setCreatedDate(LocalDate.now());
        Comment finalcomment = imageService.addComment(comment1);


        model.addAttribute("image", image);
        model.addAttribute("comments",finalcomment);

        return "images/image";

    }

}
