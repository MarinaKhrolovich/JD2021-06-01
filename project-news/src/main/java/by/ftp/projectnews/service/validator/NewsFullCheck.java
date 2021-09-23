package by.ftp.projectnews.service.validator;

import by.ftp.projectnews.bean.News;

public class NewsFullCheck {


	public void checkNews(News news) throws ValidatorException {
		String title = news.getTitle();
		String brief = news.getBrief();
		String content = news.getContent();

		if (title.length() <3  || title.length() > 200) {
			throw new ValidatorException("Invalid title!");
		}

		if (brief.length() <3  || brief.length() > 200) {
			throw new ValidatorException("Invalid brief!");
		}

		if (content.length() <3  || content.length() > 1000) {
			throw new ValidatorException("Invalid content!");
		}
	}

}
