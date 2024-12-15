package com.volkov.client.manager;

import javax.security.cert.Certificate;
import java.net.URISyntaxException;
import java.util.List;

public interface CertificateRestClient {

    List<Certificate> findAllCertificates(String filter) throws URISyntaxException;
}
