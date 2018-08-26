package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.custom.FlatDaoCustom;
import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.QFlatEntity;
import com.capgemini.enums.FlatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;

import model.FlatSearchCriteria;


@Repository
public class FlatDaoImpl implements FlatDaoCustom {

	@PersistenceContext
	EntityManager em;
	
	
	QFlatEntity qflat = QFlatEntity.flatEntity;
	
	@Override
	public List<FlatEntity> findFreeFlats(FlatSearchCriteria criteria) {
	/*	JPAQueryFactory qf = new JPAQueryFactory(em);
		List<FlatEntity> flats = qf.selectFrom(qflat).where(qflat.status.eq(FlatStatus.FREE), qflat.status).fetch();
		return flats;*/
		return null;
	}

	@Override
	public Long findPriceAllFlatsByCustomer(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlatEntity> findFreeFlats() {
		JPAQueryFactory qf = new JPAQueryFactory(em);
		List<FlatEntity> flats = qf.selectFrom(qflat).where(qflat.status.eq(FlatStatus.FREE)).fetch();
		return flats;
		
	}
	
	

}
