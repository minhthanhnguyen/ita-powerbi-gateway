package gov.ita.powerbi_gateway.public_data.otexa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OtexaHtsRefVw")
@IdClass(CategoryHts.class)
public class Hts {
  @Id
  @JsonIgnore
  public Long catId;

  @Id
  public String hts;

  public String longHts;

  @JsonIgnore
  @MapsId
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumns({
      @JoinColumn(name = "catId"),
      @JoinColumn(name = "hts")
  })
  public HtsChapter htsChapter;


  public Hts(String hts, String longHts) {
    this.hts = hts;
    this.longHts = longHts;
  }
}
