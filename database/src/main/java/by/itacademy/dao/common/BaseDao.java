package by.itacademy.dao.common;

import by.itacademy.entity.otherEntity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {
    T getByID(Long id);
    Long save(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
}
