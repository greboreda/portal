package com.greboreda.portal.business;

import java.io.Serializable;
import java.util.UUID;

public interface Id extends Serializable {
	UUID getUuid();
}
