package gov.ita.powerbi_gateway.public_data.otexa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OtexaExportsVw")
public class ExportCountry {
  @Id
  private String country;
}
