import com.google.gson.Gson;
import okhttp3.*;

public class JiraCreateIssueTest {

  private static String buildJSON(String key,String summery,String issueType){
      Gson gson = new Gson();
      Fields fields = new Fields();
      fields.setProject(new Project(key));
      fields.setSummary(summery);
      fields.setIssueType(new IssueType(issueType));

      Payload payload = new Payload(fields);

      String json_string = gson.toJson(payload);

      return  json_string;
  }

  static  class  Payload{
      private  Fields fields;

      public Payload(Fields fields) {
          this.fields = fields;
      }

      public Fields getFields() {
          return fields;
      }

      public void setFields(Fields fields) {
          this.fields = fields;
      }
  }
  static  class Fields{
      private Project project;
      private String summary;
      private  IssueType issuetype;



      public Project getProject() {
          return project;
      }

      public void setProject(Project project) {
          this.project = project;
      }

      public String getSummary() {
          return summary;
      }

      public void setSummary(String summery) {
          this.summary = summery;
      }

      public IssueType getIssueType() {
          return issuetype;
      }

      public void setIssueType(IssueType issueType) {
          this.issuetype = issueType;
      }
  }
  static class  Project{
      private String key;

      public Project(String key) {
          this.key = key;
      }

      public String getKey() {
          return key;
      }

      public void setKey(String key) {
          this.key = key;
      }
  }
  static  class  IssueType{
      private  String name;

      public IssueType(String name) {
          this.name = name;
      }

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }
  }



    public static void main(String[] args) throws Exception{

        String payload = buildJSON("KAN","This is a test for gson and create a new json string","Task");
        System.out.println(payload);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, payload);
        Request request = new Request.Builder()
                .url("https://verdantarchery2020.atlassian.net/rest/api/3/issue")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic dmVyZGFudGFyY2hlcnkyMDIwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjAtb3FYeTY1VktQS2VpUkoxcms2Q1FkbmItazc5VVlTMEFjSjRUWHhtRi1LdHI5Zk1nakFJRElHQ05fc3hNSTFPNDJyaFJ1NG9aU1ZKOGo1M1RHZGMxYWV5WE44Z1RIMmtlRHZXNElNM28xSjM3U2FQWGw0NEo4Tl8tM3FrNUZ3bWNFVzlKTjlqUHNHeUJTQXIxZXZNVEFBWlR4S1BRMGFGckREM2s1TzVGNjg9MzZCRDgxRUE=")
                .addHeader("Cookie", "atlassian.xsrf.token=d51c1f7d8a6c178651094c698f207032f416beb4_lin")
                .build();
        Response response = client.newCall(request).execute();
        String result_string = response.body().string();
        System.out.println(result_string);
        Gson gson = new Gson();

        IssueDTO issueDTO = gson.fromJson(result_string,IssueDTO.class);
        System.out.println(issueDTO.getId());

    }

}
