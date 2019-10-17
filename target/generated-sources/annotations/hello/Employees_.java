package hello;

import main.Employees;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-01T14:36:56")
@StaticMetamodel(Employees.class)
public class Employees_ { 

    public static volatile SingularAttribute<Employees, String> mail;
    public static volatile SingularAttribute<Employees, String> name;
    public static volatile SingularAttribute<Employees, Date> birth;
    public static volatile SingularAttribute<Employees, Long> tel;
    public static volatile SingularAttribute<Employees, String> office;

}