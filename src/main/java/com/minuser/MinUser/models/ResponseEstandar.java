package com.minuser.MinUser.models;

/**
 * ResponseEstandar
 */

public record ResponseEstandar<Data>(Integer code, String status, String msg, Data data) {
}
