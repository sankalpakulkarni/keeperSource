
package org.mdr.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.mdr.wsdl package. 
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

    private final static QName _UpdateContactInfo_QNAME = new QName("http://mdr.org/wsdl", "updateContactInfo");
    private final static QName _GetSupervisor_QNAME = new QName("http://mdr.org/wsdl", "getSupervisor");
    private final static QName _GetContactInfoResponse_QNAME = new QName("http://mdr.org/wsdl", "getContactInfoResponse");
    private final static QName _CreateStewardResponse_QNAME = new QName("http://mdr.org/wsdl", "createStewardResponse");
    private final static QName _Authenticate_QNAME = new QName("http://mdr.org/wsdl", "authenticate");
    private final static QName _CreateRegistrar_QNAME = new QName("http://mdr.org/wsdl", "createRegistrar");
    private final static QName _AuthenticateResponse_QNAME = new QName("http://mdr.org/wsdl", "authenticateResponse");
    private final static QName _GetContactInfo_QNAME = new QName("http://mdr.org/wsdl", "getContactInfo");
    private final static QName _UpdateContactInfoResponse_QNAME = new QName("http://mdr.org/wsdl", "updateContactInfoResponse");
    private final static QName _UpdatePasswordForUserResponse_QNAME = new QName("http://mdr.org/wsdl", "updatePasswordForUserResponse");
    private final static QName _CreateSubmitter_QNAME = new QName("http://mdr.org/wsdl", "createSubmitter");
    private final static QName _CreateRegistrarResponse_QNAME = new QName("http://mdr.org/wsdl", "createRegistrarResponse");
    private final static QName _UpdatePassword_QNAME = new QName("http://mdr.org/wsdl", "updatePassword");
    private final static QName _UpdatePasswordForUser_QNAME = new QName("http://mdr.org/wsdl", "updatePasswordForUser");
    private final static QName _CreateReadOnlyUserResponse_QNAME = new QName("http://mdr.org/wsdl", "createReadOnlyUserResponse");
    private final static QName _GetSupervisorResponse_QNAME = new QName("http://mdr.org/wsdl", "getSupervisorResponse");
    private final static QName _CreateSteward_QNAME = new QName("http://mdr.org/wsdl", "createSteward");
    private final static QName _RepositoryException_QNAME = new QName("http://mdr.org/wsdl", "RepositoryException");
    private final static QName _CreateSubmitterResponse_QNAME = new QName("http://mdr.org/wsdl", "createSubmitterResponse");
    private final static QName _RepositoryAuthenticationException_QNAME = new QName("http://mdr.org/wsdl", "RepositoryAuthenticationException");
    private final static QName _CreateReadOnlyUser_QNAME = new QName("http://mdr.org/wsdl", "createReadOnlyUser");
    private final static QName _UpdatePasswordResponse_QNAME = new QName("http://mdr.org/wsdl", "updatePasswordResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.mdr.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateRegistrar }
     * 
     */
    public CreateRegistrar createCreateRegistrar() {
        return new CreateRegistrar();
    }

    /**
     * Create an instance of {@link UpdatePassword }
     * 
     */
    public UpdatePassword createUpdatePassword() {
        return new UpdatePassword();
    }

    /**
     * Create an instance of {@link GetContactInfo }
     * 
     */
    public GetContactInfo createGetContactInfo() {
        return new GetContactInfo();
    }

    /**
     * Create an instance of {@link Authenticate }
     * 
     */
    public Authenticate createAuthenticate() {
        return new Authenticate();
    }

    /**
     * Create an instance of {@link Organization }
     * 
     */
    public Organization createOrganization() {
        return new Organization();
    }

    /**
     * Create an instance of {@link CreateRegistrarResponse }
     * 
     */
    public CreateRegistrarResponse createCreateRegistrarResponse() {
        return new CreateRegistrarResponse();
    }

    /**
     * Create an instance of {@link AuthenticateResponse }
     * 
     */
    public AuthenticateResponse createAuthenticateResponse() {
        return new AuthenticateResponse();
    }

    /**
     * Create an instance of {@link UpdateContactInfoResponse }
     * 
     */
    public UpdateContactInfoResponse createUpdateContactInfoResponse() {
        return new UpdateContactInfoResponse();
    }

    /**
     * Create an instance of {@link RegistrationAuthorityIdentifier }
     * 
     */
    public RegistrationAuthorityIdentifier createRegistrationAuthorityIdentifier() {
        return new RegistrationAuthorityIdentifier();
    }

    /**
     * Create an instance of {@link GetContactInfoResponse }
     * 
     */
    public GetContactInfoResponse createGetContactInfoResponse() {
        return new GetContactInfoResponse();
    }

    /**
     * Create an instance of {@link GetSupervisorResponse }
     * 
     */
    public GetSupervisorResponse createGetSupervisorResponse() {
        return new GetSupervisorResponse();
    }

    /**
     * Create an instance of {@link UpdatePasswordResponse }
     * 
     */
    public UpdatePasswordResponse createUpdatePasswordResponse() {
        return new UpdatePasswordResponse();
    }

    /**
     * Create an instance of {@link CreateStewardResponse }
     * 
     */
    public CreateStewardResponse createCreateStewardResponse() {
        return new CreateStewardResponse();
    }

    /**
     * Create an instance of {@link CreateSubmitter }
     * 
     */
    public CreateSubmitter createCreateSubmitter() {
        return new CreateSubmitter();
    }

    /**
     * Create an instance of {@link CreateSteward }
     * 
     */
    public CreateSteward createCreateSteward() {
        return new CreateSteward();
    }

    /**
     * Create an instance of {@link Submitter }
     * 
     */
    public Submitter createSubmitter() {
        return new Submitter();
    }

    /**
     * Create an instance of {@link Steward }
     * 
     */
    public Steward createSteward() {
        return new Steward();
    }

    /**
     * Create an instance of {@link GetSupervisor }
     * 
     */
    public GetSupervisor createGetSupervisor() {
        return new GetSupervisor();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link CreateSubmitterResponse }
     * 
     */
    public CreateSubmitterResponse createCreateSubmitterResponse() {
        return new CreateSubmitterResponse();
    }

    /**
     * Create an instance of {@link RepositoryException }
     * 
     */
    public RepositoryException createRepositoryException() {
        return new RepositoryException();
    }

    /**
     * Create an instance of {@link LanguageIdentification }
     * 
     */
    public LanguageIdentification createLanguageIdentification() {
        return new LanguageIdentification();
    }

    /**
     * Create an instance of {@link Contact }
     * 
     */
    public Contact createContact() {
        return new Contact();
    }

    /**
     * Create an instance of {@link Registrar }
     * 
     */
    public Registrar createRegistrar() {
        return new Registrar();
    }

    /**
     * Create an instance of {@link CreateReadOnlyUser }
     * 
     */
    public CreateReadOnlyUser createCreateReadOnlyUser() {
        return new CreateReadOnlyUser();
    }

    /**
     * Create an instance of {@link UpdatePasswordForUser }
     * 
     */
    public UpdatePasswordForUser createUpdatePasswordForUser() {
        return new UpdatePasswordForUser();
    }

    /**
     * Create an instance of {@link RepositoryAuthenticationException }
     * 
     */
    public RepositoryAuthenticationException createRepositoryAuthenticationException() {
        return new RepositoryAuthenticationException();
    }

    /**
     * Create an instance of {@link UpdateContactInfo }
     * 
     */
    public UpdateContactInfo createUpdateContactInfo() {
        return new UpdateContactInfo();
    }

    /**
     * Create an instance of {@link CreateReadOnlyUserResponse }
     * 
     */
    public CreateReadOnlyUserResponse createCreateReadOnlyUserResponse() {
        return new CreateReadOnlyUserResponse();
    }

    /**
     * Create an instance of {@link RegistrationAuthority }
     * 
     */
    public RegistrationAuthority createRegistrationAuthority() {
        return new RegistrationAuthority();
    }

    /**
     * Create an instance of {@link UpdatePasswordForUserResponse }
     * 
     */
    public UpdatePasswordForUserResponse createUpdatePasswordForUserResponse() {
        return new UpdatePasswordForUserResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateContactInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "updateContactInfo")
    public JAXBElement<UpdateContactInfo> createUpdateContactInfo(UpdateContactInfo value) {
        return new JAXBElement<UpdateContactInfo>(_UpdateContactInfo_QNAME, UpdateContactInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSupervisor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "getSupervisor")
    public JAXBElement<GetSupervisor> createGetSupervisor(GetSupervisor value) {
        return new JAXBElement<GetSupervisor>(_GetSupervisor_QNAME, GetSupervisor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetContactInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "getContactInfoResponse")
    public JAXBElement<GetContactInfoResponse> createGetContactInfoResponse(GetContactInfoResponse value) {
        return new JAXBElement<GetContactInfoResponse>(_GetContactInfoResponse_QNAME, GetContactInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateStewardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "createStewardResponse")
    public JAXBElement<CreateStewardResponse> createCreateStewardResponse(CreateStewardResponse value) {
        return new JAXBElement<CreateStewardResponse>(_CreateStewardResponse_QNAME, CreateStewardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Authenticate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "authenticate")
    public JAXBElement<Authenticate> createAuthenticate(Authenticate value) {
        return new JAXBElement<Authenticate>(_Authenticate_QNAME, Authenticate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRegistrar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "createRegistrar")
    public JAXBElement<CreateRegistrar> createCreateRegistrar(CreateRegistrar value) {
        return new JAXBElement<CreateRegistrar>(_CreateRegistrar_QNAME, CreateRegistrar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "authenticateResponse")
    public JAXBElement<AuthenticateResponse> createAuthenticateResponse(AuthenticateResponse value) {
        return new JAXBElement<AuthenticateResponse>(_AuthenticateResponse_QNAME, AuthenticateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetContactInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "getContactInfo")
    public JAXBElement<GetContactInfo> createGetContactInfo(GetContactInfo value) {
        return new JAXBElement<GetContactInfo>(_GetContactInfo_QNAME, GetContactInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateContactInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "updateContactInfoResponse")
    public JAXBElement<UpdateContactInfoResponse> createUpdateContactInfoResponse(UpdateContactInfoResponse value) {
        return new JAXBElement<UpdateContactInfoResponse>(_UpdateContactInfoResponse_QNAME, UpdateContactInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePasswordForUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "updatePasswordForUserResponse")
    public JAXBElement<UpdatePasswordForUserResponse> createUpdatePasswordForUserResponse(UpdatePasswordForUserResponse value) {
        return new JAXBElement<UpdatePasswordForUserResponse>(_UpdatePasswordForUserResponse_QNAME, UpdatePasswordForUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSubmitter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "createSubmitter")
    public JAXBElement<CreateSubmitter> createCreateSubmitter(CreateSubmitter value) {
        return new JAXBElement<CreateSubmitter>(_CreateSubmitter_QNAME, CreateSubmitter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRegistrarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "createRegistrarResponse")
    public JAXBElement<CreateRegistrarResponse> createCreateRegistrarResponse(CreateRegistrarResponse value) {
        return new JAXBElement<CreateRegistrarResponse>(_CreateRegistrarResponse_QNAME, CreateRegistrarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "updatePassword")
    public JAXBElement<UpdatePassword> createUpdatePassword(UpdatePassword value) {
        return new JAXBElement<UpdatePassword>(_UpdatePassword_QNAME, UpdatePassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePasswordForUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "updatePasswordForUser")
    public JAXBElement<UpdatePasswordForUser> createUpdatePasswordForUser(UpdatePasswordForUser value) {
        return new JAXBElement<UpdatePasswordForUser>(_UpdatePasswordForUser_QNAME, UpdatePasswordForUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateReadOnlyUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "createReadOnlyUserResponse")
    public JAXBElement<CreateReadOnlyUserResponse> createCreateReadOnlyUserResponse(CreateReadOnlyUserResponse value) {
        return new JAXBElement<CreateReadOnlyUserResponse>(_CreateReadOnlyUserResponse_QNAME, CreateReadOnlyUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSupervisorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "getSupervisorResponse")
    public JAXBElement<GetSupervisorResponse> createGetSupervisorResponse(GetSupervisorResponse value) {
        return new JAXBElement<GetSupervisorResponse>(_GetSupervisorResponse_QNAME, GetSupervisorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSteward }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "createSteward")
    public JAXBElement<CreateSteward> createCreateSteward(CreateSteward value) {
        return new JAXBElement<CreateSteward>(_CreateSteward_QNAME, CreateSteward.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RepositoryException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "RepositoryException")
    public JAXBElement<RepositoryException> createRepositoryException(RepositoryException value) {
        return new JAXBElement<RepositoryException>(_RepositoryException_QNAME, RepositoryException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSubmitterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "createSubmitterResponse")
    public JAXBElement<CreateSubmitterResponse> createCreateSubmitterResponse(CreateSubmitterResponse value) {
        return new JAXBElement<CreateSubmitterResponse>(_CreateSubmitterResponse_QNAME, CreateSubmitterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RepositoryAuthenticationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "RepositoryAuthenticationException")
    public JAXBElement<RepositoryAuthenticationException> createRepositoryAuthenticationException(RepositoryAuthenticationException value) {
        return new JAXBElement<RepositoryAuthenticationException>(_RepositoryAuthenticationException_QNAME, RepositoryAuthenticationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateReadOnlyUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "createReadOnlyUser")
    public JAXBElement<CreateReadOnlyUser> createCreateReadOnlyUser(CreateReadOnlyUser value) {
        return new JAXBElement<CreateReadOnlyUser>(_CreateReadOnlyUser_QNAME, CreateReadOnlyUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mdr.org/wsdl", name = "updatePasswordResponse")
    public JAXBElement<UpdatePasswordResponse> createUpdatePasswordResponse(UpdatePasswordResponse value) {
        return new JAXBElement<UpdatePasswordResponse>(_UpdatePasswordResponse_QNAME, UpdatePasswordResponse.class, null, value);
    }

}
