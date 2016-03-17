/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hops.kafka;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paul + misganu
 */
@Entity
@Table(name = "topic_acls", catalog = "hopsworks", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TopicAcls.findAll", query = "SELECT t FROM TopicAcls t"),
    @NamedQuery(name = "TopicAcls.findByTopicName", query = "SELECT t FROM TopicAcls t WHERE t.topicAclsPK.topicName = :topicName"),
    @NamedQuery(name = "TopicAcls.findByUserId", query = "SELECT t FROM TopicAcls t WHERE t.topicAclsPK.userId = :userId"),
    @NamedQuery(name = "TopicAcls.findByPermissionType", query = "SELECT t FROM TopicAcls t WHERE t.permissionType = :permissionType"),
    @NamedQuery(name = "TopicAcls.findByOperationType", query = "SELECT t FROM TopicAcls t WHERE t.operationType = :operationType"),
    @NamedQuery(name = "TopicAcls.findByHost", query = "SELECT t FROM TopicAcls t WHERE t.host = :host"),
    @NamedQuery(name = "TopicAcls.findByRole", query = "SELECT t FROM TopicAcls t WHERE t.role = :role")})
public class TopicAcls implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TopicAclsPK topicAclsPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "permission_type")
    private String permissionType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "operation_type")
    private String operationType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "host")
    private String host;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "role")
    private String role;
    @JoinColumn(name = "topic_name", 
            referencedColumnName = "topic_name", 
            insertable = false, 
            updatable = false)
    @ManyToOne(optional = false)
    private ProjectTopics projectTopics;

    
    public TopicAcls() {
    }

    public TopicAcls(TopicAclsPK topicAclsPK) {
        this.topicAclsPK = topicAclsPK;
    }

    public TopicAcls(TopicAclsPK topicAclsPK, String permissionType, String operationType, String host, String role) {
        this.topicAclsPK = topicAclsPK;
        this.permissionType = permissionType;
        this.operationType = operationType;
        this.host = host;
        this.role = role;
    }

    public TopicAcls(String topicName, String userId) {
        this.topicAclsPK = new TopicAclsPK(topicName, userId);
    }

    public TopicAclsPK getTopicAclsPK() {
        return topicAclsPK;
    }

    public void setTopicAclsPK(TopicAclsPK topicAclsPK) {
        this.topicAclsPK = topicAclsPK;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ProjectTopics getProjectTopics() {
        return projectTopics;
    }

    public void setProjectTopics(ProjectTopics projectTopics) {
        this.projectTopics = projectTopics;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (topicAclsPK != null ? topicAclsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TopicAcls)) {
            return false;
        }
        TopicAcls other = (TopicAcls) object;
        if ((this.topicAclsPK == null && other.topicAclsPK != null) || (this.topicAclsPK != null && !this.topicAclsPK.equals(other.topicAclsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "io.hops.kafka.TopicAcls[ topicAclsPK=" + topicAclsPK + " ]";
    }
    
}
