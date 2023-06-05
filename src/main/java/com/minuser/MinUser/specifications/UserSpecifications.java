package com.minuser.MinUser.specifications;

import java.util.List;

import com.minuser.MinUser.entities.UserEntity;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

/**
 * UserSpecifications
 */
public class UserSpecifications
{
    public static Specification<UserEntity> byIds(List<Long> ids)
    {
        return (root, query, builder) -> root.get("id").in(ids);
    }

    public static Specification<UserEntity> bySearch(String search)
    {
        return (root, query, builder) -> {
            String likeExpresion = "%" + search.toLowerCase() + "%";
            Predicate name = builder.like(builder.lower(root.get("name")), likeExpresion);
            Predicate lastname = builder.like(builder.lower(root.get("lastname")), likeExpresion);
            return builder.or(name, lastname);
        };
    }

    public static Specification<UserEntity> byName(String name)
    {
        return  (root, query, builder) -> {
            return builder.equal(root.get("name"), name);
        };
    }

    public static Specification<UserEntity> byLastname(String lastname)
    {
        return  (root, query, builder) -> {
            return builder.equal(root.get("lastname"), lastname);
        };
    }

    public static Specification<UserEntity> byDocument(String document)
    {
        return  (root, query, builder) -> {
            return builder.equal(root.get("document"), document);
        };
    }

    public static Specification<UserEntity> byPhone(String phone)
    {
        return  (root, query, builder) -> {
            return builder.equal(root.get("phone"), phone);
        };
    }

    public static Specification<UserEntity> byEmail(String email)
    {
        return  (root, query, builder) -> {
            return builder.equal(root.get("phone"), email);
        };
    }

    // NOTE: Sort only by two fields
    public static Specification<UserEntity> bySort(String sort)
    {
        return  (root, query, builder) -> {
            if (sort != null) {
                return query.orderBy(builder.desc(root.get(sort)), builder.desc(root.get("updatedAt"))).getRestriction();
            }
            else {
                return query.orderBy(builder.desc(root.get("updatedAt"))).getRestriction();
            }
        };
    }
}
