package service;

import common.AbstractCurdServiceSupport;
import common.ICurdDaoSupport;
import dao.IAssessmentDao;
import domain.Assessment;
import domain.Student;
import domain.User;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by hhx on 2017/4/2.
 */
@Service
public class AssessmentService extends AbstractCurdServiceSupport<Assessment> implements IAssessmentService {
    private static final Logger LOGGER = Logger.getLogger(AssessmentService.class);
    @Autowired
    private IAssessmentDao assessmentDao;
    @Autowired
    private IUserService userService;

    private static final String[] HEADERS = {"学号", "姓名", "基本", "项目", "论文", "服务", "最终津贴", "考核日期"};

    @Override
    protected ICurdDaoSupport<Assessment> getCurdDao() {
        return assessmentDao;
    }

    @Override
    @Transactional
    public void update(Assessment entity) {
        Assessment origin = assessmentDao.get(entity.getId());
        origin.setAllowance(entity.getAllowance());
        origin.setBaseRank(entity.getBaseRank());
        origin.setPaperRank(entity.getPaperRank());
        origin.setProjectRank(entity.getProjectRank());
        origin.setBaseRank(entity.getBaseRank());
        origin.setCommit(entity.getCommit());
        assessmentDao.update(inflate(entity));
    }

    private Assessment inflate(Assessment assessment) {
        User student = userService.get(assessment.getStudent().getUserId());
        String eduStartDate = student.getEduStartDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(eduStartDate);
            Date now = new Date();
            assessment.setTime(df.format(now));
            long dateLong = date.getTime();
            long nowLong = now.getTime();
            long difference = nowLong - dateLong;
            long day = difference / 1000 / 60 / 60 / 24;
            int grade = (int) (day / 365);
            int projectValue = getProjectOrPaper(assessment.getProjectRank());
            int paperValue = getProjectOrPaper(assessment.getPaperRank());
            int baseValue = getBaseValue(grade, assessment.getBaseRank());
            int finalValue = paperValue + baseValue + projectValue + assessment.getService();
            assessment.setE_allowance(finalValue);
            assessment.setPaper(paperValue);
            assessment.setProject(projectValue);
            assessment.setAllowance(baseValue);
        } catch (ParseException e) {
            LOGGER.error(e);
        }
        return assessment;
    }

    @Override
    @Transactional
    public void assess(Assessment assessment) {
        //assessmentDao.insert(inflate(assessment));
        assessmentDao.saveOrUpdate(inflate(assessment));
    }

    @Override
    public Assessment getLatestByStudent(User user) {
        return assessmentDao.getLatestByStudent(user);
    }

    private int getProjectOrPaper(String rank) {
        if (StringUtils.isEmpty(rank)) {
            return 0;
        }
        rank = rank.toUpperCase();
        int value = 0;
        switch (rank) {
            case "A":
                value = 500;
                break;
            case "B":
                value = 400;
                break;
            case "C":
                value = 300;
                break;
            case "D":
                value = 200;
                break;
            case "E":
                value = 100;
                break;
            default:
        }
        return value;
    }

    private int getBaseValue(int grade, String rank) {
        if (StringUtils.isEmpty(rank)) {
            return 0;
        }
        rank = rank.toUpperCase();
        int a = 0;
        if (grade == 1) {
            a = 300;
        } else {
            a = 200;
        }
        int value = 0;
        switch (rank) {
            case "A":
                value = a;
                break;
            case "B":
                value = (int) (a * 0.8);
                break;
            case "C":
                value = (int) (a * 0.6);
                break;
            case "D":
                value = (int) (a * 0.4);
                break;
            case "E":
                value = (int) (a * 0.2);
                break;
            default:
        }
        return value;
    }

    @Override
    public void export(final OutputStream outputStream, int year, int month) throws IOException {
        List<Assessment> list = find(year, month);
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("考核情况");
        sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 7));
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        CellStyle s = wb.createCellStyle();
        s.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(s);
        cell.setCellValue(year + "/" + month + "考核情况");
        Row title = sheet.createRow(3);
        sheet.setDefaultColumnWidth(12);
        for (int i = 0; i < HEADERS.length; i++) {
            title.createCell(i).setCellValue(HEADERS[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            Row valueRow = sheet.createRow(i + 4);
            valueRow.createCell(0).setCellValue(list.get(i).getStudent().getNo());//学号
            valueRow.createCell(1).setCellValue(list.get(i).getStudent().getName());//姓名
            valueRow.createCell(2).setCellValue(list.get(i).getAllowance());//基本
            valueRow.createCell(3).setCellValue(list.get(i).getProject());//项目
            valueRow.createCell(4).setCellValue(list.get(i).getPaper());//论文
            valueRow.createCell(5).setCellValue(list.get(i).getService());//服务
            valueRow.createCell(6).setCellValue(list.get(i).getE_allowance());//最终
            valueRow.createCell(7).setCellValue(list.get(i).getTime());//最终
        }
        wb.write(outputStream);
    }

    @Override
    public String exportFile(String url, int year, int month) throws IOException {
        List<Assessment> list = find(year, month);
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("考核情况");
        sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 7));
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        CellStyle s = wb.createCellStyle();
        s.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(s);
        cell.setCellValue(year + "/" + month + "考核情况");
        Row title = sheet.createRow(3);
        sheet.setDefaultColumnWidth(12);
        for (int i = 0; i < HEADERS.length; i++) {
            title.createCell(i).setCellValue(HEADERS[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            Row valueRow = sheet.createRow(i + 4);
            valueRow.createCell(0).setCellValue(list.get(i).getStudent().getNo());//学号
            valueRow.createCell(1).setCellValue(list.get(i).getStudent().getName());//姓名
            valueRow.createCell(2).setCellValue(list.get(i).getAllowance());//基本
            valueRow.createCell(3).setCellValue(list.get(i).getProject());//项目
            valueRow.createCell(4).setCellValue(list.get(i).getPaper());//论文
            valueRow.createCell(5).setCellValue(list.get(i).getService());//服务
            valueRow.createCell(6).setCellValue(list.get(i).getE_allowance());//最终
            valueRow.createCell(7).setCellValue(list.get(i).getTime());//最终
        }

        long time = new Date().getTime();
        String fileName;
        try {
            fileName = year + "-" + month + "-" + time + ".xlsx";
            FileOutputStream out = new FileOutputStream(url + "excel/" + fileName);
            wb.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "fail";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }

        return fileName;
    }


    private List<Assessment> find(int year, int month) {
        DetachedCriteria dc = DetachedCriteria.forClass(Assessment.class);
        dc.add(Restrictions.eq("year", year));
        dc.add(Restrictions.eq("month", month));
        return super.find(dc);
    }
}
