package com.demo.slk.application.airtel_wynk.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.slk.application.airtel_wynk.pojo.UserArtist;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Repository
@Slf4j
public class FollowerDaoManager {
	@PersistenceContext
	private EntityManager entityManager;
	public void followUserArtist(String userId,String artistId) throws Throwable{
		log.info("userId is "+userId +" artist Id is "+artistId);
		if(!artistAlreadyFollowedByuserId(userId,artistId)){
			UserArtist userArtist=new UserArtist();
			userArtist.setArtistId(artistId);
			userArtist.setUserId(userId);
			entityManager.persist(userArtist);
		}
	}
	public void unFollowUserArtist(String userId,String artistId) throws Throwable{
		log.info("userId is "+userId +" artist Id is "+artistId);
		if(artistAlreadyFollowedByuserId(userId,artistId)){
			String hql = "FROM UserArtist as ua where ua.userId='"+userId +"' and ua.artistId='"+artistId+"'";
			entityManager.createQuery(hql).executeUpdate();
			String playHql = "FROM Item as item where item.userId='"+userId +"' and item.artistId='"+artistId+"'";
			entityManager.createQuery(playHql).executeUpdate();
		}
	}
	public boolean artistAlreadyFollowedByuserId(String userId,String artistId) throws Throwable{
		log.info("userId is "+userId +" artist Id is "+artistId);
		String hql = "FROM UserArtist as ua where ua.userId='"+userId +"' and ua.artistId='"+artistId+"'";
		int count=entityManager.createQuery(hql).getFirstResult();
		return count<1?false:true;
	}
}
