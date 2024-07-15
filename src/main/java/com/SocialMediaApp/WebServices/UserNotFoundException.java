package com.SocialMediaApp.WebServices;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String message)
    {
        super(message);;
    }
}