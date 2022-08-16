package com.bah.springsecurity.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bah.springsecurity.entity.AppUser;

@Repository
@Transactional
public class AppUserDao {

	@Autowired
	private EntityManager entityManager;
	
	public AppUser findUserAccount(String userName) {
		
		try {
			
			String sql = "SELECT e from " + AppUser.class.getName() + " e "
					+ "WHERE e.username = :username";
			Query query = entityManager.createQuery(sql, AppUser.class);
			query.setParameter(":username", userName);
		
		return (AppUser) query.getSingleResult();
		} catch(NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
}
