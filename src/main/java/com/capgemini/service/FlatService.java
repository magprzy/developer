package com.capgemini.service;

import java.util.List;

import com.capgemini.types.FlatTO;

public interface FlatService {
	
	FlatTO addFlat(FlatTO flat);
	
	void removeFlat(Long flatId);
	
	FlatTO findFlatById(Long flatId);
	
	List<FlatTO> findAllFlats();
	
	void updateFlat(FlatTO flat);
	
	void addFlatToBuilding(Long flatId, Long buildingId);
	
}
