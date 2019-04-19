package raptor.modelLibrary.model.util.point;

public class TranslatedPoint implements IRotatedPoint {
	private final IRotatedPoint reference;
	private final PointTranslator translator;

	public TranslatedPoint(final IRotatedPoint reference, final PointTranslator translator) {
		this.reference = reference;
		this.translator = translator;
	}

	@Override
	public int getX() {
		return translator.translateX(reference.getX());
	}

	@Override
	public int getY() {
		return translator.translateY(reference.getY());
	}

	@Override
	public int getRotation() {
		return reference.getRotation();
	}
}
