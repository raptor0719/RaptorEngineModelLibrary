package raptor.modelLibrary.api;

import raptor.modelLibrary.model.animation.frame.Sprite;
import raptor.modelLibrary.model.util.point.IRotatedPoint;

public interface Model {
	int getRemainingFrames();
	int setAnimation(int id);
	int advanceFrame();
	void setDirection(int direction);
	void attachModel(Model model, int hardpointId, int offX, int offY, int offRot);
	void removeModel(int hardpointId);
	IRotatedPoint getHardpointPosition(int id);
	void setModel(Model model, int hardpointId);
	void unsetModel(int hardpointId);
	Sprite getModelSprite();
}
