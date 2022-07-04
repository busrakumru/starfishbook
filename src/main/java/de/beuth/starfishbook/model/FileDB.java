package de.beuth.starfishbook.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "files")
public class FileDB {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    @JsonIgnore
    private String type;

    @Lob
    @JsonIgnore
    private byte[] data;


    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notes_id")
    @JsonIgnore
    private Notes notes;*/

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notes_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Notes notes;


    public FileDB() {
    }
    public FileDB(String name, String type, byte[] data, Notes notes) {
      this.name = name;
      this.type = type;
      this.data = data;
      this.notes = notes;
    }
    public String getId() {
      return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getType() {
      return type;
    }
    public void setType(String type) {
      this.type = type;
    }
    public byte[] getData() {
      return data;
    }
    public void setData(byte[] data) {
      this.data = data;
    }

    public Notes getNotes() {
      return notes;
  }

  public void setNotes(Notes notes) {
      this.notes = notes;
  }
    
}
