package service;

import common.AbstractWeChatService;
import domain.Assessment;
import domain.User;
import message.resp.Article;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import util.MessageUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hhx on 2017/1/16.
 */
@Service
@Scope("prototype")
public class WeChatCoreService extends AbstractWeChatService {
    private final static Logger LOGGER = Logger.getLogger(WeChatCoreService.class);

    private final static String SUBSCRIBE = "欢迎关注人机物联";

    private final static String KEY_PROJECT = "project";
    private final static String KEY_WEEKLY = "weekly";
    private final static String KEY_ASSESSMENT = "assessment";
    private final static String KEY_NOTICE = "notice";
    private final static String KEY_CONFERENCE = "conference";
    private final static String KEY_CREATE_USER = "create_user";
    private final static String ROLE_STUDENT = "student";
    private final static String ROLE_TEACHER = "teacher";


    private final static String URL_BASE = "www.renjiwulian.com/renjiwulian";
    private final static String URL_STUDENT_PROJECT = URL_BASE + "/mission/index.html";
    private final static String URL_TEACHER_PROJECT = URL_BASE + "/project/index.html";
    private final static String URL_TEACHER_WEEKLY = URL_BASE + "/weekly/teacherIndex.html";
    private final static String URL_STUDENT_WEEKLY = URL_BASE + "/weekly/studentIndex.html";
    private final static String URL_ASSESSMENT = URL_BASE + "/ass/index.html";
    private final static String URL_CONFERENCE = URL_BASE + "/conference/index.html";
    private final static String URL_NOTICE = URL_BASE + "/notice/index.html";

    private final static String CREATE_USER_TIPS = "创建老师，请输入 T#工号#名字\n创建学生，请输入 S#学号#名字";
    private final static String CREATE_USER_SUCCESS_TIPS = "创建用户成功，请该用户输入\"B#工号或学号#名字\"进行绑定";
    @Autowired
    private IUserService userService;
    @Autowired
    private IAssessmentService assessmentService;

    private String urlAddToken(String url) {
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("#?weChatId=" + fromUserName);
        return stringBuilder.toString();
    }

    private String getStudentAssessment() {
        User user = userService.getByWeChatId(fromUserName);
        DetachedCriteria dc = DetachedCriteria.forClass(Assessment.class);
        dc.createAlias("student", "s");
        dc.add(Restrictions.eq("s.userId", user.getUserId()));
        dc.add(Restrictions.eq("commit", 1));
        dc.addOrder(Order.desc("id"));
        List<Assessment> list = assessmentService.find(dc);
        if (list.isEmpty()) {
            return respTextMessage("暂无考核");
        }
        Assessment show = list.get(0);
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(show.getStudent().getName()+"  "+show.getYear()+"/"+show.getMonth()+" 考核情况"));
        articles.add(new Article("基本:" + show.getAllowance()));
        articles.add(new Article("项目:" + show.getProject()));
        articles.add(new Article("论文:" + show.getPaper()));
        articles.add(new Article("服务:" + show.getService()));
        articles.add(new Article("最终津贴:" + show.getE_allowance()));
        return respNewsMessage(articles);
    }

    /**
     * 关注事件
     *
     * @return
     */
    public String subscribe() {
        return respTextMessage(SUBSCRIBE);
    }

    private String newsMessage(String title, String content, String picUrl, String url) {
        Article article = new Article();
        article.setDescription(content);
        article.setTitle(title);
        article.setPicUrl(picUrl);
        article.setUrl(url);
        List<Article> list = new ArrayList<>();
        list.add(article);
        return respNewsMessage(list);
    }

    private void insertUser(String role, String no, String name) {
        User user = new User();
        user.setRole(role);
        user.setNo(no);
        user.setName(name);
        user.setGraduate("0");
        if (ROLE_STUDENT.equals(role)) {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            user.setEduStartDate(sd.format(new Date()));
        }
        userService.insert(user);
    }


    private String createUserOrBind(final String keyWord) {
        final String error = "不知道你在说什么";
        do {
            if (StringUtils.isEmpty(keyWord)) {
                break;
            }
            String[] keys = keyWord.split("#");
            if (keys.length == 3) {
                if ("t".equalsIgnoreCase(keys[0])) {
                    if (StringUtils.isNumeric(keys[1])) {
                        insertUser(ROLE_TEACHER, keys[1], keys[2]);
                        return respTextMessage(keys[0] + keys[1] + CREATE_USER_SUCCESS_TIPS);
                    } else {
                        break;
                    }
                } else if ("s".equalsIgnoreCase(keys[0])) {
                    if (StringUtils.isNumeric(keys[1])) {
                        insertUser(ROLE_STUDENT, keys[1], keys[2]);
                        return respTextMessage(keys[0] + keys[1] + CREATE_USER_SUCCESS_TIPS);
                    } else {
                        break;
                    }
                } else if ("b".equalsIgnoreCase(keys[0])) {
                    if (StringUtils.isNumeric(keys[1])) {
                        User user = userService.findByNoName(keys[1], keys[2]);
                        if (user == null) {
                            return respTextMessage("绑定失败，请检查输入是否正确");
                        } else if (!StringUtils.isEmpty(user.getWeChatId())) {
                            return respTextMessage("此微信号已经绑定，用户是" + user.getName());
                        } else {
                            user.setWeChatId(fromUserName);
                            userService.update(user);
                            return respTextMessage(user.getName() + ",绑定成功");
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        } while (false);
        return respTextMessage(error);
    }

    private String click(String token, String key) {
        User user = userService.getByWeChatId(token);
        if (user == null) {
            return respTextMessage("绑定之后才能使用哦！");
        }
        String msg = null;
        if (ROLE_TEACHER.equals(user.getRole())) {
            switch (key) {
                case KEY_PROJECT:
                    msg = newsMessage("项目管理", "点击查看", null, urlAddToken(URL_TEACHER_PROJECT));
                    break;
                case KEY_WEEKLY:
                    msg = newsMessage("周报", "点击查看", null, urlAddToken(URL_TEACHER_WEEKLY));
                    break;
                case KEY_ASSESSMENT:
                    msg = newsMessage("考核", "点击查看", null, urlAddToken(URL_ASSESSMENT));
                    break;
                case KEY_CONFERENCE:
                    msg = newsMessage("会议纪要", "点击查看", null, urlAddToken(URL_CONFERENCE));
                    break;
                case KEY_NOTICE:
                    msg = newsMessage("公告", "点击查看", null, urlAddToken(URL_NOTICE));
                    break;
                case KEY_CREATE_USER:
                    msg = respTextMessage(CREATE_USER_TIPS);
                    break;
                default:
                    msg = respTextMessage("error");
            }
        } else if (ROLE_STUDENT.equals(user.getRole())) {
            switch (key) {
                case KEY_PROJECT:
                    msg = newsMessage("项目管理", "点击查看", null, urlAddToken(URL_STUDENT_PROJECT));
                    break;
                case KEY_WEEKLY:
                    msg = newsMessage("周报", "点击查看", null, urlAddToken(URL_STUDENT_WEEKLY));
                    break;
                case KEY_ASSESSMENT:
                    msg = getStudentAssessment();
                    break;
                case KEY_CONFERENCE:
                    msg = respTextMessage("仅供老师使用");
                    break;
                case KEY_NOTICE:
                    msg = newsMessage("公告", "点击查看", null, urlAddToken(URL_NOTICE));
                    break;
                case KEY_CREATE_USER:
                    msg = respTextMessage(CREATE_USER_TIPS);
                    break;
                default:
                    msg = respTextMessage("error");
            }
        }
        return msg;
    }

    @Override
    public String process() {
        String msg = "";
        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
            if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                // 订阅事件
                msg = subscribe();
            } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                msg = click(fromUserName, eventKey);
            }
        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            msg = createUserOrBind(content);
        }
        return String.valueOf(msg);
    }
}
