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
@Table(name = "OFFICES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offices.findAll", query = "SELECT o FROM Offices o")
    , @NamedQuery(name = "Offices.findByName", query = "SELECT o FROM Offices o WHERE o.name = :name")
    , @NamedQuery(name = "Offices.findByAddr", query = "SELECT o FROM Offices o WHERE o.addr = :addr")
    , @NamedQuery(name = "Offices.findByHc", query = "SELECT o FROM Offices o WHERE o.hc = :hc")
    , @NamedQuery(name = "Offices.findById", query = "SELECT o FROM Offices o WHERE o.id = :id")})
public class Offices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDR")
    private String addr;
    @Basic(optional = false)
    @Column(name = "HC")
    private int hc;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    public Offices() {
    }

    public Offices(Integer id) {
        this.id = id;
    }

    public Offices(Integer id, int hc) {
        this.id = id;
        this.hc = hc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getHc() {
        return hc;
    }

    public void setHc(int hc) {
        this.hc = hc;
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
        if (!(object instanceof Offices)) {
            return false;
        }
        Offices other = (Offices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Offices[ id=" + id + " ]";
    }
    
}
