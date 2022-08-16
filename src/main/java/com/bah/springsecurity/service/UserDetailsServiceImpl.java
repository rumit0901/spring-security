package com.bah.springsecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bah.springsecurity.dao.AppRoleDao;
import com.bah.springsecurity.dao.AppUserDao;
import com.bah.springsecurity.entity.AppUser;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserDao appUserDao;
	
	@Autowired
	private AppRoleDao appRoleDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		AppUser appUser = appUserDao.findUserAccount(username);
		
		if(appUser == null) {
			System.out.println("User Not Found " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		
		System.out.println("Found User: " + appUser);
		
		List<String> roleNames = appRoleDao.getRoleNames(appUser.getUserId());
		
		List<GrantedAuthority> grandList = new ArrayList<GrantedAuthority>();
		if(roleNames != null) {
			for(String role: roleNames) {
				
				grandList.add(new SimpleGrantedAuthority(role));
			}
		}
		
		UserDetails userDetails = new User(appUser.getUserName(),
				appUser.getEncrytedPassword(), grandList);
		
		return userDetails;
	}

}
