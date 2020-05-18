package gov.ita.powerbi_gateway.public_data.otexa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/otexa", produces = MediaType.APPLICATION_JSON_VALUE)
public class OtexaController {

  @Autowired
  private MetadataService metadataService;

  @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Country> getCountries() {
    return metadataService.getCountries();
  }

  @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Category> getCategories() {
    return metadataService.getCategories();
  }

  @GetMapping(value = "/chapters", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Chapter> getChapters() {
    return metadataService.getChapters();
  }

  @GetMapping(value = "/hts", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Hts> getHts(@RequestParam List<String> categories, @RequestParam List<String> chapters) {
    List<Long> categoryIds = categories.stream().map(Long::parseLong).collect(Collectors.toList());
    List<Long> chapterIds = chapters.stream().map(Long::parseLong).collect(Collectors.toList());
    return metadataService.getHtsByCategoriesAndChapters(categoryIds, chapterIds);
  }

}
