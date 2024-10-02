package fr.hb.jg.centrale.service.interfaces;

public interface ServiceInterface<T, L, C, U> {

    T create(C o);

    T update(U o, L id);

    Boolean delete(L o);

    T findOneById(L id);

}
