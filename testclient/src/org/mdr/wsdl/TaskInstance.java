
package org.mdr.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for taskInstance complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="taskInstance">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="admItem" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="task" type="{http://mdr.org/wsdl}formTask" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taskInstance", propOrder = {
    "admItem",
    "task"
})
public class TaskInstance {

    protected Long admItem;
    protected FormTask task;

    /**
     * Gets the value of the admItem property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAdmItem() {
        return admItem;
    }

    /**
     * Sets the value of the admItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAdmItem(Long value) {
        this.admItem = value;
    }

    /**
     * Gets the value of the task property.
     * 
     * @return
     *     possible object is
     *     {@link FormTask }
     *     
     */
    public FormTask getTask() {
        return task;
    }

    /**
     * Sets the value of the task property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormTask }
     *     
     */
    public void setTask(FormTask value) {
        this.task = value;
    }

}
