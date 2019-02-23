package raptor.modelLibrary.model.util;

public interface ITransformer<S,D> {
	D transform(S source);
}
