package common;

import message.resp.Article;
import message.resp.NewsMessage;
import message.resp.TextMessage;
import org.apache.log4j.Logger;
import util.MessageUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by hhx on 2017/1/16.
 */
public abstract class AbstractWeChatService {
    private static Logger LOGGER = Logger.getLogger(AbstractWeChatService.class);
    protected String fromUserName;
    protected String toUserName;
    protected long createTime;
    protected String msgType;
    protected long msgId;
    protected String content;
    protected String eventKey;
    protected String eventType;


    public String getReturnValue() {
        return process();
    }

    protected abstract String process();


    public void init(InputStream inputStream) {
        Map<String, String> map = null;
        try {
            map = MessageUtil.parseXML(inputStream);
            fromUserName = map.get("FromUserName");
            toUserName = map.get("ToUserName");
            msgType = map.get("MsgType");
            eventKey = map.get("EventKey");
            eventType = map.get("Event");
            content = map.get("Content");
            msgId = Long.valueOf(map.get("MsgId"));
            createTime = Long.valueOf(map.get("CreateTime"));
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error(e);
                }
            }
        }


    }

    /**
     * 回复图文消息，不超过10条
     *
     * @param articles
     * @return
     */
    protected final String respNewsMessage(List<Article> articles) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setArticleCount(articles.size());
        newsMessage.setArticles(articles);
        newsMessage.setCreateTime(System.currentTimeMillis() / 1000);
        newsMessage.setFromUserName(toUserName);
        // newsMessage.setFuncFlag(0);
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setToUserName(fromUserName);
        return MessageUtil.newsMessageToXml(newsMessage);

    }

    /**
     * 回复文本消息
     *
     * @param
     * @return
     */
    protected final String respTextMessage(String content) {
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setContent(content);
        textMessage.setCreateTime(System.currentTimeMillis() / 1000);
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        // textMessage.setFuncFlag(0);
        return MessageUtil.textMessageToXml(textMessage);

    }
}
