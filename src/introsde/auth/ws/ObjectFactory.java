
package introsde.auth.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the introsde.auth.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAccessTokenResponse_QNAME = new QName("http://ws.auth.introsde/", "getAccessTokenResponse");
    private final static QName _GetAuthenticationUrl_QNAME = new QName("http://ws.auth.introsde/", "getAuthenticationUrl");
    private final static QName _GetAuthenticationUrlResponse_QNAME = new QName("http://ws.auth.introsde/", "getAuthenticationUrlResponse");
    private final static QName _GetAccessToken_QNAME = new QName("http://ws.auth.introsde/", "getAccessToken");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: introsde.auth.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAccessToken }
     * 
     */
    public GetAccessToken createGetAccessToken() {
        return new GetAccessToken();
    }

    /**
     * Create an instance of {@link GetAuthenticationUrlResponse }
     * 
     */
    public GetAuthenticationUrlResponse createGetAuthenticationUrlResponse() {
        return new GetAuthenticationUrlResponse();
    }

    /**
     * Create an instance of {@link GetAuthenticationUrl }
     * 
     */
    public GetAuthenticationUrl createGetAuthenticationUrl() {
        return new GetAuthenticationUrl();
    }

    /**
     * Create an instance of {@link GetAccessTokenResponse }
     * 
     */
    public GetAccessTokenResponse createGetAccessTokenResponse() {
        return new GetAccessTokenResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccessTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.auth.introsde/", name = "getAccessTokenResponse")
    public JAXBElement<GetAccessTokenResponse> createGetAccessTokenResponse(GetAccessTokenResponse value) {
        return new JAXBElement<GetAccessTokenResponse>(_GetAccessTokenResponse_QNAME, GetAccessTokenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthenticationUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.auth.introsde/", name = "getAuthenticationUrl")
    public JAXBElement<GetAuthenticationUrl> createGetAuthenticationUrl(GetAuthenticationUrl value) {
        return new JAXBElement<GetAuthenticationUrl>(_GetAuthenticationUrl_QNAME, GetAuthenticationUrl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthenticationUrlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.auth.introsde/", name = "getAuthenticationUrlResponse")
    public JAXBElement<GetAuthenticationUrlResponse> createGetAuthenticationUrlResponse(GetAuthenticationUrlResponse value) {
        return new JAXBElement<GetAuthenticationUrlResponse>(_GetAuthenticationUrlResponse_QNAME, GetAuthenticationUrlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccessToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.auth.introsde/", name = "getAccessToken")
    public JAXBElement<GetAccessToken> createGetAccessToken(GetAccessToken value) {
        return new JAXBElement<GetAccessToken>(_GetAccessToken_QNAME, GetAccessToken.class, null, value);
    }

}
