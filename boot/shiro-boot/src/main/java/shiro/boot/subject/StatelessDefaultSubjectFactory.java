package shiro.boot.subject;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;


public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    public Subject createSubject(SubjectContext context) {
        context.setSessionCreationEnabled(false);//禁用Session
        return super.createSubject(context);
    }
}