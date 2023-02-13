package com.example.blogsystem.Service;

import com.example.blogsystem.Exception.ApiException;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.BlogRepository;
import com.example.blogsystem.Repository.MyUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BlogService {
    private BlogRepository blogRepository;
    private MyUserRepository myUserRepository;

    public BlogService(BlogRepository blogRepository, MyUserRepository myUserRepository){
        this.blogRepository=blogRepository;
        this.myUserRepository=myUserRepository;
    }

    public List<Blog> GetAllBlogs(){
        return blogRepository.findAll();
    }

    public void AddBlog(Integer user_id ,Blog blog){
        User user = myUserRepository.findUserById(user_id);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void UpdateBlog(Integer blog_id , Blog blog,Integer user_id ){
        User user = myUserRepository.findUserById(user_id);
        Blog UpdateBlog = blogRepository.findBlogById(blog_id);
        if(UpdateBlog==null){
            throw new ApiException("blog id not found");
        }
        else if(UpdateBlog.getUser().getId()!=user_id){
            throw new ApiException("You do not have the authority to update!");
        }
        //id - title - body (Add All validation required)
        UpdateBlog.setTitle(blog.getTitle());
        UpdateBlog.setBody(blog.getBody());
        UpdateBlog.setUser(user);
        blogRepository.save(UpdateBlog);
    }
    public void  DeleteBlog(Integer blog_id ,Integer user_id){
        Blog delete_blog = blogRepository.findBlogById(blog_id);
        if(delete_blog==null){
            throw new ApiException("blog id not found!");
        } else if (delete_blog.getUser().getId()!=user_id) {
            throw new ApiException("You do not have the authority to delete!");
        }
        blogRepository.delete(delete_blog);
    }

    //Get All user blogs
    public List GetAllUserBlog(Integer user_id){
        User user =myUserRepository.findUserById(user_id);
        List<Blog> all_blogs = blogRepository.findAllByUser(user);
        if(user_id==null){
            throw new ApiException("user id not found!");
        }
        else if(user.getBlogList().isEmpty()){
            throw new ApiException("you don't have a blog");
        }
        return all_blogs;

    }


    //Get blog by Id
    public Blog GetBlogById(Integer blog_id,Integer user_id){
        Blog blog = blogRepository.findBlogById(blog_id);
        if(blog==null){
            throw new ApiException("blog id not found!");
        } else if (blog.getUser().getId()!=user_id) {
            throw new ApiException("You do not have the authority to search!");
        }

        return blog;
    }
    // get blog by title
    public Blog BlogByTitle(String title,Integer user_id){
        Blog blog = blogRepository.findBlogByTitle(title);
        if(blog==null){
            throw new ApiException("blog title not found!");
        } else if (blog.getUser().getId()!=user_id) {
            throw new ApiException("You do not have the authority to search!");
        }
        return blog;
    }

}
