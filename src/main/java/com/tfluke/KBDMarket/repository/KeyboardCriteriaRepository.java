package com.tfluke.KBDMarket.repository;

import com.tfluke.KBDMarket.model.Keyboard;
import com.tfluke.KBDMarket.model.KeyboardFilters;
import com.tfluke.KBDMarket.model.KeyboardPage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.security.Key;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class KeyboardCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public KeyboardCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }
    public Page<Keyboard> getAllWithFilters(KeyboardFilters keyboardFilters,
                                            KeyboardPage keyboardPage){
        CriteriaQuery<Keyboard> criteriaQuery =  criteriaBuilder.createQuery(Keyboard.class);
        Root<Keyboard> keyboardRoot =  criteriaQuery.from(Keyboard.class);
//        keyboardRoot.alias("alias1");

        Predicate predicate = buildPredicate(keyboardFilters,keyboardRoot);

        criteriaQuery.where(predicate);


        setOrder(keyboardPage,criteriaQuery,keyboardRoot);

        TypedQuery<Keyboard> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(keyboardPage.getPageNumber() * keyboardPage.getPageSize());
        typedQuery.setMaxResults(keyboardPage.getPageSize());
        List<Keyboard> list =  typedQuery.getResultList();
        Pageable pageable=getPageable(keyboardPage);


        long keyboardCount = getKeyboardCount(keyboardFilters);

        return new PageImpl<>(list, pageable, keyboardCount);

    }



    private Predicate buildPredicate(KeyboardFilters keyboardFilters, Root<Keyboard> keyboardRoot){
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(keyboardFilters.getBrand())){
            predicates.add(
                    criteriaBuilder.like(keyboardRoot.get("brand"),
                            "%" + keyboardFilters.getBrand() + "%")
            );
        }
        if(Objects.nonNull(keyboardFilters.getModel())){
            predicates.add(
                    criteriaBuilder.like(keyboardRoot.get("model"),
                            "%" + keyboardFilters.getModel() + "%")
            );
        }
        if(Objects.nonNull(keyboardFilters.getLayout())){
            predicates.add(
                    criteriaBuilder.like(keyboardRoot.get("layout"),
                            "%" + keyboardFilters.getLayout() + "%")
            );
        }
        if(Objects.nonNull(keyboardFilters.getColor())){
            predicates.add(
                    criteriaBuilder.like(keyboardRoot.get("color"),
                            "%" + keyboardFilters.getColor() + "%")
            );
        }
        if(Objects.nonNull(keyboardFilters.getSize())){
            predicates.add(
                    criteriaBuilder.equal(keyboardRoot.get("size"),
                            keyboardFilters.getSize())
            );
        }
        if(Objects.nonNull(keyboardFilters.getLed())){
            predicates.add(
                    criteriaBuilder.equal(keyboardRoot.get("led"),
                            keyboardFilters.getLed())
            );
        }
        if(Objects.nonNull(keyboardFilters.getMinPrice())){
            predicates.add(
                    criteriaBuilder.gt(keyboardRoot.get("price"),
                            keyboardFilters.getMinPrice())
            );
        }
        if(Objects.nonNull(keyboardFilters.getMaxPrice())){
            predicates.add(
                    criteriaBuilder.le(keyboardRoot.get("price"),
                            keyboardFilters.getMaxPrice())
            );
        }

        //predicate[0] to specify the array type
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
    private void setOrder(KeyboardPage keyboardPage,
                          CriteriaQuery<Keyboard> criteriaQuery,
                          Root<Keyboard> keyboardRoot) {
        if(keyboardPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(keyboardRoot.get(keyboardPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(keyboardRoot.get(keyboardPage.getSortBy())));
        }

    }
    private Pageable getPageable(KeyboardPage keyboardPage) {
        Sort sort = Sort.by(keyboardPage.getSortDirection(), keyboardPage.getSortBy());
        return PageRequest.of(keyboardPage.getPageNumber(),keyboardPage.getPageSize(), sort);
    }

    private long getKeyboardCount(KeyboardFilters keyboardFilters) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Keyboard> countRoot = countQuery.from(Keyboard.class);
        Predicate pr = buildPredicate(keyboardFilters,countRoot);
        countQuery.select(criteriaBuilder.count(countRoot));
        countQuery.where(pr);

        return entityManager.createQuery(countQuery).getSingleResult();
    }




}