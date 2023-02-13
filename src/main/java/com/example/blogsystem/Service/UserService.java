package com.example.blogsystem.Service;


import com.example.blogsystem.Exception.ApiException;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.MyUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@Service
public class UserService {
    private MyUserRepository myUserRepository;

    public UserService(MyUserRepository myUserRepository){
        this.myUserRepository=myUserRepository;
    }
    public List<User> GetAllUsers(){
        return myUserRepository.findAll();
    }
    public void AddUser(User user){
        String hashedPassword=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        myUserRepository.save(user);
    }
    public User GetUser(Integer user_id){
        User user = myUserRepository.findUserById(user_id);
        if(user==null){
            throw new ApiException("user id not found");
        }
        return user;
    }


    public void UpdateUser(Integer user_id , User newuser){
        User old_user =myUserRepository.findUserById(user_id);
        if(old_user==null){
            throw new ApiException("user id not found");
        }
        newuser.setId(user_id);
        newuser.setRole(old_user.getRole());
        newuser.setPassword(new BCryptPasswordEncoder().encode(newuser.getPassword()));
        myUserRepository.save(newuser);
    }

    public void DeleteUser(Integer user_id){
        User user = myUserRepository.findUserById(user_id);
        if(user==null){
            throw new ApiException("user id not found");
        }
        myUserRepository.delete(user);
    }
}
