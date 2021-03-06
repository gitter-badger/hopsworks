
package se.kth.hopsworks.hdfsUsers.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "hops.hdfs_users")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "HdfsUsers.findAll",
          query
          = "SELECT h FROM HdfsUsers h"),
  @NamedQuery(name = "HdfsUsers.delete",
          query
          = "DELETE FROM HdfsUsers h WHERE h.id =:id"),
  @NamedQuery(name = "HdfsUsers.findByName",
          query
          = "SELECT h FROM HdfsUsers h WHERE h.name = :name")})
public class HdfsUsers implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Lob
  @Column(name = "id")
  private byte[] id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1,
          max = 1000)
  @Column(name = "name")
  private String name;
    @JoinTable(name = "hops.hdfs_users_groups",
          joinColumns
          = {
            @JoinColumn(name = "user_id",
                    referencedColumnName = "id")},
          inverseJoinColumns
          = {
            @JoinColumn(name = "group_id",
                    referencedColumnName = "id")})
  @ManyToMany
  private Collection<HdfsGroups> hdfsGroupsCollection;

  public HdfsUsers() {
  }

  public HdfsUsers(byte[] id) {
    this.id = id;
  }

  public HdfsUsers(byte[] id, String name) {
    this.id = id;
    this.name = name;
  }

  public byte[] getId() {
    return id;
  }

  public void setId(byte[] id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername(){
    int index = this.name.indexOf("__");
    if (index == -1) {
      return this.name;
    }
    index += 2; //removes the "__"
    return this.name.substring(index);
  }
  
  @XmlTransient
  @JsonIgnore
  public Collection<HdfsGroups> getHdfsGroupsCollection() {
    return hdfsGroupsCollection;
  }

  public void setHdfsGroupsCollection(
          Collection<HdfsGroups> hdfsGroupsCollection) {
    this.hdfsGroupsCollection = hdfsGroupsCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof HdfsUsers)) {
      return false;
    }
    HdfsUsers other = (HdfsUsers) object;
    if (!this.name.equals(other.name)) {
      return false;
  }
    return true;
  }

  @Override
  public String toString() {
    return "se.kth.hopsworks.hdfsUsers.HdfsUsers[ name=" + name + " ]";
  }
  
}
