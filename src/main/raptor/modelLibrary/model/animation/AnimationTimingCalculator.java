package raptor.modelLibrary.model.animation;

import java.util.List;

import raptor.modelLibrary.model.FrameTiming;

public class AnimationTimingCalculator {
	public int[] calculateTiming(final Animation<?> animation, final int frameCount) {
		return buildTimingList(animation, frameCount);
	}

	public int[] calculateTiming(final Animation<?> animation) {
		return calculateTiming(animation, animation.getDefaultFrameCount());
	}

	/* HELPER METHODS */

	// FIXME: The math for distributing the leftover frames is all sorts of wrong
	private int[] buildTimingList(final Animation<?> animation, final int totalFrameCount) {
		final int totalFrames = totalFrameCount;
		final int totalFrameScale = getTotalFrameScale(animation.getTimingObjects());

		final int framesPerUnit = totalFrames / totalFrameScale;
		final int leftoverFrames = totalFrames % totalFrameScale;

		final int highestPriorityFrame = getHighestPriority(animation.getTimingObjects());

		final int[] timingList = new int[animation.getTimingObjects().size()];

		for (int i = 0; i < animation.getTimingObjects().size(); i++) {
			final int adjust = (i == highestPriorityFrame) ? leftoverFrames : 0;
			final int frameCount = (framesPerUnit * animation.getTimingObjects().get(0).getScale()) + adjust;

			timingList[i] = frameCount;
		}

		return timingList;
	}

	private int getTotalFrameScale(final List<FrameTiming> timings) {
		int total = 0;

		for (final FrameTiming f : timings)
			total += f.getScale();

		return total;
	}

	private int getHighestPriority(final List<FrameTiming> timings) {
		int frame = 0;
		int maxPriority = Integer.MIN_VALUE;

		for (int i = 0; i < timings.size(); i++) {
			final FrameTiming f = timings.get(i);
			if (f.getPriority() > maxPriority) {
				frame = i;
				maxPriority = f.getPriority();
			}
		}

		return frame;
	}
}
