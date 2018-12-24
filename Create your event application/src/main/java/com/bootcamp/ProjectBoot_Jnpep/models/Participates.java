/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author pitpr
 */
@Entity
@Table(name = "participates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participates.findAll", query = "SELECT p FROM Participates p")
    , @NamedQuery(name = "Participates.findById", query = "SELECT p FROM Participates p WHERE p.id = :id")})
public class Participates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private Activity activityId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private User userId;

    public Participates() {
    }

    public Participates(Integer id) {
        this.id = id;
    }
    
    public Participates(Activity activity,User user){
        this.activityId=activity;
        this.userId=user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Activity getActivityId() {
        return activityId;
    }

    public void setActivityId(Activity activityId) {
        this.activityId = activityId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Participates)) {
            return false;
        }
        Participates other = (Participates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bootcamp.ProjectBoot_Jnpep.models.Participates[ id=" + id + " ]";
    }
    
}
