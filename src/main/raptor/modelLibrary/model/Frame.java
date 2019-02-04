package raptor.modelLibrary.model;

import java.awt.Image;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import raptor.modelLibrary.model.point.IPointReader;

public class Frame {
	private final Image image;
	private final List<IPointReader> pointPositions;

	public Frame(final Image image, final IPointReader[] pointPositions) {
		this.image = image;
		this.pointPositions = Collections.unmodifiableList(Arrays.asList(pointPositions));
	}

	public Image getImage() {
		return image;
	}

	public List<IPointReader> getHardpointPositions() {
		return pointPositions;
	}
}
