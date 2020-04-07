package com.quince.rentingapp.domain;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;

@Component
public class Utils {
    public static Mapper mapper;

    public static <A> A mapper(Object object, Class<A> bClass){
        if(mapper == null) {
            mapper = new DozerBeanMapper();
        }
        return mapper.map(object, bClass);
    }

    public static void mapper(Object source, Object target) {
        if (mapper == null) {
            mapper = new DozerBeanMapper();
        }
        mapper.map(source, target);
    }

}
