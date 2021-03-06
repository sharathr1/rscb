/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.domain;

import java.util.List;

import lombok.*;

@Getter
@Setter
public class ResponseObj<T> {
	private Long count;
	private List<T> data;
}
