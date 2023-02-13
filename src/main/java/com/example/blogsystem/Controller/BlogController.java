package com.example.blogsystem.Controller;

import com.example.blogsystem.Dto.ApiResponse;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    private BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService=blogService;
    }

  //  Add All CRUD
    @GetMapping("/all/bllogs")
    public ResponseEntity All(){
        return ResponseEntity.status(200).body(blogService.GetAllBlogs());
    }

    @PostMapping ("/add")
    public ResponseEntity AddBlogs(@Valid @RequestBody Blog blog, @AuthenticationPrincipal User user){
        blogService.AddBlog(user.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog Added !!"));
    }
    @PutMapping("/update/{blog_id}")
    public ResponseEntity UpdateBlogs(@PathVariable Integer blog_id , @Valid @RequestBody Blog blog , @AuthenticationPrincipal User user){
        blogService.UpdateBlog(blog_id,blog,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Blog updated !!"));
    }
    @DeleteMapping("/delete/{blog_id}")
    public ResponseEntity DeleteBlog(@PathVariable Integer blog_id ,@AuthenticationPrincipal User user){
        blogService.DeleteBlog(blog_id,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Blog deleted !!"));
    }
   // Get All user blogs
    @GetMapping("/all/blogs")
    public ResponseEntity GetAllUserBlogs(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.GetAllUserBlog(user.getId()));
    }

   // Get blog by Id
    @GetMapping("/blog/{Blog_id}")
    public ResponseEntity GetBlogById(@PathVariable Integer Blog_id , @AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.GetBlogById(Blog_id, user.getId()));
    }

   // Get blog by title
    @GetMapping("/blogtitle/{title}")
    public ResponseEntity GetBlogByTitle(@PathVariable String title , @AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.BlogByTitle(title, user.getId()));

    }



}
