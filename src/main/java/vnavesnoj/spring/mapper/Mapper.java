package vnavesnoj.spring.mapper;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface Mapper<F, T> {

    T map(F object);

    default T map(F fromObject, T toObject) {
        return toObject;
    }
}
