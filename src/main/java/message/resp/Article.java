package message.resp;

public class Article {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;

    public Article() {
        // TODO Auto-generated constructor stub
    }

    public Article(String title) {
        // TODO Auto-generated constructor stub
        Title = title;
    }

    public Article(String title, String description, String picUrl, String url) {
        super();
        Title = title;
        Description = description;
        PicUrl = picUrl;
        Url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return null == Description ? "" : Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return null == PicUrl ? "" : PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return null == Url ? "" : Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

}
