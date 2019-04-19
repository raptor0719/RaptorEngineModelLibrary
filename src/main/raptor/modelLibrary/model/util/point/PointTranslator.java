package raptor.modelLibrary.model.util.point;

public class PointTranslator {
	private final int offsetX;
	private final int offsetY;

	public PointTranslator(final int offsetX, final int offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public int translateX(final int x) {
		return x + offsetX;
	}

	public int translateY(final int y) {
		return y + offsetY;
	}
}
