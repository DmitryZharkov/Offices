/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author SM
 */
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.apache.commons.validator.routines.EmailValidator;

@Controller
public class Office_Controller {
    String s1="t";
    static String  lb= "<li class=\"sel\"",lbe=">",le="</li>",nlmb="<ul class=\"nested\">",
            nlme="</ul>",nlb="<li><span class=\"caret sel\"",nle="</span>",idb=" id=",quote="\"";
    static Integer Count=0;static Integer OfficeCount=0;
    
    @InitBinder
public void initBinder(WebDataBinder webDataBinder) {
 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd/");
 dateFormat.setLenient(false);
 webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
 }
    @GetMapping("/")
    public String index(Model model) {
        Count=0;OfficeCount=0;
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("org.springframework_gs-rest-service_jar_0.1.0PU");
        EntityManager em= emf.createEntityManager();
        List<Offices> o1= em.createNamedQuery("Offices.findAll").getResultList();
        System.out.println(o1.toString());
        for(Offices o:o1)
        {
            Maps.OffceMap.put(Count,o);
             Count++;
        }
        Maps.OfficeNumber=Count;
        s1=MakeTree(o1,em);
        model.addAttribute("s1", s1);
        model.addAttribute("Employees", new Employees());
        return "index";
    }
@GetMapping("/office")
    public String office() {
   return "offices";
    }
    @GetMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "redirect:/";
    }
    @PostMapping("/add_office")
    public String checkPersonInfo(Model model,@Valid Offices office , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        office.setHc(0);
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("org.springframework_gs-rest-service_jar_0.1.0PU");
        EntityManager em= emf.createEntityManager();
        EntityTransaction entr=em.getTransaction();
                if(em.createQuery ("SELECT MAX(o.id) FROM Offices o").getSingleResult()==null)
        {
            office.setId(1);
        }
        else
        {
        office.setId(1+(int)em.createQuery ("SELECT MAX(s.id) FROM Subs s").getSingleResult());
        }
        List <Offices> off = (List <Offices>) em.createNamedQuery("Offices.findByName").setParameter("name", office.getName()).getResultList();
        if(!off.isEmpty())
        {
            model.addAttribute("error", "Офис с таким наименованием уже существует.");
            model.addAttribute("office", office);
            return "add_office";
        }
        entr.begin();
        em.persist(office);
        entr.commit();
        em.close();
        emf.close();
        model.addAttribute("s1", s1);
System.out.println(office.getName());
        return "redirect:/";
    }
    @PostMapping("/add_sub")
    public String add_sub(Model model,@Valid Subs sub , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("org.springframework_gs-rest-service_jar_0.1.0PU");
        EntityManager em= emf.createEntityManager();
        EntityTransaction entr=em.getTransaction();
        List <Offices> subs = (List <Offices>) em.createNamedQuery("Subs.findByName").setParameter("name", sub.getName()).getResultList();
        if(!subs.isEmpty())
        {
            model.addAttribute("error", "Подразделение с таким наименованием уже существует.");
            model.addAttribute("sub", sub);
            return "add_sub";
        }
        entr.begin();
        sub.setHc(0);
        if(em.createQuery ("SELECT MAX(s.id) FROM Subs s").getSingleResult()==null)
        {
            sub.setId(1);
        }
        else
        {
        sub.setId(1+(int)em.createQuery ("SELECT MAX(s.id) FROM Subs s").getSingleResult());
        }
        switch(Maps.OfficeSub){
            case 1:
                sub.setParent(Maps.SelOffice.getName());
                Offices off = (Offices) em.createNamedQuery("Offices.findByName").setParameter("name", Maps.SelOffice.getName()).getResultList().get(0);
                off.setHc(1);
        break;
            case 2:
                sub.setParent(Maps.SelSub.getName());
                Subs offsub = (Subs) em.createNamedQuery("Subs.findByName").setParameter("name", Maps.SelSub.getName()).getResultList().get(0);
                offsub.setHc(1);
        break;
        }
        em.persist(sub);
        entr.commit();
        em.close();
        emf.close();
        model.addAttribute("s1", s1);
System.out.println(sub.getName());
        return "redirect:/";
    }
    @PostMapping("/edit_office")
    public String edit_office(Model model,@Valid Offices office , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        office.setHc(0);
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("org.springframework_gs-rest-service_jar_0.1.0PU");
        EntityManager em= emf.createEntityManager();
        EntityTransaction entr=em.getTransaction();
        List <Offices> off1 = (List <Offices>) em.createNamedQuery("Offices.findByName").setParameter("name", office.getName()).getResultList();
        entr.begin();
        Offices off = (Offices) em.createNamedQuery("Offices.findByName").setParameter("name", Maps.SelOffice.getName()).getResultList().get(0);
        List <Subs> offsub = (List <Subs>) em.createNamedQuery("Subs.findByParent").setParameter("parent", Maps.SelOffice.getName()).getResultList();
        for (Subs sub1: offsub)
        {
            sub1.setParent(office.getName());
        }
        off.setName(office.getName());
        off.setAddr(office.getAddr());
        entr.commit();
        em.close();
        emf.close();
        model.addAttribute("s1", s1);
System.out.println(office.getName());
        return "redirect:/";
    }
    @PostMapping("/edit_sub")
    public String edit_sub(Model model,@Valid Subs sub , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("org.springframework_gs-rest-service_jar_0.1.0PU");
        EntityManager em= emf.createEntityManager();
        EntityTransaction entr=em.getTransaction();
        entr.begin();
        Subs offsub = (Subs) em.createNamedQuery("Subs.findByName").setParameter("name", Maps.SelSub.getName()).getResultList().get(0);
        List <Subs> offsubl = (List <Subs>) em.createNamedQuery("Subs.findByParent").setParameter("parent", Maps.SelSub.getName()).getResultList();
        for (Subs sub2: offsubl)
        {
            sub2.setParent(sub.getName());
        }
        List <Employees> employel = (List <Employees>) em.createNamedQuery("Employees.findByOffice").setParameter("office", Maps.SelSub.getName()).getResultList();
        for (Employees employe: employel)
        {
            employe.setOffice(sub.getName());
        }
        offsub.setName(sub.getName());
        offsub.setFio(sub.getFio());
        entr.commit();
        em.close();
        emf.close();
        model.addAttribute("s1", s1);
System.out.println(sub.getName());
        return "redirect:/";
    }
    @PostMapping("/addemployee")
    public String add_employee(Model model,@Valid Employees employee , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("org.springframework_gs-rest-service_jar_0.1.0PU");
        EntityManager em= emf.createEntityManager();
        EntityTransaction entr=em.getTransaction();
        List <Employees> employees = (List <Employees>) em.createNamedQuery("Employees.findByMail").setParameter("mail", employee.getMail()).getResultList();
        if(!employees.isEmpty())
        {
            model.addAttribute("error", "Пользователь с таким адресом електронной почты уже существует.");
            model.addAttribute("employee", employee);
            return "add_employee";
        }
        if(!EmailValidator.getInstance().isValid(employee.getMail()))
        {
            model.addAttribute("error", "Адрес электронной почты имеет недопустимый формат.");
            model.addAttribute("employee", employee);
            return "add_employee";
        }
        entr.begin();
        employee.setOffice(Maps.SelSub.getName());
        em.persist(employee);
        entr.commit();
        em.close();
        emf.close();
        model.addAttribute("s1", s1);
System.out.println(employee.getBirth());
        return "redirect:/";
    }
    @PostMapping("/editemployee")
    public String edit_employee(Model model,@Valid Employees edited_employee , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().toString());
            model.addAttribute("Employees", edited_employee);
            return "redirect:/";
        }
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("org.springframework_gs-rest-service_jar_0.1.0PU");
        EntityManager em= emf.createEntityManager();
        EntityTransaction entr=em.getTransaction();
        if(!EmailValidator.getInstance().isValid(edited_employee.getMail()))
        {
            model.addAttribute("error", "Адрес электронной почты имеет недопустимый формат.");
            model.addAttribute("employee", edited_employee);
            return "add_employee";
        }
        entr.begin();
        Employees employee =(Employees)em.createNamedQuery("Employees.findByName").setParameter("name", edited_employee.getName()).getResultList().get(0);
        employee.setMail(edited_employee.getMail());
        employee.setBirth(edited_employee.getBirth());
        employee.setTel(edited_employee.getTel());
        em.persist(employee);
        entr.commit();
        em.close();
        emf.close();
        model.addAttribute("s1", s1);
System.out.println(employee.getBirth());
        return "redirect:/";
    }
    static String MakeTree(List<Offices> lo,EntityManager em){
        String s=new String();
        //s+=lb;
        for(Offices o:lo)
        {
            if (o.getHc()==0)
            {
                s=s+lb+idb+quote+OfficeCount.toString()+quote+lbe+o.getName()+le;
            }
            else
            {
               s+=nlb+idb+quote+OfficeCount.toString()+quote+lbe+o.getName()+nle+nlmb;
               s+=MakeSub(em.createNamedQuery("Subs.findByParent").setParameter("parent", o.getName()).getResultList(),em);
               s+=nlme;
            }
            OfficeCount++;
        }
        return s;
        }
    static String MakeSub(List<Subs> lo,EntityManager em){
        String s=new String();
        //s+=lb;
        for(Subs o:lo)
        {
            Maps.SubsMap.put(Count,o);
            if (o.getHc()==0)
            {
                s=s+lb+idb+quote+Count.toString()+quote+lbe+o.getName()+le;
                Count++;
            }
            else
            {
               s+=nlb+idb+quote+Count.toString()+quote+lbe+o.getName()+nle+nlmb;
               Count++;
               s+=MakeSub(em.createNamedQuery("Subs.findByParent").setParameter("parent", o.getName()).getResultList(),em);
               s+=nlme;
            }
        }
        return s;
        }
}


