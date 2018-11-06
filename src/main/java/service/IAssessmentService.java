package service;

import common.ICurdServiceSupport;
import domain.Assessment;
import domain.User;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by hhx on 2017/4/2.
 */

public interface IAssessmentService extends ICurdServiceSupport<Assessment> {
    void assess(Assessment assessment);

    Assessment getLatestByStudent(User user);

    void export(OutputStream outputStream, int year, int month) throws IOException;
    String exportFile(String url, int year, int month) throws IOException;
}
