package com.volkov.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CertificateModel {

    private String url;
    private Integer rangeValidation;
    private boolean validation;
}
