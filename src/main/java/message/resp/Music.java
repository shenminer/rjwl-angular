package message.resp;


public class Music {
	private String title;
	private String description;
	private String musicUrl;
	private String hQMusicUrl;

	public String getTitleString() {
		return title;
	}

	public void setTitleString(String titleString) {
		this.title = titleString;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String gethQMusicUrl() {
		return hQMusicUrl;
	}

	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}

}
