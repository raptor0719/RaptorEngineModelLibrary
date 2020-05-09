package raptor.modelLibrary.api;

import raptor.modelLibrary.model.util.point.IRotatedPoint;
import raptor.modelLibrary.temp.DimensionalSprite;
import raptor.modelLibrary.temp.ModelDescriptor;

public interface Model {
	int getRemainingFrames();
	int setAnimation(int animationId);
	int setAnimation(String animationName);
	void setAnimationFrameCount(int animationId, int frameCount);
	void setAnimationFrameCount(String animationName, int frameCount);
	int advanceFrame();
	int advanceFrames(int count);
	void setDirection(int direction);
	void setSprite(int hardpointId, DimensionalSprite sprite);
	void setSprite(String hardpointName, DimensionalSprite sprite);
	void unsetSprite(int hardpointId);
	void unsetSprite(String hardpointName);
	IRotatedPoint getHardpointPosition(int hardpointId);
	IRotatedPoint getHardpointPosition(String hardpointName);
	DimensionalSprite getAttachedSprite(int hardpointId);
	DimensionalSprite getAttachedSprite(String hardpointName);
	ModelDescriptor getDescriptor();
}
