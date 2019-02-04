package raptor.modelLibrary.model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import raptor.modelLibrary.model.point.IPointReader;

public class Model {
	private Image currentSprite;
	private IPointReader position;
	private int direction;

	// Animation Timing Stuff
	private final Map<Integer, List<Integer>> animationTimingMap;

	private List<Integer> currentTimings;
	private int totalFramesRemaining = 0;
	private int countRemainingForCurrentFrame = 0;
	private int currentFrameIndex = 0;

	public Model(final ModelData base, final IPointReader position, final int initialDirection) {
		currentSprite = base.getDefaultFrame().getImage();
		this.position = position;
		direction = initialDirection;

		animationTimingMap = buildTimingMap(base.getAnimations());
	}

	public Image getCurrentSprite() {
		return currentSprite;
	}

	public IPointReader getPosition() {
		return position;
	}

	public void setDirection(final int newDirection) {
		direction = newDirection;
	}

	public int setAnimation(int id) {
		currentTimings = animationTimingMap.get(id);
		totalFramesRemaining = sumCounts(currentTimings);
		currentFrameIndex = 0;
		countRemainingForCurrentFrame = currentTimings.get(currentFrameIndex);

		return totalFramesRemaining;
	}

	/* Helper Methods */
	private Map<Integer, List<Integer>> buildTimingMap(final Map<Integer, Animation> animations) {
		final Map<Integer, List<Integer>> timingMap = new HashMap<>();

		for (final Entry<Integer, Animation> entry : animations.entrySet())
			timingMap.put(entry.getKey(), buildTimingList(entry.getValue()));

		return timingMap;
	}

	// FIXME: The math for distributing the leftover frames is all sorts of wrong
	private List<Integer> buildTimingList(final Animation animation) {
		final int totalFrames = animation.getDefaultFrameCount();
		final int totalFrameScale = getTotalFrameScale(animation.getTimings());

		final int framesPerUnit = totalFrames / totalFrameScale;
		final int leftoverFrames = totalFrames % totalFrameScale;

		final int highestPriorityFrame = getHighestPriority(animation.getTimings());

		final List<Integer> timingList = new ArrayList<>();

		for (int i = 0; i < animation.getTimings().size(); i++) {
			final int adjust = (i == highestPriorityFrame) ? leftoverFrames : 0;
			final int frameCount = (framesPerUnit * animation.getTimings().get(0).getScale()) + adjust;

			timingList.add(frameCount);
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

	private int sumCounts(final List<Integer> list) {
		int total = 0;

		for (final Integer i : list)
			total += i;

		return total;
	}
}
