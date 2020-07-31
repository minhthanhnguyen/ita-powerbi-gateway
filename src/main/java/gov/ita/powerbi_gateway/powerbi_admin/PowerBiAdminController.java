package gov.ita.powerbi_gateway.powerbi_admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/pbi-admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class PowerBiAdminController {

  private final PowerBiAdminService powerBiAdminService;
  private final Map<String, ReportToken> embedTokens;

  public PowerBiAdminController(PowerBiAdminService powerBiAdminService) {
    this.powerBiAdminService = powerBiAdminService;
    this.embedTokens = new HashMap<>();
  }

  @GetMapping("/groups")
  public Groups getGroups(@RequestParam(required = false) String workspaceName) {
    return powerBiAdminService.getPbiGroups(workspaceName);
  }

  @GetMapping("/reports")
  public Reports getReports(@RequestParam String workspaceName) {
    Group group = powerBiAdminService.getPbiGroups(workspaceName).getValue().get(0);
    return powerBiAdminService.getPbiReports(group.getId(), null);
  }

  @GetMapping("/generate-token")
  public ReportToken generateToken(@RequestParam String workspaceName, @RequestParam String reportName) {
    Group group = powerBiAdminService.getPbiGroups(workspaceName).getValue().get(0);
    Report report = powerBiAdminService.getPbiReports(group.getId(), reportName).getValue().get(0);
    String reportTokenKey = group.getId() + "#" + report.getId();
    ReportToken existingReportToken = embedTokens.get(reportTokenKey);
    if (existingReportToken != null && existingReportToken.getPowerBiToken().getExpiration().isAfter(LocalDateTime.now(ZoneOffset.UTC))) {
      return existingReportToken;
    } else {
      Token token = powerBiAdminService.generatePbiEmbedToken(group.getId(), report.getId());
      ReportToken value = new ReportToken(report, token);
      embedTokens.put(reportTokenKey, value);
      return value;
    }
  }

  @GetMapping("/export-to-file")
  public ExportStatus exportToFile(@RequestParam String workspaceName,
                                   @RequestParam String reportName,
                                   @RequestParam String bookmarkState) throws JsonProcessingException {
    Group group = powerBiAdminService.getPbiGroups(workspaceName).getValue().get(0);
    Report report = powerBiAdminService.getPbiReports(group.getId(), reportName).getValue().get(0);
    return powerBiAdminService.exportToFileInGroup(group.getId(), report.getId(), bookmarkState);
  }

  @GetMapping("/export-to-file-status")
  public ExportStatus getExportToFileStatus(@RequestParam String workspaceName,
                                            @RequestParam String reportName,
                                            @RequestParam String exportStatusId) {
    Group group = powerBiAdminService.getPbiGroups(workspaceName).getValue().get(0);
    Report report = powerBiAdminService.getPbiReports(group.getId(), reportName).getValue().get(0);
    return powerBiAdminService.getExportToFileStatusInGroup(group.getId(), report.getId(), exportStatusId);
  }

  @GetMapping(value = "/export-file", produces = MediaType.APPLICATION_PDF_VALUE)
  public byte[] getExportFile(@RequestParam String workspaceName,
                              @RequestParam String reportName,
                              @RequestParam String exportStatusId) {
    Group group = powerBiAdminService.getPbiGroups(workspaceName).getValue().get(0);
    Report report = powerBiAdminService.getPbiReports(group.getId(), reportName).getValue().get(0);
    return powerBiAdminService.getFileOfExportToFileInGroup(group.getId(), report.getId(), exportStatusId);
  }
}
