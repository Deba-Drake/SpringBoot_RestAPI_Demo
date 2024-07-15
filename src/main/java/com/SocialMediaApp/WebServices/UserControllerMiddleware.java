package com.SocialMediaApp.WebServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserControllerMiddleware implements CommandLineRunner
{
    @Autowired
    private UserRepository userrepo;

    // GET MULTIPLE METHOD
    public List<User> getallusers()
    {
        return userrepo.findAll();
    }

    //GET ONE METHOD
    public User getoneuser(Integer id)
    {
        return userrepo.findById(id).orElse(null);
    }

    // POST METHOD
    public User postuser(User user)
    {
        return userrepo.save(user);
    }

    // DELETE METHOD
    public void deleteuser(User user)
    {
        userrepo.delete(user);
    }

    //PUT METHOD
    public User putuser(User user, User userdetails)
    {
        user.setUsername(userdetails.getUsername());
        user.setBirthdate(userdetails.getBirthdate());
        return userrepo.save(user);
    }

    //PATCH NAME METHOD
    public User patchnameuser(User user, User userdetails)
    {
        user.setUsername(userdetails.getUsername());
        return userrepo.save(user);
    }

    //PATCH BIRTHDATE METHOD
    public User patchbirthdateuser(User user, User userdetails)
    {
        user.setBirthdate(userdetails.getBirthdate());
        return userrepo.save(user);
    }

    @Override
    public void run(String... args) throws Exception
    {
        System.out.println("The value is = "+userrepo.findById(2));
    }
}