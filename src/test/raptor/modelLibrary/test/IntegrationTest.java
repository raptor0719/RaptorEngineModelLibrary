package raptor.modelLibrary.test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import raptor.modelLibrary.model.util.DimensionalList;
import raptor.modelLibrary.model.util.ListRotation;
import raptor.modelLibrary.model.util.MultipliedList;

public class IntegrationTest {

	@Test
	public void test() {
		final AbstractList<Integer> timings = createList(5, 3, 1, 4);

		// We specifically set the ones place and the index to the be same.
		// With the timings specified above, we have: 5 of index 0, 3 of index 1, 1 of index 2, and 4 of index 3.
		final MultipliedList<Integer> dims1_dim0 = new MultipliedList<Integer>(createList(0, 1, 2, 3), timings);
		final MultipliedList<Integer> dims1_dim1 = new MultipliedList<Integer>(createList(10, 11, 12, 13), timings);
		final MultipliedList<Integer> dims1_dim2 = new MultipliedList<Integer>(createList(20, 21, 22, 23), timings);
		final MultipliedList<Integer> dims1_dim3 = new MultipliedList<Integer>(createList(30, 31, 32, 33), timings);

		final List<AbstractList<Integer>> dims1 = new ArrayList<>();
		dims1.add(dims1_dim0);
		dims1.add(dims1_dim1);
		dims1.add(dims1_dim2);
		dims1.add(dims1_dim3);

		final DimensionalList<Integer> dimList1 = new DimensionalList<Integer>((AbstractList<AbstractList<Integer>>) dims1);

		final ListRotation<Integer> rot = new ListRotation<>(dimList1);

		/*
		Here we specify that we want 15 total numbers.

		The first 4 are the default dimension of 0 (tens place of 0), and there are 5 of index 0. So the first 4 digits are '0' '0' '0' '0'.

		The next 3 we set to dimension 2 (tens place of 2).
		We still have 1 of index 0, so '20', and then the next 3 are index 1, so '21' and '21'.

		The next 3 we set to dimension 3 (tens place of 3).
		We still have 1 of index 1 (we only used 2 so far), so'31'.
		We only need 1 for index 2, so '32'. Then we start index 3, so '33'.

		The last 5 we set to dimension 1 (tens place of 1).
		We still have 3 of index 3 (we only used 1 so far), so '13' '13' '13'.

		This now finishes all indices specified by the timings, but the list auto-rotates back to the beginning.
		We still need 2 more and we are still in dimension 1, so the last 2 are '10' and '10'.
		*/
		final int[] result = new int[15];
		for (int i = 0; i < result.length; i++) {
			if (i == 4)
				dimList1.setDimension(2);
			if (i == 7)
				dimList1.setDimension(3);
			if (i == 10)
				dimList1.setDimension(1);
			result[i] = rot.advance();
		}

		final int[] expected = {0, 0, 0, 0,
								20, 21, 21,
								31, 32, 33,
								13, 13, 13,
								10, 10};

		Assert.assertArrayEquals(expected, result);
	}

	/* HELPER METHODS */

	private AbstractList<Integer> createList(final Integer... ints) {
		final List<Integer> newList = new ArrayList<>(ints.length);

		for (final Integer i : ints)
			newList.add(i);

		return (AbstractList<Integer>) newList;
	}
}
