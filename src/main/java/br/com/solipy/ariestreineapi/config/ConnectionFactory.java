package br.com.solipy.ariestreineapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ConnectionFactory {

    private String jwtSecret = "9e09ef55a4144237bd059ec9843d65ac";
    private String hashidSalt = "ppcOpmT57PCY5EpwbiDmr6IfupC5K0RZ";
    private Long jwtExpirationInMs = 604800000l;
}
