/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author pitpr
 */
@Entity
@Table(name = "activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a")
    , @NamedQuery(name = "Activity.findById", query = "SELECT a FROM Activity a WHERE a.id = :id")
    , @NamedQuery(name = "Activity.findByName", query = "SELECT a FROM Activity a WHERE a.name = :name")
    , @NamedQuery(name = "Activity.findByLocationDescription", query = "SELECT a FROM Activity a WHERE a.locationDescription = :locationDescription")
    , @NamedQuery(name = "Activity.findByDate", query = "SELECT a FROM Activity a WHERE a.date = :date")
    , @NamedQuery(name = "Activity.findByTime", query = "SELECT a FROM Activity a WHERE a.time = :time")
    , @NamedQuery(name = "Activity.findByLocationX", query = "SELECT a FROM Activity a WHERE a.locationX = :locationX")
    , @NamedQuery(name = "Activity.findByLocationY", query = "SELECT a FROM Activity a WHERE a.locationY = :locationY")
    , @NamedQuery(name = "Activity.findByPast", query = "SELECT a FROM Activity a WHERE a.past = :past")
    , @NamedQuery(name = "Activity.findByMaxMembers", query = "SELECT a FROM Activity a WHERE a.maxMembers = :maxMembers")
    , @NamedQuery(name = "Activity.findByImage", query = "SELECT a FROM Activity a WHERE a.image = :image")
    , @NamedQuery(name = "Activity.findByDescription", query = "SELECT a FROM Activity a WHERE a.description = :description")})
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 250)
    @Column(name = "location_description")
    private String locationDescription;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 45)
    @Column(name = "time")
    private String time;
    @Size(max = 45)
    @Column(name = "location_x")
    private String locationX;
    @Size(max = 45)
    @Column(name = "location_y")
    private String locationY;
    @Column(name = "past")
    private Integer past;
    @Column(name = "max_members")
    private Integer maxMembers;
    @Size(max = 250)
    @Column(name = "image")
    private String image;
    @Lob
    @Size(max = 1073741824)
    @Column(name = "chatroom",columnDefinition = "json")
    private String chatroom;
    @Size(max = 250)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private ActivitiesDefault type;
    @OneToMany(mappedBy = "activityId")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private Collection<Participates> participatesCollection;

    public Activity() {
    }

    public Activity(Integer id) {
        this.id = id;
    }

    public Activity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public Integer getPast() {
        return past;
    }

    public void setPast(Integer past) {
        this.past = past;
    }

    public Integer getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChatroom() {
        return chatroom;
    }

    public void setChatroom(String chatroom) {
        this.chatroom = chatroom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivitiesDefault getType() {
        return type;
    }

    public void setType(ActivitiesDefault type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Participates> getParticipatesCollection() {
        return participatesCollection;
    }

    public void setParticipatesCollection(Collection<Participates> participatesCollection) {
        this.participatesCollection = participatesCollection;
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
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bootcamp.ProjectBoot_Jnpep.models.Activity[ id=" + id + " ]";
    }
    
}
