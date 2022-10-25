package org.dgtech.sms.constants;

public enum UserManagement {/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
	
	TOKEN_SPLITER(":"),
    SMS("SMS"),
    PARENTS_CONNECT("PC"),
	TEACHERS_CONNECT("TC"),
    SUPER_ADMIN("1");
//    CREATE_USER(1,"To create users,assign roles");
	
	private String value;

	UserManagement(String value) {
		this.value=value;
		// TODO Auto-generated constructor stub
	}


}
