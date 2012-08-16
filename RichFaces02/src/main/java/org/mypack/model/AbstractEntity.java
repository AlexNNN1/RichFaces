package org.mypack.model;

import static org.hibernate.proxy.HibernateProxyHelper.getClassWithoutInitializingProxy;
import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class AbstractEntity implements Serializable {
	public abstract Integer getId();
	
	@Override
	public boolean equals(Object other){
		if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }

        Class<?> selfClass = getClassWithoutInitializingProxy(this);
        Class<?> otherClass = getClassWithoutInitializingProxy(other);
        if (!selfClass.equals(otherClass)) {
            return false;
        }

        final AbstractEntity otherEntity = (AbstractEntity) other;
        return getId() != null && getId().equals(otherEntity.getId());
	}
	
	@Override
	public int hashCode() {
		return getId() != null ? getId() : System.identityHashCode(this);
	}
	
	@Override
	public String toString() {
		return super.toString() + " id=" + getId();
	}

}
