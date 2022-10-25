package org.dgtech.sms.sevice;

import org.springframework.lang.Nullable;

public interface FileUploads {
	
	default boolean isEmpty(@Nullable String str) {
		return str == null || "".equals(str.trim());
	}

}
