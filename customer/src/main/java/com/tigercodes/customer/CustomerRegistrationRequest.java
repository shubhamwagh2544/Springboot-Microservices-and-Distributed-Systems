package com.tigercodes.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {

}
