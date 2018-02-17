package com.greboreda.portal.business;

import java.io.Serializable;

public interface ValueObject extends Serializable {

	boolean equals(Object other);
}
