package com.minuser.MinUser.models;

/**
 * ResponseEstandar
 */

public record ResponseEstandar<Data>(int code, String status, String msg, Data data) {
}
