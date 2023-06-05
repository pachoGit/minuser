package com.minuser.MinUser.models;

import java.util.List;

/**
 * UserGetQueryParams
 */
public record UserEntityQueryParams(List<Long> id,
                                 String search,
                                 String name,
                                 String lastname,
                                 String document,
                                 String phone,
                                 String sort,
                                 String email) {

}
