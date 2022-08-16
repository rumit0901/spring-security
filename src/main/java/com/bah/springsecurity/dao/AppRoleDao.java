package com.bah.springsecurity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bah.springsecurity.entity.UserRole;

@Repository
@Transactional
public class AppRoleDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<String> getRoleNames(Long userid) {
		
		String sql = "SELECT ur.appRole.roleName FROM " + UserRole.class.getName() + " ur "
				+ "WHERE ur.appUser.userId = :userid";
		
		Query query = entityManager.createQuery(sql, String.class);
		query.setParameter(":userid", userid);
		
		return query.getResultList();
	}

}
