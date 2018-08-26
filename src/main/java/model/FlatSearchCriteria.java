package model;

public class FlatSearchCriteria {

	private Long minSize;
	private Long maxSize;
	private int minNumberOfRooms;
	private int maxNumberOfRooms;
	private int minNumberOfBalconies;
	private int maxNumberOfBalconies;
	

	public FlatSearchCriteria() {
		
	}

	
	public FlatSearchCriteria(Long minSize, Long maxSize, int minNumberOfRooms, int maxNumberOfRooms,
			int minNumberOfBalconies, int maxNumberOfBalconies) {
	
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


	public int getMinNumberOfRooms() {
		return minNumberOfRooms;
	}


	public void setMinNumberOfRooms(int minNumberOfRooms) {
		this.minNumberOfRooms = minNumberOfRooms;
	}


	public int getMaxNumberOfRooms() {
		return maxNumberOfRooms;
	}


	public void setMaxNumberOfRooms(int maxNumberOfRooms) {
		this.maxNumberOfRooms = maxNumberOfRooms;
	}


	public int getMinNumberOfBalconies() {
		return minNumberOfBalconies;
	}


	public void setMinNumberOfBalconies(int minNumberOfBalconies) {
		this.minNumberOfBalconies = minNumberOfBalconies;
	}


	public int getMaxNumberOfBalconies() {
		return maxNumberOfBalconies;
	}


	public void setMaxNumberOfBalconies(int maxNumberOfBalconies) {
		this.maxNumberOfBalconies = maxNumberOfBalconies;
	}

	
	
	
}
