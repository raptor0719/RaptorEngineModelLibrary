package raptor.modelLibrary.temp;

import java.util.AbstractList;

import raptor.modelLibrary.model.FrameTiming;
import raptor.modelLibrary.model.animation.frame.WiredFrame;

public class Animation {
	private static final AnimationTimingCalculator animTiming = new AnimationTimingCalculator();

	private final String name;
	private final AbstractList<AbstractList<WiredFrame>> frames;
	private final AbstractList<FrameTiming> timings;
	private final int defaultFrameCount;

	public Animation(final String name, final AbstractList<AbstractList<WiredFrame>> frames, final AbstractList<FrameTiming> timings, final int defaultFrameCount) {
		this.name = name;
		this.frames = frames;
		this.timings = timings;
		this.defaultFrameCount = defaultFrameCount;
	}

	public String getName() {
		return name;
	}

	public AbstractList<AbstractList<WiredFrame>> getFrames() {
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
