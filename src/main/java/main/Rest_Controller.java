package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest_Controller {
    public String office="";
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    int pagen=0;
    int osta=0;
    int sort=0;
    String s="";
    @RequestMapping("/getoffice{office}")
    public Object getofficeinfo(@PathVariable("office") Integer office) {
        System.out.println(office.toString());
        System.out.println(Maps.SubsMap.toString()+Maps.OfficeNumber);
        Maps.edit=false;
        if (office<Maps.OfficeNumber){
            Maps.SelOffice=(Offices) Maps.OffceMap.get(office);
            Maps.OfficeSub=1;
        return Maps.SelOffice;        
        }
        else
        {
           Maps.SelSub=(Subs)Maps.SubsMap.get(office);
           Maps.OfficeSub=2;
           return Maps.SelSub;
        }
    }
    @RequestMapping("/edit")
    public void edit() {
        Maps.edit=true;
    }
    @RequestMapping("/delete{office}")
    public Object deleteoffice(@PathVariable("office") String office) {
        System.out.println(office.toString());
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("org.springframework_gs-rest-service_jar_0.1.0PU");
        EntityManager em= emf.createEntityManager();
        switch(Maps.OfficeSub){
            case 1:
                EntityTransaction entr = em.getTransaction();
                entr.begin();
                Offices off = (Offices) em.createNamedQuery("Offices.findByName").setParameter("name", Maps.SelOffice.getName()).getResultList().get(0);
                if(off.getHc()==0)
                {
                em.remove(off);
                }
                entr.commit();
                break;
            case 2:
                System.out.println("doshlo"+Maps.SelSub.getName());
                EntityTransaction entr1 = em.getTransaction();
                entr1.begin();
                Subs offsub = (Subs) em.createNamedQuery("Subs.findByName").setParameter("name", Maps.SelSub.getName()).getResultList().get(0);
                if(offsub.getHc()==0)
                {
                    System.out.println("doshlo"+offsub);
                em.remove(offsub);
                List <Employees> employees = em.createNamedQuery("Employees.findByOffice").setParameter("office", Maps.SelSub.getName()).getResultList();
                if (!employees.isEmpty())
                {
                for (int i=0;i<employees.size();i++)
                    em.remove(employees.get(i));
                }
                Subs subs = (Subs) em.createNamedQuery("Subs.findByName").setParameter("name", Maps.SelSub.getParent()).getResultList().get(0);
                if(subs==null)
                {System.out.println("doshlo5");
                    Offices parent = (Offices) em.createNamedQuery("Offices.findByName").setParameter("name", Maps.SelSub.getParent()).getResultList().get(0);
                    List <Subs> childs = (List <Subs>) em.createNamedQuery("Subs.findByParent").setParameter("name", Maps.SelSub.getParent()).getResultList();
                    if ((parent!=null)&&(childs.isEmpty()))
                    {
                       parent.setHc(0);
                    }
                }                
                else
                {
                    List <Subs> childs = (List <Subs>) em.createNamedQuery("Subs.findByParent").setParameter("parent", Maps.SelSub.getParent()).getResultList();
                    if (childs.isEmpty())
                    {
                       subs.setHc(0);
                    }
                }
                System.out.println("doshlo2");
                entr1.commit();
                break;
        }
        }
        System.out.println("doshlo6");
        em.close();System.out.println("doshlo7");
        emf.close();System.out.println("doshlo8");
        return "redirect:/";
    }
    @RequestMapping("/getemployees")
    public List<Employees> getemployees() {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("org.springframework_gs-rest-service_jar_0.1.0PU");
        EntityManager em= emf.createEntityManager();
        List<Employees> allemployees=new ArrayList<Employees>();
        List<Employees> tenemployees=new ArrayList<Employees>();
        allemployees=em.createNamedQuery("Employees.findByOffice").setParameter("office", Maps.SelSub.getName()).getResultList();
        if(sort!=0)
        {
            switch (sort)
            {
                   case 1:
                       allemployees.sort(Comparator.comparing(Employees::getName));
                       break;
                   case 2:
                       allemployees.sort(Comparator.comparing(Employees::getBirth));
                       break;
                   case 3:
                       allemployees.sort(Comparator.comparing(Employees::getTel));
                       break;
                   case 4:
                       allemployees.sort(Comparator.comparing(Employees::getMail));
                       break;
            }
        }
        if(allemployees.size()>10){
        pagen = allemployees.size()/10;
        osta= allemployees.size()%10;
        System.out.println("osta= "+osta);
        if (osta!=0){pagen++;}
        for(int i=0;i<10;i++){
            tenemployees.add(allemployees.get(i));
        }   
        }
        else
        {
          pagen=0;
          osta=allemployees.size();
          for(int i=0;i<osta;i++){
            tenemployees.add(allemployees.get(i));
        }
        }           
        em.close();
        emf.close();
        return tenemployees;
    }
    @RequestMapping("/delete_employee{name}")
    public Object delete_employee(@PathVariable("name") String name) {
        System.out.println(name.toString());
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("org.springframework_gs-rest-service_jar_0.1.0PU");
        EntityManager em= emf.createEntityManager();
        EntityTransaction entr = em.getTransaction();
        entr.begin();
        Employees employee =(Employees)em.createNamedQuery("Employees.findByName").setParameter("name", name).getResultList().get(0);
        em.remove(employee);
        entr.commit();
        em.close();
        emf.close();
        return "redirect:/";
    }
    @RequestMapping("/gete_page{page}")
    public List<Employees> getemployeespage(@PathVariable("page") int page) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("org.springframework_gs-rest-service_jar_0.1.0PU");
        EntityManager em= emf.createEntityManager();
        List<Employees> allemployees=new ArrayList<Employees>();
        List<Employees> tenemployees=new ArrayList<Employees>();
        allemployees=em.createNamedQuery("Employees.findByOffice").setParameter("office", Maps.SelSub.getName()).getResultList();
        if(sort!=0)
        {
            switch (sort)
            {
                   case 1:
                       allemployees.sort(Comparator.comparing(Employees::getName));
                       break;
                   case 2:
                       allemployees.sort(Comparator.comparing(Employees::getBirth));
                       break;
                   case 3:
                       allemployees.sort(Comparator.comparing(Employees::getTel));
                       break;
                   case 4:
                       allemployees.sort(Comparator.comparing(Employees::getMail));
                       break;
            }
        }
        System.out.println("page= "+page+"pagen= "+pagen);
        if((page+1)==pagen){
            System.out.println("pagen=pagen");
            for(int i=page*10;i<page*10+osta;i++){
            tenemployees.add(allemployees.get(i));
        }
        }
        else
        {
            for(int i=page*10;i<page*10+10;i++){
            tenemployees.add(allemployees.get(i));
        }  
        }   
        em.close();
        emf.close();
        return tenemployees;
    }
    @RequestMapping("/getpagen")
    public int greeting() {
        return pagen;
    }
    @RequestMapping("/setsort{sort}")
    public void setsort(@PathVariable("sort") int sort) {
        this.sort=sort;
        System.out.println("sort"+sort);
    }
}
