package raptor.modelLibrary.model.util;

import java.util.AbstractList;
import java.util.Arrays;

public class TimingList extends AbstractList<Integer> {
	private final int[] source;

	public TimingList(final int[] originalSource) {
		this.source = Arrays.copyOf(originalSource, originalSource.length);
	}

	public void set(final int index, final int value) {
		source[index] = value;
	}

	@Override
	public Integer get(final int index) {
		return source[index];
	}

	@Override
	public int size() {
		return source.length;
	}

}
