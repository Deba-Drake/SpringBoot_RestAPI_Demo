package com.SocialMediaApp.WebServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserController
{
    @Autowired
    UserControllerMiddleware ucm = new UserControllerMiddleware();

    @GetMapping(path = "/users")
    public List<User> returnallusers()
    {
        return ucm.getallusers();
    }

    @GetMapping(path = "/user/{id}")
    public User returnsingleuser(@PathVariable Integer id)
    {
        if (ucm.getoneuser(id)==null)
        {
            throw new UserNotFoundException("User not found for the ID: "+id);
        }
        else return ucm.getoneuser(id);

    }

    @PostMapping("/user")
    public ResponseEntity<User> createsingleuser(@RequestBody User user)
    {
        if(user.getUsername()!=null && user.getUsername()!="")
        {
            User saveduser = ucm.postuser(user);
            ucm.getoneuser(saveduser.getId());

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        else
        {
            throw new UsernamenullException("User name cannot be empty");
        }
    }

    @DeleteMapping(path = "/user/{id}")
    public void deletesingleuser(@PathVariable Integer id)
    {
        User todeleteuser = ucm.getoneuser(id);
        ucm.deleteuser(todeleteuser);
    }

    @PutMapping(path = "/user/{id}")
    public User updatewholesingleuser(@PathVariable Integer id,@RequestBody User userupdatedetails)
    {
        User toupdateuser = ucm.getoneuser(id);
        return ucm.putuser(toupdateuser,userupdatedetails);
    }

    @PatchMapping(path = "/user/{id}/name")
    public User updatenamesingleuser(@PathVariable Integer id,@RequestBody User userupdatedetails)
    {
        User toupdateuser = ucm.getoneuser(id);
        return  ucm.patchnameuser(toupdateuser,userupdatedetails);
    }

    @PatchMapping(path = "/user/{id}/birthdate")
    public User updatebirthdatesingleuser(@PathVariable Integer id,@RequestBody User userupdatedetails)
    {
        User toupdateuser = ucm.getoneuser(id);
        return  ucm.patchbirthdateuser(toupdateuser,userupdatedetails);
    }

    private MessageSource msgsrc;

    public UserController(MessageSource messageSource)
    {
        this.msgsrc=messageSource;
    }

    @GetMapping("/good-morning/{id}")
    public String message(@PathVariable String id)
    {
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println(locale);
        return msgsrc.getMessage
                (
                        "good.morning.message",
                        null,
                        "Default Message",
                        locale
                );
        //return "Hello and Good Morning"+id;
    }
}