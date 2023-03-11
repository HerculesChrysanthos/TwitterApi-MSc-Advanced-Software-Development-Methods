package com.twitter.persistence;

import com.twitter.domain.EmailAddress;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailCustomType implements UserType {

    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        if ( x == y) return true;
        if ( x== null || y==null) return false;
        return x.equals(y);
    }

    public int hashCode(Object value) throws HibernateException {
        return value.hashCode();
    }

    public boolean isMutable() {
        return false;
    }

    public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
        String stringValue = resultSet.getString(names[0]);
        if ( resultSet.wasNull()) {
            return null;
        }
        EmailAddress eMail = new EmailAddress(stringValue);
        return eMail;
    }

    public void nullSafeSet(PreparedStatement statement, Object value, int index) throws HibernateException, SQLException {
        if (value == null) {
            statement.setNull(index, java.sql.Types.VARCHAR);
        }
        else {
            EmailAddress eMail = (EmailAddress) value;
            if (eMail.getEmail() == null ) {
                statement.setNull(index, java.sql.Types.VARCHAR);
            } else {
                statement.setString(index, eMail.getEmail());
            }
        }
    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }


    @SuppressWarnings("rawtypes")
    public Class returnedClass() {
        return EmailAddress.class;
    }

    public int[] sqlTypes() {
        return new int [] { java.sql.Types.VARCHAR };
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor arg2, Object arg3)
            throws HibernateException, SQLException {
        return nullSafeGet(resultSet, names, arg3);
    }

    @Override
    public void nullSafeSet(PreparedStatement statement, Object value, int index, SharedSessionContractImplementor arg3)
            throws HibernateException, SQLException {
        nullSafeSet(statement, value, index);
    }
}
