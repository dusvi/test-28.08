package dao;


import exception.DaoException;

import java.util.List;

public interface IDao<T,K>{
    int create(T element) throws DaoException;
    List<T> ricercaPerCognome(String cognome) throws DaoException;
    T ricercaPerCodFiscale(String codFiscale) throws DaoException;

}
