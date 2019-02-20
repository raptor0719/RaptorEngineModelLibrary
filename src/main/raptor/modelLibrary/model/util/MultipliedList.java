package raptor.modelLibrary.model.util;

import java.util.AbstractList;

public class MultipliedList<T> extends AbstractList<T> {
	private final AbstractList<T> provider;
	private final AbstractList<Integer> countsProvider;

	public MultipliedList(final AbstractList<T> provider, final AbstractList<Integer> countsProvider) {
		if (!isValid(provider, countsProvider))
			throw new RuntimeException("Base size and multiplier size did not match during initialization.");

		this.provider = provider;
		this.countsProvider = countsProvider;
	}

	@Override
	public T get(final int index) {
		if (index == 0)
			return provider.get(0);

		int working = index;
		int actualIndex = 0;
		for (final Integer i : countsProvider) {
			if (working < i)
				return provider.get(actualIndex);

			working -= i;
			actualIndex++;
		}

		throw new IndexOutOfBoundsException();
	}

	@Override
	public int size() {
		int total = 0;
		for (final Integer i : countsProvider)
			total += i;

		return total;
	}

	private boolean isValid(final AbstractList<T> base, final AbstractList<Integer> counts) {
		return base.size() == counts.size();
	}
}
