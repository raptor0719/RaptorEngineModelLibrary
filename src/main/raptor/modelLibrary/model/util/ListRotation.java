package raptor.modelLibrary.model.util;

import java.util.AbstractList;

public class ListRotation<T> {
	private AbstractList<T> provider;
	private int currentIndex;

	public ListRotation(final AbstractList<T> initialProvider) {
		this.provider = initialProvider;
		currentIndex = 0;
	}

	public void setProvider(final AbstractList<T> newProvider, boolean keepIndex) {
		this.provider = newProvider;

		if (currentIndex >= newProvider.size())
			keepIndex = false;

		currentIndex = (keepIndex) ? currentIndex : 0;
	}

	public void setProvider(final AbstractList<T> newProvider) {
		this.setProvider(newProvider, false);
	}

	public void advance(final int count) {
		currentIndex = (currentIndex + count) % provider.size();
	}

	public void advance() {
		this.advance(1);
	}

	public T getCurrent() {
		return provider.get(currentIndex);
	}

	public int size() {
		return provider.size() - currentIndex;
	}

	public int totalSize() {
		return provider.size();
	}
}
