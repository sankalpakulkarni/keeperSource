
package org.mdr.wsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for keeperRole.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="keeperRole">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="system"/>
 *     &lt;enumeration value="accreditor"/>
 *     &lt;enumeration value="registrar"/>
 *     &lt;enumeration value="steward"/>
 *     &lt;enumeration value="submitter"/>
 *     &lt;enumeration value="readOnlyUser"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "keeperRole")
@XmlEnum
public enum KeeperRole {

    @XmlEnumValue("system")
    SYSTEM("system"),
    @XmlEnumValue("accreditor")
    ACCREDITOR("accreditor"),
    @XmlEnumValue("registrar")
    REGISTRAR("registrar"),
    @XmlEnumValue("steward")
    STEWARD("steward"),
    @XmlEnumValue("submitter")
    SUBMITTER("submitter"),
    @XmlEnumValue("readOnlyUser")
    READ_ONLY_USER("readOnlyUser");
    private final String value;

    KeeperRole(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static KeeperRole fromValue(String v) {
        for (KeeperRole c: KeeperRole.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
