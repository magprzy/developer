package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.criteria.expression.function.AggregationFunction.COUNT;

import com.capgemini.dao.ClientDao;
import com.capgemini.dao.custom.ClientDaoCustom;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.QClientEntity;
import com.capgemini.domain.QFlatEntity;
import com.querydsl.core.types.dsl.SetPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ClientDaoImpl implements ClientDaoCustom{

	@PersistenceContext
	EntityManager em;

	JPAQueryFactory qf = new JPAQueryFactory(em);
	QFlatEntity qflat = QFlatEntity.flatEntity;
	QClientEntity qclient = QClientEntity.clientEntity;
	
	
	@Override
	public List<ClientEntity> findAllClientsWithAtLeastTwoFlats() {
		
	//List<ClientEntity> clients = qf.selectFrom(qclient)
			
		
		return null;
	}


	/*private int COUNT(SetPath<FlatEntity, QFlatEntity> flats, QFlatEntity qflat2) {
		// TODO Auto-generated method stub
		return 0;
	}*/
}
