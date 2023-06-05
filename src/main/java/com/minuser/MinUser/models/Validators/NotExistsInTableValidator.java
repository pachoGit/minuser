package com.minuser.MinUser.models.Validators;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * ExistsInTableValidator
 */
public class NotExistsInTableValidator implements ConstraintValidator<NotExistsInTable, Object>
{
    private final EntityManager entityManager;

    private Class<?> entityClass;

    private String column;

    @Autowired
    public NotExistsInTableValidator(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(NotExistsInTable constraintAnnotation)
    {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.entityClass = constraintAnnotation.entityClass();
        this.column = constraintAnnotation.column();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<?> root = query.from(this.entityClass);
        query.select(builder.literal(1L)).where(builder.equal(root.get(this.column), value));

        return this.entityManager.createQuery(query).getResultList().size() == 0;
    }
}
