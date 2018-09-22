package org.example.test;

public enum ResourcesFactoryTestURL {

	TEST("/org/example/test/test-native-query.xml");
	
	private String context;

    private ResourcesFactoryTestURL(final String context) {
        this.context = context;
    }

    public String getContext() {
		return context;
	}

}
