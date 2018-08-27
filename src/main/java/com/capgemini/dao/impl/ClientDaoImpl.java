package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.dao.custom.ClientDaoCustom;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.QClientEntity;
import com.capgemini.domain.QFlatEntity;
import com.capgemini.enums.FlatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ClientDaoImpl implements ClientDaoCustom {

	@PersistenceContext
	EntityManager em;

	QFlatEntity qflat = QFlatEntity.flatEntity;
	QClientEntity qclient = QClientEntity.clientEntity;

	@Override
	public List<ClientEntity> findAllClientsWithMinTwoFlats() {

		JPAQueryFactory qf = new JPAQueryFactory(em);
		List<ClientEntity> result = qf.selectFrom(qclient).join(qclient.flats, qflat)
				.on(qflat.status.eq(FlatStatus.SOLD)).groupBy(qclient).having(qflat.count().gt(1)).fetch();
		return result;

	}

}
