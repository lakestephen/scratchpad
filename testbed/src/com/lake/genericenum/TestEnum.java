package com.lake.genericenum;


/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public enum TestEnum implements ImageName {
	A (1, "TEST.png"),
	B (1, "My.png"),
	;
	
	int index;
	String imageName;
	
	TestEnum(int index, String imageName) {
		this.index = index;
		this.imageName = imageName;
	}

	@Override
	public String getImageName() {
		return imageName;
	}
}
