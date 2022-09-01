package br.com.solipy.ariestreineapi.services.auth;

import br.com.solipy.ariestreineapi.config.ConnectionFactory;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;

@Service
public class HashidService {

    private ConnectionFactory connection;
    private Hashids hashid;

    public HashidService(){
        this.connection = new ConnectionFactory();
        hashid = new Hashids(connection.getHashidSalt());
    }

    public Hashids get(){
        return this.hashid;
    }

    public Hashids get(int length){
        return new Hashids(connection.getHashidSalt(), length);
    }

    public String toStringUserKey(String userKey){
        Long userId = get(32).decode(userKey)[0];
        return  userId.toString();
    }

    public Long toIntUserKey(String userKey){
        Long userId = get(32).decode(userKey)[0];
        return userId;
    }

    public Long toLongUserKey(String userKey){
        Long userId = get(32).decode(userKey)[0];
        return userId;
    }
}
