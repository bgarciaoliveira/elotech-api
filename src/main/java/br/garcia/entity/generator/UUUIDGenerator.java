package br.garcia.entity.generator;

import br.garcia.util.UUUID;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

// não apagar essa classe, ela é usada por reflection
public class UUUIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object object){
        return UUUID.generate();
    }
}