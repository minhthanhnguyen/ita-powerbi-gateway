package gov.ita.powerbi_gateway.public_data.otexa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"production", "staging", "test"})
public class ProductionMetadataService implements MetadataService {

  @Autowired
  private CountryRepository countryRepository;

  @Autowired
  private ExportCountryRepository exportCountryRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private MergedCategoryRepository mergedCategoryRepository;

  @Autowired
  private PartCategoryRepository partCategoryRepository;

  @Autowired
  private ExportGroupRepository exportGroupRepository;

  @Autowired
  private HtsChapterRepository htsChapterRepository;

  @Autowired
  private HtsRepository htsRepository;

  @Autowired
  private YearRepository yearRepository;

  @Override
  public List<Country> getCountries(String source) {
    return countryRepository.findBySource(source);
  }

  @Override
  public List<ExportCountry> getExportCountries() {
    return exportCountryRepository.findAllExportCountries();
  }

  @Override
  public List<Year> getYears() {
    return yearRepository.findAllYears();
  }

  @Override
  public List<Category> getCategories(String source) {
    return categoryRepository.findBySource(source);
  }

  @Override
  public List<MergedCategory> getMergedCategories() {
    return mergedCategoryRepository.findAllMergedCategories();
  }

  @Override
  public List<PartCategory> getPartCategories() {
    return partCategoryRepository.findAllPartCategories();
  }

  @Override
  public List<ExportGroup> getExportGroups() {
    return exportGroupRepository.findAllExportGroups();
  }

  @Override
  public List<Chapter> getChapters() {
    return htsChapterRepository.findAllChapters();
  }

  @Override
  public List<Hts> getHtsByCategoriesAndChapters(List<Long> categories, List<Long> chapters) {
    return htsRepository.findByCatIdInAndHtsChapterChapterInOrderByHts(categories, chapters);
  }

  @Override
  public List<Hts> getHtsByCategories(List<Long> categoryIds) {
    return htsRepository.findByCatIdIn(categoryIds);
  }

  @Override
  public List<Hts> getHtsByChapters(List<Long> chapters) {
    return htsRepository.findByHtsChapterChapterInOrderByHts(chapters);
  }
}
