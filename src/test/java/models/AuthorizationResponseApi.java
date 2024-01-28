package models;

import lombok.Data;

@Data

public class AuthorizationResponseApi {
    String userId,  token, expires;

}
