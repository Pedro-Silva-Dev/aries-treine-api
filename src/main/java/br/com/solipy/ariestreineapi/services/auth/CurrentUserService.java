package br.com.solipy.ariestreineapi.services.auth;

import br.com.solipy.ariestreineapi.models.auth.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
public class CurrentUserService {

    private final HashidService hashidService;

    public UserRequest get(){
        UserRequest userRequest = (UserRequest) ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getAttribute("userRequest");
        return userRequest;
    }

    public Long getId(){
        UserRequest userRequest = get();
        if(Objects.nonNull(userRequest) && Objects.nonNull(userRequest.getUserKey())) {
            Long userId = hashidService.toIntUserKey(userRequest.getUserKey());
            return (userId > 0) ? userId : null;
        }
        return null;
    }

}
