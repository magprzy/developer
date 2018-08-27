package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.custom.FlatDaoCustom;
import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.QBuildingEntity;
import com.capgemini.domain.QClientEntity;
import com.capgemini.domain.QFlatEntity;
import com.capgemini.enums.FlatStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import model.FlatSearchCriteria;

@Repository
public class FlatDaoImpl implements FlatDaoCustom {

	@PersistenceContext
	EntityManager em;

	QFlatEntity qflat = QFlatEntity.flatEntity;
	QClientEntity qclient = QClientEntity.clientEntity;
	QBuildingEntity qbuilding = QBuildingEntity.buildingEntity;

	@Override
	public List<FlatEntity> findFreeFlats(FlatSearchCriteria criteria) {
		Long minSize = criteria.getMinSize();
		Long maxSize = criteria.getMaxSize();
		Long minRooms = criteria.getMinNumberOfRooms();
		Long maxRooms = criteria.getMaxNumberOfRooms();
		Long minBalconies = criteria.getMinNumberOfBalconies();
		Long maxBalconies = criteria.getMaxNumberOfBalconies();

		JPAQueryFactory qf = new JPAQueryFactory(em);

		BooleanBuilder predicate = new BooleanBuilder();

		if (minSize != null && maxSize != null) {
			predicate.and(qflat.size.between(minSize, maxSize));
		}
		if (minRooms != null && maxRooms != null) {
			predicate.and(qflat.numberOfRooms.between(minRooms, maxRooms));
		}
		if (minBalconies != null && maxBalconies != null) {
			predicate.and(qflat.numberOfBalconies.between(minBalconies, maxBalconies));
		}
		List<FlatEntity> flats = qf.selectFrom(qflat).where(qflat.status.eq(FlatStatus.FREE), predicate).fetch();
		return flats;

	}

	@Override
	public Long findPriceAllFlatsByCustomer(Long clientId) {
		JPAQueryFactory qf = new JPAQueryFactory(em);
		Long result = qf.selectFrom(qflat).select(qflat.price.sum())
				.where(qflat.clients.any().id.eq(clientId), qflat.status.eq(FlatStatus.SOLD)).fetchOne();

		return result;

	}

	@Override
	public List<FlatEntity> findFreeFlats() {
		JPAQueryFactory qf = new JPAQueryFactory(em);
		List<FlatEntity> flats = qf.selectFrom(qflat).where(qflat.status.eq(FlatStatus.FREE)).fetch();
		return flats;

	}

	@Override
	public Double countAveragePriceOfFlatInBuilding(Long buildingID) {
		JPAQueryFactory qf = new JPAQueryFactory(em);
		Double average = qf.selectFrom(qflat).select(qflat.price.avg()).leftJoin(qflat.building, qbuilding)
				.where(qbuilding.id.eq(buildingID)).fetchOne();
		return average;
	}

	@Override
	public Long countFlatWithStatusInBuilding(FlatStatus status, Long buildingId) {
		JPAQueryFactory qf = new JPAQueryFactory(em);
		Long result = qf.selectFrom(qflat).select(qflat.count()).where(qflat.building.id.eq(buildingId), qflat.status.eq(status)).fetchOne();
		return result;
	}
	
	

}
