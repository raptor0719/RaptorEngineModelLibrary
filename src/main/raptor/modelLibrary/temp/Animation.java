package raptor.modelLibrary.temp;

import java.util.AbstractList;

import raptor.modelLibrary.model.FrameTiming;
import raptor.modelLibrary.model.animation.frame.Frame;

public class Animation {
	private static final AnimationTimingCalculator animTiming = new AnimationTimingCalculator();

	private final AbstractList<AbstractList<Frame>> frames;
	private final AbstractList<FrameTiming> timings;
	private final int defaultFrameCount;

	public Animation(final AbstractList<AbstractList<Frame>> frames, final AbstractList<FrameTiming> timings, final int defaultFrameCount) {
		this.frames = frames;
		this.timings = timings;
		this.defaultFrameCount = defaultFrameCount;
	}

	public AbstractList<AbstractList<Frame>> getFrames() {
		return frames;
	}

	public int[] getTimings(final int frameCount) {
		return animTiming.calculateTiming(this, frameCount);
	}

	public int[] getTimings() {
		return animTiming.calculateTiming(this, defaultFrameCount);
	}

	protected int getDefaultFrameCount() {
		return defaultFrameCount;
	}

	protected AbstractList<FrameTiming> getTimingObjects() {
		return timings;
	}
}
