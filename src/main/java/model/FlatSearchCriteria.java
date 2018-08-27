package model;

public class FlatSearchCriteria {

	private Long minSize;
	private Long maxSize;
	private Long minNumberOfRooms;
	private Long maxNumberOfRooms;
	private Long minNumberOfBalconies;
	private Long maxNumberOfBalconies;
	

	public FlatSearchCriteria() {
		
	}

	
	public FlatSearchCriteria(Long minSize, Long maxSize, Long minNumberOfRooms, Long maxNumberOfRooms,
			Long minNumberOfBalconies, Long maxNumberOfBalconies) {
	
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.minNumberOfRooms = minNumberOfRooms;
		this.maxNumberOfRooms = maxNumberOfRooms;
		this.minNumberOfBalconies = minNumberOfBalconies;
		this.maxNumberOfBalconies = maxNumberOfBalconies;
	}



	public Long getMinSize() {
		return minSize;
	}


	public void setMinSize(Long minSize) {
		this.minSize = minSize;
	}


	public Long getMaxSize() {
		return maxSize;
	}


	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}


	public Long getMinNumberOfRooms() {
		return minNumberOfRooms;
	}


	public void setMinNumberOfRooms(Long minNumberOfRooms) {
		this.minNumberOfRooms = minNumberOfRooms;
	}


	public Long getMaxNumberOfRooms() {
		return maxNumberOfRooms;
	}


	public void setMaxNumberOfRooms(Long maxNumberOfRooms) {
		this.maxNumberOfRooms = maxNumberOfRooms;
	}


	public Long getMinNumberOfBalconies() {
		return minNumberOfBalconies;
	}


	public void setMinNumberOfBalconies(Long minNumberOfBalconies) {
		this.minNumberOfBalconies = minNumberOfBalconies;
	}


	public Long getMaxNumberOfBalconies() {
		return maxNumberOfBalconies;
	}


	public void setMaxNumberOfBalconies(Long maxNumberOfBalconies) {
		this.maxNumberOfBalconies = maxNumberOfBalconies;
	}

	
	
	
}
