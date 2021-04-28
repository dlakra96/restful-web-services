package com.elearning.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int userCount = 3;
	
	static {
		users.add(new User(1, "Deepanshu Lakra", new Date()));
		users.add(new User(2, "Sachin Gupta", new Date()));
		users.add(new User(3, "Manish Agarwal", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user)
	{
		if(user.getUserId() == null)
			user.setUserId(++userCount);
		users.add(user);
		return user;
	}
	
	public User findOne(int id)
	{
		for(User user : users)
			if(user.getUserId() == id)
				return user;
		return null;
	}
	
	public User deleteUserById(int userId)
	{
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext())
		{
			User user = iterator.next();
			if(user.getUserId() == userId)
			{
				iterator.remove();
				return user;
			}
		}
		return null;
		
	}
}
