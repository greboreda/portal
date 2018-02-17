package com.greboreda.portal.business;

import java.io.Serializable;

public interface Entity<T extends Id> extends Serializable {
	T getId();
}
