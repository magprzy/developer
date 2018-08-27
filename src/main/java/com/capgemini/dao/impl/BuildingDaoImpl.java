package com.capgemini.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.dao.custom.BuildingDaoCustom;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.QBuildingEntity;
import com.capgemini.domain.QClientEntity;
import com.capgemini.domain.QFlatEntity;
import com.capgemini.enums.FlatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class BuildingDaoImpl implements BuildingDaoCustom {

	@PersistenceContext
	EntityManager em;

	QFlatEntity qflat = QFlatEntity.flatEntity;
	QClientEntity qclient = QClientEntity.clientEntity;
	QBuildingEntity qbuilding = QBuildingEntity.buildingEntity;

	@Override
	public BuildingEntity findBuildingWithMaxFreeFlats() {

		JPAQueryFactory qf = new JPAQueryFactory(em);

		BuildingEntity result = qf.selectFrom(qbuilding).join(qbuilding.flats, qflat)
				.on(qflat.status.eq(FlatStatus.FREE)).groupBy(qbuilding).orderBy(qflat.count().desc()).limit(1)
				.fetchOne();

		return result;
	}
}
