package gov.ita.powerbi_gateway.public_data.otexa;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OtexaCategoryRefVw")
public class Category {
  @Id
  private Long catId;
  private String longCategory;
}