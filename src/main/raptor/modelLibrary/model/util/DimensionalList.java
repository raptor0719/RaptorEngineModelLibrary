package raptor.modelLibrary.model.util;

import java.util.AbstractList;
import java.util.List;

public class DimensionalList<T> extends AbstractList<T> {
	private final AbstractList<AbstractList<T>> lists;
	private int currentDimenesion;

	public DimensionalList(final AbstractList<AbstractList<T>> lists) {
		if (!isValid(lists))
			throw new RuntimeException("Invalid list provided to dimensional list during initialization.");

		this.lists = lists;
		currentDimenesion = 0;
	}

	public void setDimension(final int newDimension) {
		this.currentDimenesion = newDimension;
	}

	@Override
	public T get(int index) {
		return lists.get(currentDimenesion).get(index);
	}

	@Override
	public int size() {
		return lists.get(0).size();
	}

	private boolean isValid(final AbstractList<AbstractList<T>> lists) {
		if (lists.isEmpty())
			return false;

		int count = lists.get(0).size();

		for (final List<T> l : lists) {
			if (l.size() != count)
				return false;
		}

		return true;
	}
}
