package com.example.blogsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.blogsystem.Model.Blog;

import java.util.List;
import com.example.blogsystem.Model.User;
@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    Blog findBlogById(Integer id);
    List<Blog> findAllByUser(User user);

    Blog findBlogByTitle(String title);

}

