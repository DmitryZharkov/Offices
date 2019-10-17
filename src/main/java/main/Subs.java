/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SM
 */
@Entity
@Table(name = "SUBS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subs.findAll", query = "SELECT s FROM Subs s")
    , @NamedQuery(name = "Subs.findByName", query = "SELECT s FROM Subs s WHERE s.name = :name")
    , @NamedQuery(name = "Subs.findByFio", query = "SELECT s FROM Subs s WHERE s.fio = :fio")
    , @NamedQuery(name = "Subs.findByHc", query = "SELECT s FROM Subs s WHERE s.hc = :hc")
    , @NamedQuery(name = "Subs.findByParent", query = "SELECT s FROM Subs s WHERE s.parent = :parent")
    , @NamedQuery(name = "Subs.findById", query = "SELECT s FROM Subs s WHERE s.id = :id")})
public class Subs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "FIO")
    private String fio;
    @Column(name = "HC")
    private Integer hc;
    @Column(name = "PARENT")
    private String parent;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    public Subs() {
    }

    public Subs(Integer id) {
        this.id = id;
    }

    public Subs(Integer id, String name, String fio) {
        this.id = id;
        this.name = name;
        this.fio = fio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getHc() {
        return hc;
    }

    public void setHc(Integer hc) {
        this.hc = hc;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subs)) {
            return false;
        }
        Subs other = (Subs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hello.Subs[ id=" + id + " ]";
    }
    
}
